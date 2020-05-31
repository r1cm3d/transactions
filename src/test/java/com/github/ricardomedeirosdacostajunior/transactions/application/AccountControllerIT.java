package com.github.ricardomedeirosdacostajunior.transactions.application;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIT {

  private static final String DOCUMENT_NUMBER = "42";
  private static final String ACCOUNT_ENDPOINT = "/accounts";

  @MockBean private AccountController accountController;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void create() throws Exception {
    var requestAccountDTO = AccountDTO.builder().documentNumber(DOCUMENT_NUMBER).build();

    mockMvc
        .perform(
            post(ACCOUNT_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestAccountDTO)))
        .andExpect(status().isOk());
  }
}

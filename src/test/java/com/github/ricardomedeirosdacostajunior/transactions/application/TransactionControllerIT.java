package com.github.ricardomedeirosdacostajunior.transactions.application;

import static java.math.BigDecimal.valueOf;
import static java.util.UUID.fromString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.AccountDTO;
import com.github.ricardomedeirosdacostajunior.transactions.domain.dto.TransactionDTO;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIT {

  private static final String TRANSACTIONS_ENDPOINT = "/transactions";
  private static final UUID ACCOUNT_UUID = fromString("8df29f84-4bcf-49f3-babd-3acbb8ec1673");
  private static final Integer PAYMENT = 4;
  private static final BigDecimal AMOUNT = valueOf(123.45);

  @MockBean
  private TransactionController transactionController;

  @Autowired
  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void create() throws Exception {
    var requestTransactionDTO = TransactionDTO.builder()
        .accountUuid(ACCOUNT_UUID)
        .operationType(PAYMENT)
        .amount(AMOUNT)
        .build();


    mockMvc
        .perform(
            post(TRANSACTIONS_ENDPOINT)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestTransactionDTO)))
        .andExpect(status().isOk());
  }
}


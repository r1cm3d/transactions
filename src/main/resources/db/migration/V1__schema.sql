create table account(
  uuid uuid NOT NULL constraint account_pk primary key,
  document_number text NOT NULL
);

create table transaction(
  uuid uuid NOT NULL constraint transaction_pk primary key,
  account_id uuid NOT NULL constraint account_fk references account,
  amount numeric(19, 2) NOT NULL,
  event_date TIMESTAMP NOT NULL,
  operation_type integer NOT NULL
);
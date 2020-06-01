# Transactions

Create account

**URL** : `/transactions`

**Method** : `POST`

#### Data constraints

```json
{
  "account_id": "UUID",
  "operation_type": "[1-4]",
  "amount": numeric_number
}
```

#### Example

```json
{
  "account_id": "b2b5aa47-f0e5-4866-9ab1-12b8bd6632d5",
  "operation_type": "1",
  "amount": 123.45
}
```

### Success Response

**Code** : `200 OK`

**Content example**

```json
{
  "amount": -123.45,
  "id": "a19f0c93-1559-4e10-8d55-53f74ef35f72",
  "account_id": "b2b5aa47-f0e5-4866-9ab1-12b8bd6632d5",
  "operation_type": 1,
  "event_date": "2020-06-01T21:59:45.133455777"
}
```

### Error response
Whether `operation_type` is missing or invalid or `account_id` is missing or invalid the response will be `Bad Request`

## curl example
In the project root:
```sh
curl -sX POST http://localhost:8080/transactions -d @scripts/json/transaction.json -H "Content-type: application/json"
```



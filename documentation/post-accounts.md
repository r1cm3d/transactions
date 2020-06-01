# Accounts

Create account

**URL** : `/accounts`

**Method** : `POST`

#### Data constraints

```json
{
	"document_number": "some_document_number"
}
```

#### Example

```json
{
	"document_number": "06388715907"
}
```

### Success Response

**Code** : `200 OK`

**Content example**

```json
{
	"account_id": "f8eface4-fedf-4b5b-b230-fe950aa3db8f",
	"document_number": "06388715907"
}
```

### Error response
Whether `document_number` is invalid the response will be `Bad Request`

## curl example
In the project root:
```sh
curl -sX POST http://localhost:8080/accounts -d @scripts/json/account.json -H "Content-type: application/json"
```



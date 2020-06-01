# Accounts

Query account

**URL** : `/accounts/:id`

**Method** : `GET`

### Success Response

**Code** : `200 OK`

**Content example**

```json
{
	"account_id": "f8eface4-fedf-4b5b-b230-fe950aa3db8f",
	"document_number": "06388715907"
}
```

## curl example
In the project root:
```sh
curl -sX GET http://localhost:8080/accounts/f8eface4-fedf-4b5b-b230-fe950aa3db8f -H "Content-type: application/json"
```



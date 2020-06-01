#!/bin/bash

echo "Creating a new account";
FIRST_REQUEST="curl -sX POST http://localhost:8080/accounts -d @scripts/json/account.json -H \"Content-type: application/json\" | grep -Po '(?<=uuid\":\")[^\"]+'";
echo "$FIRST_REQUEST";
NEW_UUID="$(eval $FIRST_REQUEST)";
echo "Account UUID created"
echo $NEW_UUID;
echo "\nQuerying this ID $NEW_UUID"

SECOND_REQUEST="curl -sX GET http://localhost:8080/accounts/$NEW_UUID"
echo "$SECOND_REQUEST";
eval "$SECOND_REQUEST";
echo "\n\nCreating account error example"

THIRD_REQUEST="curl -sX POST http://localhost:8080/accounts -d @scripts/json/account-error.json -H 'Content-type: application/json'";
echo "$THIRD_REQUEST"
eval "$THIRD_REQUEST";
TRANSACTION_REQUEST="{ \"accountUuid\": \"$NEW_UUID\", \"operationType\": \"1\", \"amount\": 123.45 }";
echo "\n\nCreating a new transaction according this account UUID $NEW_UUID"

FOURTH_REQUEST="curl -sX POST http://localhost:8080/transactions -d '$TRANSACTION_REQUEST' -H 'Content-type: application/json'"
echo "$FOURTH_REQUEST"
eval "$FOURTH_REQUEST";
echo "\n"
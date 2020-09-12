Run application with mvn spring-boot:run or any IDE.

Example curl for credit operation: 
curl --location --request POST 'http://localhost:8080/user/v1/transaction' \
--header 'Content-Type: application/json' \
--data-raw '{
    "account_id": "test-12345",
    "sum": "10.0",
    "action": "CREDIT",
    "date": "1000"
}'

Example curl for debit operation:
curl --location --request POST 'http://localhost:8080/user/v1/transaction' \
--header 'Content-Type: application/json' \
--data-raw '{
    "account_id": "test-12345",
    "sum": "20.0",
    "action": "DEBIT",
    "date": "1000"
}'
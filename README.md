#программа для перевода денег 
Примеры запросов:

Сборка и запуск:
mvn clean package
docker-compose build
docker-compose up


Перевод денег:
curl -X POST http://localhost:8080/transfer \
  -H "Content-Type: application/json" \
  -d '{
    "cardFromNumber": "1234567812345678",
    "cardFromValidTill": "12/25",
    "cardFromCVV": "123",
    "cardToNumber": "8765432187654321",
    "amount": {
      "value": 1000,
      "currency": "RUB"
    }
  }'


Подтверждение операции:

curl -X POST http://localhost:8080/transfer/confirmOperation \
  -H "Content-Type: application/json" \
  -d '{
    "operationId": "a1b2c3d4-e5f6-7890",
    "code": "1234"
  }'

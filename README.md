## Berlin Delivery Challenge

If Customers in our Store order they want to know when the expected delivery date is. The expected delivery date depends on certain conditions:

1- Which Carrier is selected. Each Carrier has different delivery days DHL for example needs 2 days, Hermes needs 3 days.

2- The cut-off hour in the warehouse, after this time, a parcel is not shipped on this day anymore.

3- The working times in the warehouse. Our warehouse only works from Monday to Friday.

4- Public Holidays, Germany has a lot of common public holidays but Berlin has some special holidays.

So the challenge is, please provide a function, where can be filled in the current time and a provider and get returned the expected delivery date.

The configuration for different carriers is free to choose.

For the given test data, we assume cut-off-time: 12 and delivery days: 2

POST: `http://localhost:8081/delivery/date` - 1 order in request

POST: `http://localhost:8081/delivery/date-list` - list of orders in request

Request Body:
`
[
{
"orderId": 1,
"carrierId": 1,
"warehouseId" : 1,
"orderDate": "2020-03-30T10:21:38"
},
{
"orderId": 2,
"carrierId": 1,
"warehouseId" : 1,
"orderDate": "2020-03-30T11:21:38"
},
{
"orderId": 3,
"carrierId": 1,
"warehouseId" : 1,
"orderDate": "2020-04-01T11:21:38"
},
{
"orderId": 4,
"carrierId": 1,
"warehouseId" : 1,
"orderDate": "2020-01-01T11:21:38"
}
]
`

Response Body:
`
[
{
"orderId": 1,
"deliveryDate": "2020-04-01"
},
{
"orderId": 2,
"deliveryDate": "2020-04-02"
},
{
"orderId": 3,
"deliveryDate": "2020-04-04"
},
{
"orderId": 4,
"deliveryDate": "2020-01-04"
}
]
`


#### Test & Validation

Run `mvn test` for Unit Tests (**/*Test.java)

Run `mvn integration-test` for Integration Tests (**/*IT.java)

JUnit5, Mockito and Github-Faker are used in this project. Used patterns:

* TDD (Test Driven Development)

* GWT (Given When Then)

#### Swagger & Api-Docs

Used Swagger and Api-Docs for RestAPI endpoints.

`http://localhost:8081/swagger-ui/index.html?configUrl=/api-docs/swagger-config`


### Notes

- **Docker** is used for containerization.
- **Lombok** is used for autogenerate boilerplate zero-value codes.
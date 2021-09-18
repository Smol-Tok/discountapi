# discountapi
calculates discounts

<h1> Running the application </h1>

The executable application is located at root folder  <b>build\libs </b>

<br/>
The application may be run by run by opening command prompe and issueing command $ java -jar disacountapi-0.0.1.jar

<br />
<h3>API Test </h3>


On terminal $ curl localhost:7770/api/v1/paybill . A list of customers are displayed
<br />

To pay bill <br />
curl -X POST -H 'Content-Type: application/json' -d '{ "customerID": 1,  "billType": "bill",  "amount": 995 }' http://localhost:7770/api/v1/paybill -i

<h2> Generating voverage report</h2>
  
  Run <b> ./gradlew build jacocoTestReport </b> on root folder 
  <br>
located at  main/build/reports/jacoco/
  
  <h3> API documentation </h3>
  
Post api located at can be used for documenting the API https://www.postman.com/api-documentation-tool/ 

# discountapi
calculates discounts

<h1> Running the application </h1>

To run the API application navigate to  <b>build\libs </b>

<br/>
Run the application by executing the command <b>java -jar disacountapi-0.0.1.jar </a>

<br />
<h3>API Test </h3>


On terminal $ curl localhost:7770/api/v1/paybill . A list of customers are displayed
<br />

To pay bill <br />
curl -X POST -H 'Content-Type: application/json' -d '{ "customerID": 1,  "billType": "bill",  "amount": 995 }' http://localhost:7770/api/v1/paybill -i

<h2> Generating coverage report</h2>
  
  Run <b> "./gradlew build jacocoTestReport" </b> on cmd while in root folder 
  <br>
Reports are located at <b> main/build/reports/jacoco/</b>

<h2> Running unit test report</h2>
Run <b>"./gradlew test --tests  DisacountapiApplicationTests"</b> on cmd while in root folder  
<br />
Reports are located at <b>build\reports\tests\test</b>
  
  <h3> API documentation </h3>
  
Post api located at can be used for documenting the API https://www.postman.com/api-documentation-tool/ 

# discountapi
calculates discounts

<h1> Running the application </h1>

To run the API application navigate to  <b>build\libs </b>
<br/>
Run the application by executing the command <b>java -jar disacountapi-0.0.1.jar </b>

<br />
<h3>API Test </h3>

On terminal run <b>curl localhost:7770/api/v1/paybill</b> . A list of customers are displayed

<i><small>[{"id":1,"name":"John Wick","isEmployee":true,"isAffiliate":false,"dateRegistered":"2021-04-02","employee":true,"affiliate":false},{"id":2,"name":"Spider Man","isEmployee":true,"isAffiliate":false,"dateRegistered":"2010-04-02","employee":true,"affiliate":false},{"id":3,"name":"Super Man","isEmployee":true,"isAffiliate":false,"dateRegistered":"2004-04-02","employee":true,"affiliate":false},{"id":4,"name":"Batma","isEmployee":true,"isAffiliate":true,"dateRegistered":"2021-04-02","employee":true,"affiliate":true},{"id":5,"name":"John Wick","isEmployee":true,"isAffiliate":true,"dateRegistered":"2001-04-02","employee":true,"affiliate":true},{"id":6,"name":"Green Goblin","isEmployee":false,"isAffiliate":false,"dateRegistered":"2004-04-02","employee":false,"affiliate":false}]
  </small> </i>
<br />

<p>Posting a paybill  requires the customer Id , the amount and the bill type. Bill type can either be grocery or bill</p>

To pay bill execute <b> curl -X POST -H 'Content-Type: application/json' -d '{ "customerID": 1,  "billType": "grocery",  "amount": 995 }' http://localhost:7770/api/v1/paybill -i</b>
<br />
The netpayment is returned <i>
{"discount":"$ 45.0","netpayment":"$ 950.0"}</i>

<h2>Postman test</h2>
<img src="https://github.com/Smol-Tok/discountapi/blob/main/postmanpost.PNG" />

<h2> Generating coverage report</h2>
  
  Run <b> "./gradlew build jacocoTestReport" </b> on cmd while in root folder 
  <br>
Reports are located at <b> main/build/reports/jacoco/</b>

<h2> Running unit test report</h2>
Run <b>"./gradlew test --tests  DisacountapiApplicationTests"</b> on cmd while in root folder  
<br />
Reports are located at <b>build\reports\tests\test</b>
  
  <h3> API documentation </h3>
  
Postman can be used to document the API. Postman collections is a set of pre-built requests grouped into folders during testing and can be  exported and shared. 

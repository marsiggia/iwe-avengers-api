Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://6elf5xvhvl.execute-api.us-east-2.amazonaws.com/dev'

Scenario: Get Avenger by Id

Given path 'avengers', '1'
When method get
Then status 200
And match response == {id: '#string', name: 'Iron Man', secretIdentity: 'Tony Stark'}

Scenario: Registry new Avenger

Given path 'avengers'
And request {name: 'Thor', secretIdentity: 'Thor'}
When method post
Then status 201
And match response == {id: '#string', name: 'Thor', secretIdentity: 'Thor'}

Scenario: Update existing Avenger by ID

Given path 'avengers', '2'
And request {name: 'Thor - Odin\'s Son', secretIdentity: 'Thor'}
When method put
Then status 200
And match response == {id: '#string', name: 'Thor - Odin\'s Son', secretIdentity: 'Thor'}

Scenario: Remove existing Avenger

Given path 'avengers', '2'
When method delete
Then status 204

Scenario: Registry Avenger with Invalid Payload

Given path 'avengers'
And request {secretIdentity: 'Thor'}
When method post
Then status 400

Scenario: Update Avenger with Invalid Payload

Given path 'avengers', '2'
And request {secretIdentity: 'Thor'}
When method put
Then status 400

Scenario: Avenger Not Found

Given path 'avengers', 'invalid'
When method get
Then status 404

Scenario: Update Avenger NotFound

Given path 'avengers', 'invalid'
And request {name: 'Thor - Odin\'s Son', secretIdentity: 'Thor'}
When method put
Then status 404

Scenario: Remove Avenger NotFound

Given path 'avengers', 'invalid'
When method delete
Then status 404


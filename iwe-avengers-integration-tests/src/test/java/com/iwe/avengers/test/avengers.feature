Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://6elf5xvhvl.execute-api.us-east-2.amazonaws.com/dev'
#nao é auto suficiente por isso foi comentado
#Scenario: Get Avenger by Id

#Given path 'avengers', '1'
#When method get
#Then status 200
#And match response == {id: '#string', name: 'Iron Man', secretIdentity: 'Tony Stark'}

Scenario: Should return Unauthorized access

Given path 'avengers', 'anyid'
When method get
Then status 401

Scenario: Registry new Avenger

Given path 'avengers'
And request {name: 'Iron Man', secretIdentity: 'Tony Stark'}
When method post
Then status 201
And match response == {id: '#string', name: 'Iron Man', secretIdentity: 'Tony Stark'}

* def savedAvenger = response

#Get Avenger by Id
Given path 'avengers', savedAvenger.id
When method get
Then status 200
And match $ == savedAvenger

Scenario: Update existing Avenger by ID

#Create Avenger by Id
Given path 'avengers'
And request {name: 'Thor', secretIdentity: 'Thor'}
When method post
Then status 201
And match response == {id: '#string', name: 'Thor', secretIdentity: 'Thor'}

* def createdAvenger = response

#Update Avenger by Id
Given path 'avengers', createdAvenger.id
And request {name: 'Thor - Odin\'s son', secretIdentity: 'Thor - The Lightining God'}
When method put
Then status 200
And match $.id == createdAvenger.id
And match $.name == 'Thor - Odin\'s son' 
And match $.secretIdentity == 'Thor - The Lightining God'

* def updatedAvenger = response

#Get Avenger by Id
Given path 'avengers', updatedAvenger.id
When method get
Then status 200
And match $ == updatedAvenger

Scenario: Remove existing Avenger

#Create Avenger
Given path 'avengers'
And request {name: 'Hulk', secretIdentity: 'Bruce Banner'}
When method post
Then status 201

* def avengerToRemove = response

#Delete Avenger by Id
Given path 'avengers', avengerToRemove.id
When method delete
Then status 204

#Get Avenger by Id should return 404
Given path 'avengers', avengerToRemove.id
When method get
Then status 404

Scenario: Registry Avenger with Invalid Payload

Given path 'avengers'
And request {secretIdentity: 'Thor'}
When method post
Then status 400

Scenario: Update Avenger with Invalid Payload

#Create Avenger by Id
Given path 'avengers'
And request {name: 'Captain America', secretIdentity: 'Stieve Rogers'}
When method post
Then status 201
And match response == {id: '#string', name: 'Captain America', secretIdentity: 'Stieve Rogers'}

* def createdAvenger = response

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


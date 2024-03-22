# Java Backend Server for User Registration Authentication

This Java backend server provides API endpoints for user registration and authentication. It allows users to register with their personal information and authenticate themselves with their email and password.

## API Endpoints

### User Registration

- **Endpoint**: `https://liedetector.azurewebsites.net/api/users/register`
- **Method**: `POST`

**Json Form Data for Registering a New User**:

```json
{
  "firstName": "",
  "lastName": "",
  "email": "",
  "password": ""
}
```

### User Login

- **Endpoint**: `https://liedetector.azurewebsites.net/api/users/login`
- **Method**: `POST`

**Json Form Data for User Login**:

```json
{
  "email": "",
  "password": ""
}
```

### User Details Update(If Password is also modified by user)

- **Endpoint**:`https://liedetector.azurewebsites.net/api/users/update`
- **Method**:`POST`
- **Header** Key:`Authorization` Value:`BearerJWTToken`

  **Json Form Data for Updating an Existing User Details**:

```json
{
  "firstName": "",
  "lastName": "",
  "email": "",
  "password": ""
}
```

### User Details Update(If Password is not modified by user)

- **Endpoint**:`https://liedetector.azurewebsites.net/api/users/update1`
- **Method**:`POST`
- **Header** Key:`Authorization` Value:`BearerJWTToken`

**Json Form Data for Updating an Existing User Details**:

```json
{
  "firstName": "",
  "lastName": "",
  "email": ""
}
```

### Adding a Question for Displaying it to User
- **Endpoint**: `https://liedetector.azurewebsites.net/api/questions/add`
- **Method**:`POST`
  **Json Form Data**
```json

{
  "question": "",
  "answer": "",
  "questionType": "Arithmetic" or "Image" or "Random"
}

```

### Delete a Question
- **Endpoint**: `https://liedetector.azurewebsites.net/api/questions/delete`
- **Method**:`POST`
  **Json Form Data**
```json

{
  "questionId": ""
}

```
### Update Question  for Displaying it to User
- **Endpoint**: `https://liedetector.azurewebsites.net/api/questions/update`
- **Method**:`POST`
  **Json Form Data**
```json

{
  "questionId": "",
  "question": "",
  "answer": "",
  "questionType": ""
}

```
### Add Multiple Questions  for Displaying it to User
- **Endpoint**: `https://liedetector.azurewebsites.net/api/questions/addQuestions`
- **Method**:`POST`
  **Json Form Data**
```json

[
  {
    "question": "",
    "answer": "",
    "questionType": ""
  },
  {
    "question": "",
    "answer": "",
    "questionType": ""
  }
]

```

### Fetch Question   for Displaying it to User Using questionId
- **Endpoint**: `https://liedetector.azurewebsites.net/api/questions/fetchQuestion`
- **Method**:`GET`
  **Json Form Data**
```json

{
  "questionId": ""
}

```
### Fetch List of Questions
- **Endpoint**: `https://liedetector.azurewebsites.net/api/questions/fetchQuestionList`
- **Method**:`GET`
**Json Form Data**
```json
{
  "type": "",
  "count": ""
}
```
### Creating of Test Session and Fetching Required Inputs to Display for User

- **Endpoint**: `https://liedetector.azurewebsites.net/api/UserTestResponse/generateSession`
- **Method**:`POST`
  **Json Form Data for request**
```json

{
"userId":"",
"startTimeStamp":"",
"type":"",
"count":1 

}
```
**Json Response Form Data from Request**
```json

{
  "questions": [
    {
      "questionId": "de93bbfe-22cc-423e-8239-ff3336c9db5a",
      "question": "What is 8 + 4?",
      "answer": "12",
      "questionType": "Arithmetic"
    }
  ],
  "sessionId": "9f6205ed-a201-48ad-8289-8600a159645b"
}

```
### Storing User Submitted Response in Database for Processing and Future Use
- **Endpoint**: `https://liedetector.azurewebsites.net/api/UserTestResponse/saveUserResponse`
- **Method**:`POST`
  **Json Form Data for request**
```json

{
  "questions": [
    {
      "questionId": "1db81f74-e685-43be-8dbb-b61d93fd5dc8",
      "question": "What is 20 / 4?",
      "answer": "5",
      "userAnswer": "6",
      "userRole": "deceiver",
      "questionType": "Arithmetic",
      "questionStartTime": "2021-03-24 08:30:05",
      "questionEndTime": "2024-03-23 12:30:45"
    }
  ],
  "sessionId": "02c24df3-4867-4c1e-9d29-6694f38f24b0",
  "sessionEndTime": "2024-03-23 12:30:45",
  "userId": ""
}

```



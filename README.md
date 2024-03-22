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

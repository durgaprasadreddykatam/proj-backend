# Java Backend Server for User Registration Authentication

This Java backend server provides API endpoints for user registration and authentication. It allows users to register with their personal information and authenticate themselves with their email and password.

## API Endpoints

### User Registration

- **Endpoint**: `http://ip-address:8080/api/users/register`
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

- **Endpoint**: `http://ip-address:8080/api/users/login`
- **Method**: `POST`

**Json Form Data for User Login**:

```json
{
  "email": "",
  "password": ""
}
```

### User Details Update(If Password is also modified by user)

- **Endpoint**:`http://ip-address:8080/api/users/update`
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

- **Endpoint**:`http://ip-address:8080/api/users/update1`
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

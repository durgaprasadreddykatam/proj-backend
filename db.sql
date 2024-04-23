CREATE TABLE IF NOT EXISTS UserDetails (
    user_id uuid PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    intro_test_taken BOOLEAN,
    intro_seen BOOLEAN

    );

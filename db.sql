CREATE TABLE IF NOT EXISTS UserDetails (
    user_id uuid PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    intro_test_taken_as_liar BOOLEAN,
    intro_test_taken_as_truth_teller BOOLEAN,
    intro_seen BOOLEAN,
    assigned_number INT

    );
CREATE TABLE IF NOT EXISTS Questions (
                                         question_id uuid PRIMARY KEY,
                                         question VARCHAR(255),
                                         answer VARCHAR(255),
                                         question_type VARCHAR(255)

);


CREATE TABLE IF NOT EXISTS test_session (
                                            session_id UUID PRIMARY KEY,
                                            user_id uuid,
                                            session_start_time_stamp TIMESTAMP,
                                            session_end_time_stamp  TIMESTAMP,
                                            role VARCHAR(128),
                                            test_or_train VARCHAR(128),
                                            FOREIGN KEY (user_id) REFERENCES userdetails (user_id)
);
CREATE TABLE IF NOT EXISTS user_response (
                                             user_response_id UUID PRIMARY KEY,
                                             session_id UUID,
                                             user_id uuid,
                                             question_id UUID,
                                             user_answer VARCHAR(255),
                                             user_role VARCHAR(255),
                                             question_start_time_stamp TIMESTAMP,
                                             question_end_time_stamp TIMESTAMP,
                                             FOREIGN KEY (session_id) REFERENCES test_session(session_id),
                                             FOREIGN KEY (user_id) REFERENCES userdetails(user_id) ,
                                             FOREIGN KEY (question_id) REFERENCES questions(question_id)
);


CREATE TABLE IF NOT EXISTS eeg_string (
                                            sequence_id uuid PRIMARY KEY ,
                                            session_id uuid ,
                                            timestamp TIMESTAMP ,
                                            eeg_data VARCHAR(128000),
                                            train_or_test VARCHAR(128),
                                            role VARCHAR(128)

);

CREATE TABLE IF NOT EXISTS eeg_test_lie (

                                            af3 DOUBLE PRECISION,
                                            af4 DOUBLE PRECISION,
                                            pz DOUBLE PRECISION,
                                            t7 DOUBLE PRECISION,
                                            t8 DOUBLE PRECISION


);

CREATE TABLE IF NOT EXISTS eeg_test_truth (

                                            af3 DOUBLE PRECISION,
                                            af4 DOUBLE PRECISION,
                                            pz DOUBLE PRECISION,
                                            t7 DOUBLE PRECISION,
                                            t8 DOUBLE PRECISION


);

CREATE TABLE IF NOT EXISTS eeg_train_lie (

                                              af3 DOUBLE PRECISION,
                                              af4 DOUBLE PRECISION,
                                              pz DOUBLE PRECISION,
                                              t7 DOUBLE PRECISION,
                                              t8 DOUBLE PRECISION


);

CREATE TABLE IF NOT EXISTS eeg_train_truth (

                                             af3 DOUBLE PRECISION,
                                             af4 DOUBLE PRECISION,
                                             pz DOUBLE PRECISION,
                                             t7 DOUBLE PRECISION,
                                             t8 DOUBLE PRECISION


);
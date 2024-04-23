

CREATE TABLE IF NOT EXISTS test_session (
                                            session_id UUID PRIMARY KEY,
                                            user_id uuid,
                                            session_start_time_stamp TIMESTAMP,
                                            session_end_time_stamp  TIMESTAMP,
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

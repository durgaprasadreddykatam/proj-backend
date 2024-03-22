

CREATE TABLE IF NOT EXISTS TestSessions (
                                            SessionID UUID PRIMARY KEY,
                                            USER_ID VARCHAR(255),
                                            SessionStartTimeStamp TIMESTAMP,
                                            SessionEndTimeStamp TIMESTAMP,
                                            FOREIGN KEY (USER_ID) REFERENCES users(USER_ID)
);
CREATE TABLE IF NOT EXISTS UserResponses (
                                             UserResponseID UUID PRIMARY KEY,
                                             SessionID UUID,
                                             USER_ID VARCHAR(255),
                                             QuestionID UUID,
                                             UserAnswer VARCHAR(255),
                                             UserRole VARCHAR(255),
                                             QuestionStartTimeStamp TIMESTAMP,
                                             QuestionEndTimeStamp TIMESTAMP,
                                             FOREIGN KEY (SessionID) REFERENCES TestSessions(SessionID),
                                             FOREIGN KEY (USER_ID) REFERENCES users(USER_ID),
                                             FOREIGN KEY (QuestionID) REFERENCES Questions(questionid)
);

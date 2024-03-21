package cs555.devdynamos.projbackend.domain;

import java.util.UUID;


public class User {

    public User(UUID userId, String firstName, String lastName, String email, String password,boolean introTestTaken,boolean introSeen) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.introTestTaken=introTestTaken;
        this.introSeen=introSeen;

    }


    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public boolean isIntroTestTaken() {
        return introTestTaken;
    }

    public void setIntroTestTaken(boolean introTestTaken) {
        this.introTestTaken = introTestTaken;
    }

    public boolean isIntroSeen() {
        return introSeen;
    }

    public void setIntroSeen(boolean introSeen) {
        this.introSeen = introSeen;
    }

    private boolean introTestTaken;
    private boolean introSeen;

    public User() {
        super();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

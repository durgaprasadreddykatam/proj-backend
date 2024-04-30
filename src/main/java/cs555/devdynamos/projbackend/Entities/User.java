package cs555.devdynamos.projbackend.Entities;
import jakarta.persistence.*;

import java.util.UUID;


@Entity(name="userdetails")
public class User {

    public User(UUID userId, String firstName, String lastName, String email, String password, boolean introTestTaken, boolean introSeen,int assignedNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.introSeen=introSeen;
        this.assignedNumber = assignedNumber;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Column(name = "assigned_number")
    private int assignedNumber;

    @Column(name = "intro_test_taken_as_liar")
    private boolean introTestTakenAsLiar;
    @Column(name = "intro_test_taken_as_truth_teller")
    private boolean introTestTakenAsTruthTeller;
    private boolean introSeen;

    public int getAssignedNumber() {
        return assignedNumber;
    }

    public void setAssignedNumber(int assignedNumber) {
        this.assignedNumber = assignedNumber;
    }



    public boolean isIntroSeen() {
        return introSeen;
    }

    public void setIntroSeen(boolean introSeen) {
        this.introSeen = introSeen;
    }

    public boolean isIntroTestTakenAsLiar() {
        return introTestTakenAsLiar;
    }

    public void setIntroTestTakenAsLiar(boolean introTestTakenAsLiar) {
        this.introTestTakenAsLiar = introTestTakenAsLiar;
    }

    public boolean isIntroTestTakenAsTruthTeller() {
        return introTestTakenAsTruthTeller;
    }

    public void setIntroTestTakenAsTruthTeller(boolean introTestTakenAsTruthTeller) {
        this.introTestTakenAsTruthTeller = introTestTakenAsTruthTeller;
    }



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

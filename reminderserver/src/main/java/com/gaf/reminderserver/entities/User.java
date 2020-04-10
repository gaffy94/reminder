package com.gaf.reminderserver.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "rem_users")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="UserTb_seqgen")
    @SequenceGenerator(name="UserTb_seqgen", sequenceName="User_SEQ",allocationSize=1)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "aprtmntNumber", unique = true)
    private String aprtmntNumber;

    @Column(name = "password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @javax.persistence.Transient
    private Map<String, Object> genericAttribute = new HashMap<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAprtmntNumber() {
        return aprtmntNumber;
    }

    public void setAprtmntNumber(String aprtmntNumber) {
        this.aprtmntNumber = aprtmntNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public Map<String, Object> getGenericAttribute() {
        return genericAttribute;
    }

    public void setGenericAttribute(Map<String, Object> genericAttribute) {
        this.genericAttribute = genericAttribute;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", aprtmntNumber='" + aprtmntNumber + '\'' +
                ", genericAttribute=" + genericAttribute +
                '}';
    }
}

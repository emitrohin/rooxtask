package ru.emitrohin.roox.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.List;

/**
 * Created by emitrokhin on 27.02.2017.
 */

@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "users_unique_email_idx")})
public class Customer extends BaseEntity {

    private String login;

    private String password;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    private int balance;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "customer")
    private List<PartnerMapping> mappings;

    public Customer(){

    }

    public Customer(String login, String password, String lastName, String firstName, String middleName, int balance, boolean isActive, List<PartnerMapping> mappings) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.balance = balance;
        this.isActive = isActive;
        this.mappings = mappings;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<PartnerMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<PartnerMapping> mappings) {
        this.mappings = mappings;
    }
}

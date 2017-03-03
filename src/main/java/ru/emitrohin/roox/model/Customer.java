package ru.emitrohin.roox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The {@code Customer} is the representation of customers table.
 *
 * Data format
 *
 * login - {@code String} - VARCHAR(255) NOT NULL
 * password - {@code String} - VARCHAR(255) NOT NULL
 * lastName - {@code String} - VARCHAR(255) NOT NULL
 * firstName - {@code String} - VARCHAR(255) NOT NULL
 * middleName - {@code String} - VARCHAR(255) NOT NULL
 * balance - {@code String} - INTEGER NOT NULL,
 * enabled - {@code boolean} - BOOLEAN DEFAULT TRUE,
 *
 * @author  Evgeniy Mitrokhin
 */

@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = "ix_login")})
public class Customer extends BaseEntity {

    @NotNull
    @JsonIgnore
    private String login;

    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "middle_name")
    @NotNull
    private String middleName;

    private int balance;

    private boolean enabled = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonIgnore
    private List<PartnerMapping> mappings;

    public Customer(){

    }

    public Customer(Integer id, String login, String password, String lastName, String firstName, String middleName, int balance, boolean enabled, List<PartnerMapping> mappings) {
        super(id);
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.balance = balance;
        this.enabled = enabled;
        this.mappings = mappings;
    }

    public static Customer fromCustomer(Customer customer)
    {
        return new Customer(customer.getId(), customer.getLogin(), customer.getPassword(), customer.getLastName(), customer.getFirstName(), customer.getMiddleName(), customer.getBalance(), customer.isEnabled(), customer.getMappings());
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<PartnerMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<PartnerMapping> mappings) {
        this.mappings = mappings;
    }
}

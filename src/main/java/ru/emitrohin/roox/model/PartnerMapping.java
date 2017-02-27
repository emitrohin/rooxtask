package ru.emitrohin.roox.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by emitrokhin on 27.02.2017.
 */
@Entity
@Table(name = "partner_mapping", uniqueConstraints = {@UniqueConstraint(columnNames = "partner_id", name = "partner_id_idx")})
public class PartnerMapping {

    @NotEmpty
    @Column(name = "partner_id")
    private String partnerId;

    @NotEmpty
    @Column(name = "customer_id")
    private String customerId;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "avatar_image")
    private String avatarImage;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public PartnerMapping() {
    }

    public PartnerMapping(String partnerId, String customerId, String lastName, String firstName, String middleName, String avatarImage) {
        this.partnerId = partnerId;
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.avatarImage = avatarImage;
    }


    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}

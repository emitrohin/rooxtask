package ru.emitrohin.roox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Created by emitrokhin on 27.02.2017.
 */
@Entity
@Table(name = "partner_mapping", uniqueConstraints = {@UniqueConstraint(columnNames = {"customer_id", "partner_id"}, name = "ix_customer_id_partner_id")})
public class PartnerMapping extends BaseEntity{

    @NotEmpty
    @Column(name = "partner_id")
    private String partnerId;

    @NotEmpty
    @Column(name = "partner_customer_id")
    private String partnerCustomerId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "avatar_image")
    private byte[]  avatarImage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    public PartnerMapping() {
    }

    public PartnerMapping(Integer id, String partnerId, String partnerCustomerId, String lastName, String firstName, String middleName, byte[] avatarImage, Customer customer) {
        super(id);
        this.partnerId = partnerId;
        this.partnerCustomerId = partnerCustomerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.avatarImage = avatarImage;
        this.customer = customer;
    }

    public static PartnerMapping from(PartnerMapping partnerMapping)
    {
        return new PartnerMapping(
                partnerMapping.getId(),
                partnerMapping.getPartnerId(),
                partnerMapping.getPartnerCustomerId(),
                partnerMapping.getLastName(),
                partnerMapping.getFirstName(),
                partnerMapping.getMiddleName(),
                partnerMapping.getAvatarImage(),
                partnerMapping.getCustomer());
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerCustomerId() {
        return partnerCustomerId;
    }

    public void setPartnerCustomerId(String partnerCustomerId) {
        this.partnerCustomerId = partnerCustomerId;
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

    public byte[] getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(byte[] avatarImageByteArray) {
        this.avatarImage = avatarImageByteArray;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "PartnerMapping{" +
                "partnerId='" + partnerId + '\'' +
                ", partnerCustomerId='" + partnerCustomerId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", avatarImage=" + Arrays.toString(avatarImage) +
                '}';
    }
}

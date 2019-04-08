package dto;

import javax.validation.constraints.*;

public class PayerDTO {

    @Pattern(regexp = "(\\p{Alpha}|\\s)*", message = "caracter no validos")
    private String surname = null;

    @Pattern(regexp = "(\\p{Alpha}|\\s)*", message = "caracteres no validos")
    private String name = null;

    private Integer phone = null;

    @NotEmpty
    @Email(message = "El correo electrónico debe ser válido")
    private String email= null;

    private String  identificationType= null;
    @Size(min = 8, max = 9)
    private String  identificationNumber= null;

    private String  number= null;
    private String streetAddress = null;
    private String phoneNumber = null;
    private String phoneArea = null;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneArea() {
        return phoneArea;
    }

    public void setPhoneArea(String phoneArea) {
        this.phoneArea = phoneArea;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}

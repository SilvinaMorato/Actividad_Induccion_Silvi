package dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class PaymentDTO {

    @NotEmpty
    @Email(message = "El correo electrónico debe ser válido")
    private String email= null;

    @NotNull(message = "Es nulo")
    @Positive(message = "No debe ser un numero menor que 0")
    @Digits(integer = 6,fraction = 2, message = "Debe ser un numero con dos decimales")
    private String amount= null;

    @NotEmpty(message = "No puede ser nulo")
    @Pattern(regexp = "[a-z]*", message = "Solo caracteres se aceptan")
    private String PaymentMethodId= null;

    @NotNull(message = "no debe ser nulo")
    @Positive
    @Digits( integer = 2, fraction = 0, message = "Las cuotas de 1 a 18")
    @Min(value = 1, message = "El minimo de cuota es 1.")
    private String Installments = null;

    @NotBlank(message = "no puede ser nulo o estar en blanco")
    private String issuerId = null;

    @NotBlank
    @Pattern(regexp = "(\\p{Alnum}|\\s|'|-)*", message = "Se debe ingresar una descripcion.")
    @Length(min = 32,max = 32,message = "El token tiene un longitud de 32 caracteres.")
    private String token = null;


    @Pattern(regexp = "(\\p{Alnum}|\\s|'|-)*", message = "Se debe ingresar una descripcion.")
    private String decription = null;

    private PayerDTO payer= null;

    public String getAmount() {
        return amount;
    }

    public String getPaymentMethodId() {
        return PaymentMethodId;
    }

    public String getInstallments() {
        return Installments;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public String getToken() {
        return token;
    }

    public String getDecription() {
        return decription;
    }

    public PayerDTO getPayer() {
        return payer;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "PaymentDTO{" +
                "email='" + email + '\'' +
                ", amount='" + amount + '\'' +
                ", PaymentMethodId='" + PaymentMethodId + '\'' +
                ", Installments='" + Installments + '\'' +
                ", issuerId='" + issuerId + '\'' +
                ", token='" + token + '\'' +
                ", decription='" + decription + '\'' +
                ", payer=" + payer +
                '}';
    }
}

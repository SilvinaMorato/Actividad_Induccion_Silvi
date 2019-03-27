package dto;

import javax.validation.constraints.*;

public class ItemDTO {



    @NotNull(message = "Dato Incorrecto o Nulo")
    private String idProduct = null;

    @NotBlank
    @Min(value = 1, message = "Dato Incorrecto")
    @Digits (integer = 2, fraction  = 0)
    private String quantity = null;

    @NotBlank
    @Size(min = 5, max= 50)
    private String titulo = null;


    @NotBlank
    @Min(value = 0, message = "No puede ser menor a 0")
    private String unitPrice = null;

    @NotBlank
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
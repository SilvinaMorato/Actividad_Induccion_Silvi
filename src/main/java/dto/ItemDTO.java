package dto;

import javax.validation.constraints.*;

public class ItemDTO {

    @NotNull(message = "Dato Incorrecto o Nulo")
    private Integer idProduct = null;

    @NotNull
    @Min(value = 1, message = "Dato Incorrecto")
    @Digits (integer = 2, fraction  = 0)
    private Integer quantity = null;

    @NotBlank
    @Size(min = 5, max= 50)
    private String titulo = null;


    @NotNull
    @Min(value = 1, message = "No puede ser menor a 0")
    private Float unitPrice = null;


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
}
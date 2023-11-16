package com.elevenamspringboot.application.payload;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDto {
    private long id;
    @NotEmpty(message = "Product title is required!")
    @Size(min = 2, message = "Product title characters is must be greater than 2!")
    private String title;
    @NotNull(message = "Product price is required!")
    private int price;
    @NotEmpty(message = "Product description is required!")
    private String description;
    @NotEmpty(message = "Product image is required!")
    private String image;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;

    public ProductDto() {
    }

    public ProductDto(long id, String title, int price, String description, String image, LocalDateTime createdOn,
            LocalDateTime modifiedOn) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public String toString() {
        return "ProductDto [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
                + ", image=" + image + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + "]";
    }

}

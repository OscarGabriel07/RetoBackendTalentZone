package com.example.springmongo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Producto")
public class ProductModel {

    @Id
    private Long id;
    private String name;
    private Integer inInventory;
    private Boolean enabled;
    private Integer min;
    private Integer max;


    public ProductModel(Long id, String name, Integer inInventory, Boolean enabled, Integer min, Integer max) {
        this.id = id;
        this.name = name;
        this.inInventory = inInventory;
        this.enabled = enabled;
        this.min = min;
        this.max = max;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInInventory() {
        return inInventory;
    }

    public void setInInventory(Integer inInventory) {
        this.inInventory = inInventory;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}

package com.example.springmongo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "Buy")
public class BuyModel {

    @Id
    private Long id;
    private String date;
    private String idType;
    private String idNumber;
    private String clientName;
    private ArrayList<Order> products;

    public BuyModel(Long id, String date, String idType, String idNumber, String clientName, ArrayList<Order> products) {
        this.id = id;
        this.date = date;
        this.idType = idType;
        this.idNumber = idNumber;
        this.clientName = clientName;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public ArrayList<Order> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Order> products) {
        this.products = products;
    }
}

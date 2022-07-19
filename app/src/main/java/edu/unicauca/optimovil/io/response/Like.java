package edu.unicauca.optimovil.io.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Like {

    @Expose
    @SerializedName("product")
    private String productName;
    @Expose
    @SerializedName("products_id")
    private int productId;
    @Expose
    @SerializedName("clients_id")
    private int clientId;
    @Expose
    @SerializedName("id")
    private int id;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

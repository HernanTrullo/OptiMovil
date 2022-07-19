package edu.unicauca.optimovil.io.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("category")
    private String category;
    @Expose
    @SerializedName("type")
    private String type;
    @Expose
    @SerializedName("type_id")
    private int typeId;
    @Expose
    @SerializedName("category_id")
    private int categoryId;
    @Expose
    @SerializedName("price")
    private int price;
    @Expose
    @SerializedName("stock")
    private int stock;
    @Expose
    @SerializedName("image_path")
    private String imagePath;
    @Expose
    @SerializedName("image")
    private int image;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*


     "id": 2,
            "name": "Producto prueba 2",
            "description": "producto prueba 2",
            "image": 2,
            "image_path": "https://optimovilapi.herokuapp.com/uploads/1/2022-06/avatar.jpg",
            "stock": 23,
            "price": 233332,
            "category_id": 5,
            "type_id": 2,
            "type": "Lentes",
            "category": "TODOS"
     */
}

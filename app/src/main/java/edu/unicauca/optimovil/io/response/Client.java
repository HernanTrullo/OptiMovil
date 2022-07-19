package edu.unicauca.optimovil.io.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Client {
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("second_last_name")
    private String secondLastName;
    @Expose
    @SerializedName("first_last_nam")
    private String firstLastNam;
    @Expose
    @SerializedName("second_name")
    private String secondName;
    @Expose
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getFirstLastNam() {
        return firstLastNam;
    }

    public void setFirstLastNam(String firstLastNam) {
        this.firstLastNam = firstLastNam;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    {"id": 1,
            "name": "Carlos",
            "second_name": "Andres",
            "first_last_nam": "Chapid",
            "second_last_name": "Inga",
            "email": "carloschapid@unicauca.edu.co",
            "password": "$2y$10$76lcCXzk9kyF4oev01BjCu2hPYRHv6ULJKfx46z6afxunVJt3EjDy",
            "phone_number": "3122229900" }

     */
}

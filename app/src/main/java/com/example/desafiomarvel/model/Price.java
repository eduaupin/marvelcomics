
package com.example.desafiomarvel.model;

import com.google.gson.annotations.Expose;

public class Price {

    @Expose
    private String price;
    @Expose
    private String type;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

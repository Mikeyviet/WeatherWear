package com.bitr8.weatherwear;

import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

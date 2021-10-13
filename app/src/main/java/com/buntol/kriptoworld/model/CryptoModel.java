package com.buntol.kriptoworld.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    @SerializedName("currency")
    public String isim;

    @SerializedName("price")
    public String fiyat;
}

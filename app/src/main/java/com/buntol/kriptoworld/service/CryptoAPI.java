package com.buntol.kriptoworld.service;

import com.buntol.kriptoworld.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    // Get , Post , Update , Delete işlemleri
    //https://api.nomics.com/v1/prices?key=2f3da129c68b82bab4c5d29763920981 lazım olur
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    //URL BASE  ->www.website.com
    //GET -> price?key=....

    @GET("prices?key=2f3da129c68b82bab4c5d29763920981")
    Call <List<CryptoModel>> getData();

}

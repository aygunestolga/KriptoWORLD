package com.buntol.kriptoworld.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;

import com.buntol.kriptoworld.R;
import com.buntol.kriptoworld.adapter.RecyclerViewAdapter;
import com.buntol.kriptoworld.model.CryptoModel;
import com.buntol.kriptoworld.service.CryptoAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



        ArrayList<CryptoModel> cryptoModels;
        private String BASE_URL = "https://api.nomics.com/v1/";
        Retrofit retrofit;
        RecyclerView recyclerView;
        RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      

        recyclerView = findViewById(R.id.recyclerView);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();
    }

        private void  loadData(){

            CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

            Call<List<CryptoModel>> call = (Call<List<CryptoModel>>) cryptoAPI.getData();            // call metoduyla veriyi çekiyoruz
            call.enqueue(new Callback<List<CryptoModel>>() {
                @Override
                public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                    if (response.isSuccessful()){
                        List<CryptoModel> responseList = response.body();
                        cryptoModels = new ArrayList<>(responseList);             // listi array liste çevirdim

                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
                        recyclerView.setAdapter(recyclerViewAdapter);

                  /*      for (CryptoModel cryptoModel : cryptoModels){
                            System.out.println(cryptoModel.isim);
                            System.out.println(cryptoModel.fiyat);
                        }
                   */

                    }
                }

                @Override
                public void onFailure(Call<List<CryptoModel>> call, Throwable t) {

                }
            });

        }


}
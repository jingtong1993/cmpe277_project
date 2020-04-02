package com.example.chatEats.factory.net;

import com.example.chatEats.common.Common;
import com.example.chatEats.factory.Factory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    public static Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();

        Retrofit retrofit = builder.baseUrl(Common.Constance.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();

        return retrofit;
    }
}

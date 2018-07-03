package com.example.knightcube.bakingapp.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public class NetworkClient {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        if(retrofit == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
//                    .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/")
                    .baseUrl("https://gist.githubusercontent.com/knightcube/1b3cd08c4d08207b359ad429ed78a54f/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }
}

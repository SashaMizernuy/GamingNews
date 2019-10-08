package com.example.gamingnews.request;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



@Module
public class NewsServiceModule {


    @Provides
    @Singleton
    public NewsService songService(Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }

//    @Provides
//    @Singleton
//    OkHttpClient provideHttpClient() {
//        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
//        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        client.addInterceptor(interceptor);
//        return client.build();
//    }


    @Provides
    @Singleton
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl()
//                .addConverterFactory(gsonConverterFactory)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        return retrofit.create(NewsService.class);

        return new Retrofit.Builder()
                .baseUrl("http://allcom.lampawork.com/")
                .addConverterFactory(gsonConverterFactory)
                //.client(provideHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
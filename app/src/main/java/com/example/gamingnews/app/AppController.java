package com.example.gamingnews.app;

import android.app.Application;
import android.content.Context;

import com.example.gamingnews.component.AppComponent;

import com.example.gamingnews.component.DaggerAppComponent;
import com.example.gamingnews.request.NewsService;
import com.example.gamingnews.request.NewsServiceModule;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mobile on 26.03.2018.
 */

public class AppController extends Application {
    private NewsService newsService; //retrofit things
    private Scheduler scheduler; //Rx object
    private static AppComponent appComponent;

    private static AppController get(Context context){
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

   /* public NewsService getSongService() {
        if (newsService == null) {
            newsService = NewsServiceModule.newsService();
        }
        return newsService;
    }*/

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    //DI ile buna gerek kalmayacak.Inject edip geçiceksin
    public void setUserService(NewsService newsService) {
        this.newsService = newsService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent buildAppComponent() {
        return DaggerAppComponent.builder().newsServiceModule(new NewsServiceModule()).build();
    }

}
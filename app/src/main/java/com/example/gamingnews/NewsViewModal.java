package com.example.gamingnews;

import android.content.Context;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import com.example.gamingnews.app.AppController;
import com.example.gamingnews.request.NewsService;
import javax.inject.Inject;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.android.schedulers.AndroidSchedulers;



public class NewsViewModal extends Observable {


    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt tvGetNews;
    private List<Result> newsList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    NewsService newsService;


    public NewsViewModal(@NonNull Context context) {
        this.context = context;
        this.newsList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        tvGetNews = new ObservableInt(View.VISIBLE);
    }

    public void getNews(){
        final AppController appController = AppController.create(context);

        appController.getAppComponent().inject(this);
        Disposable disposable = newsService.fetchSongs("api/v1.0/products/?format=json")
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsModal>() {
                    @Override public void accept(NewsModal songModal) throws Exception {

                        for(int i=0;i<songModal.getResults().size();i++) {
                                    NewsModal.Result d=songModal.getResults().get(i);
                            newsList.add(new Result(d.getName(),d.getImage().getUrl()));
                        }
                        setChanged();
                        notifyObservers();
                        progressBar.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                        tvGetNews.set(View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        progressBar.set(View.GONE);
                        userRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);

    }

    public List<Result> getNewsList() {
        return newsList;
    }







//    private void updateSongList(List<Result> songs) {//,List<Result> image
//        Log.d("NewsViewModal","updateSongList fired");
//       // newsList.addAll(songs);
//        //newsList.addAll(image);
//        Log.i("Script","newsList"+newsList.get(0).name);
//        Log.i("Script","newsList"+newsList.get(0));
//        setChanged();
//        notifyObservers();
//    }





    public void reset(){
        Log.d("NewsViewModal","reset fired");
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable=null;
        context=null;

        newsList =null;
    }

}
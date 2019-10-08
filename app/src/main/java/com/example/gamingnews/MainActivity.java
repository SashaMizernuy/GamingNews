package com.example.gamingnews;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.gamingnews.databinding.ActivityMainBinding;
import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {

    private NewsViewModal newsViewModal;
    private ActivityMainBinding mainActivityBinding;
    private SearchView searchView;
    private NewsAdapter newsAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search, menu);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                newsAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                newsAdapter.getFilter().filter(query);
                return true;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setRecycler(mainActivityBinding.recViewSongs);
        setUpObserver(newsViewModal);
        newsAdapter =new NewsAdapter();
        newsViewModal.getNews();

//        mainActivityBinding.tvGetNews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newsViewModal.getNews();
//            }
//        });
    }





    @Override
    public void update(Observable observable, Object o) {
        Log.d("MainActivity","update fired");
        if(observable instanceof NewsViewModal) {
            NewsAdapter newsAdapter = (NewsAdapter) mainActivityBinding.recViewSongs.getAdapter(); //adaptörün hangi recyclerview için set edilceğini
            NewsViewModal songVM = (NewsViewModal) observable; //ViewModal sınıfını observable olarak tanımlıyorsun
            newsAdapter.setSongList(this,songVM.getNewsList()); //viewmodal'daki güncel şarkı listesini çeker
        }
    }

    private void initBinding(){
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main); //layoutu set ediyor.
        newsViewModal = new NewsViewModal(this);
        mainActivityBinding.setNewsViewModal(newsViewModal);
    }

    private void setRecycler(RecyclerView listsongs){
        Log.d("MainActivity","setRecycler fired");
        NewsAdapter newsAdapter = new NewsAdapter();
        listsongs.setAdapter(newsAdapter);
        listsongs.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

//    @Override
//    protected void onDestroy() {
//        Log.d("MainActivity","onDestroy fired");
//        newsViewModal.reset();
//        super.onDestroy();
//    }
}

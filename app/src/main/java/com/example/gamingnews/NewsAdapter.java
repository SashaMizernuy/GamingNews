package com.example.gamingnews;

//import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mobile on 22.03.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.SongAdapterViewHolder> implements Filterable {

    private List<Result> songList;
    private List<Result> songListFiltered;
    private Context context;
    private Result result;

    public NewsAdapter() {
        this.songList = Collections.emptyList();

    }



    @Override
    public SongAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item1, parent, false);
        return new SongAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.SongAdapterViewHolder holder, int position) {
        result = songList.get(position);

        holder.bindData(result);


    }

//    @Override
//    public int getItemCount() {
//        if(songList!=null){
//            //Log.d("getItemCOunt: ",String.valueOf(songList.size()));
//            return songListFiltered.size();
//        }
//        else{
//           // Log.d("getItemCOunt: ","SONGLIST IS NULL");
//           return 0;
//        }
//
//        //return songList.size();
//
//    }

    @Override
    public int getItemCount() {
        if(songListFiltered==null){
            //Log.d("getItemCOunt: ",String.valueOf(songList.size()));
            return 0;
        }
        else{
           // Log.d("getItemCOunt: ","SONGLIST IS NULL");
           return songListFiltered.size();
        }

       // return songList.size();

    }










    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    songListFiltered = songList;
                    Log.i("Script","SongList="+ songList);
                } else {
                    List<Result> filteredList = new ArrayList<>();
                    for (Result result : songList) {
                        Log.i("Script","RESULT="+ result);
                        if (result.name.toUpperCase().contains(charString)|| result.url.toLowerCase().contains(charString)) {
                            filteredList.add(result);
                        }
                    }
                    songListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = songListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                songListFiltered = (List<Result>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }


    public void setSongList(Context context, final List<Result> songList) {
        this.context = context;
        if(this.songList==null) {
            this.songList = songList;
            this.songListFiltered=songList;
            notifyItemChanged(0,songListFiltered.size());
        }else{
            final DiffUtil.DiffResult result=DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return NewsAdapter.this.songList.size();
                }

                @Override
                public int getNewListSize() {
                    return songList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return NewsAdapter.this.songList.get(oldItemPosition).name==songList.get(newItemPosition).name;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Result newResult= NewsAdapter.this.songList.get(oldItemPosition);
                    Result oldResult=songList.get(newItemPosition);
                    return newResult.name==oldResult.name;
                }
            });
            this.songList=songList;
            this.songListFiltered=songList;
            result.dispatchUpdatesTo(this);
        }
        //notifyDataSetChanged();
    }

    public static class SongAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView tvArtistName;
        public ImageView imageView;


        public SongAdapterViewHolder(View view) {
            super(view);
            tvArtistName = view.findViewById(R.id.tvSongNameDynamic);
            imageView = view.findViewById(R.id.thumbnail);
        }

        public void bindData(final Result result) {
            Picasso.get().load(result.url).fit().into(imageView);
            tvArtistName.setText(result.name);
        }

    }

}

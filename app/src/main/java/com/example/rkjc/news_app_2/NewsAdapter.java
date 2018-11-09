package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {
    Context mContext;
    ArrayList<NewsItem> mNewsItems;

    public NewsAdapter(Context context, ArrayList<NewsItem> newsItems){
        this.mContext = context;
        this.mNewsItems = newsItems;
    }

    @Override
    public NewsAdapter.NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }



    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);

        }

        void bind(final int listIndex) {
            title.setText("Title: " + mNewsItems.get(listIndex).getTitle());
            description.setText("Description: " + mNewsItems.get(listIndex).getDescription());
            date.setText("Date: " + mNewsItems.get(listIndex).getPublishedAt());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String urlString = mNewsItems.get(getAdapterPosition()).getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlString));
                    intent.putExtra("urlString", urlString);
                    mContext.startActivity(intent);
                }
            });
        }
    }

}

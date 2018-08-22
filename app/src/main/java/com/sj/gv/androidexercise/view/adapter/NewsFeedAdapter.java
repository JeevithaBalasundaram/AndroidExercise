package com.sj.gv.androidexercise.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sj.gv.androidexercise.R;
import com.sj.gv.androidexercise.model.NewsFeed;

import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = NewsFeedAdapter.class.getSimpleName();
    private Context mContext;
    private List<NewsFeed> mNewsList;

    public NewsFeedAdapter(Context context){
        this.mContext = context;
    }
    public void setFeedsList(List<NewsFeed> newsList){
        this.mNewsList = newsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView newsHeadline;
        private TextView newsDescription;
        private ImageView newsImage;
        public ViewHolder(View itemView){
            super(itemView);
            newsHeadline = itemView.findViewById(R.id.news_title);
            newsDescription = itemView.findViewById(R.id.news_description);
            newsImage = itemView.findViewById(R.id.news_image);

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        final View itemView = LayoutInflater.from(mContext).inflate(R.layout.news_layout, parent, false);
//        ViewHolder viewHolder = new ViewHolder(itemView);
//        return viewHolder;

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_layout, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public String getImg(NewsFeed feed) {
        if ((feed.getImageUrl()) == null) return null;
        String url = feed.getImageUrl();
        return url;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mViewHolder = (ViewHolder)holder;
        NewsFeed feed = mNewsList.get(position);
        String title = feed.getTitle();
        String description = feed.getDescription();
        String imageUrl = getImg(feed);
        mViewHolder.newsDescription.setText("");
        mViewHolder.newsHeadline.setText("");
        if(title!= null)
            mViewHolder.newsHeadline.setText(title);
//        else
//            mViewHolder.newsHeadline.setVisibility(View.INVISIBLE);
        if(description!= null)
            mViewHolder.newsDescription.setText(description);
//        else
//            mViewHolder.newsDescription.setVisibility(View.INVISIBLE);
        if(imageUrl != null) {
            Glide.with(mContext)
                    .load(imageUrl)
                    .placeholder(R.drawable.switch_toggr_android)
                    .error(R.drawable.ic_launcher_background)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontTransform()
                    .dontAnimate()
                    .into(mViewHolder.newsImage);
        }
        else {
            mViewHolder.newsImage.setVisibility(View.INVISIBLE);
            Glide.clear(mViewHolder.newsImage);
        }
    }
}

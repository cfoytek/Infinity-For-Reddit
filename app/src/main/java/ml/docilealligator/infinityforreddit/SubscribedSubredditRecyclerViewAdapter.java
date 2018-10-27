package ml.docilealligator.infinityforreddit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class SubscribedSubredditRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<SubscribedSubredditData> mSubscribedSubredditData;
    private RequestManager glide;
    private OnItemClickListener mOnItemClickListener;

    interface OnItemClickListener {
        void onClick();
    }

    SubscribedSubredditRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener) {
        mContext = context.getApplicationContext();
        glide = Glide.with(context.getApplicationContext());
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SubredditViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subscribed_subreddit, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.getAdapterPosition() >= 0) {
                    mOnItemClickListener.onClick();
                    Intent intent = new Intent(mContext, ViewSubredditDetailActivity.class);
                    intent.putExtra(ViewSubredditDetailActivity.EXTRA_SUBREDDIT_NAME_KEY, mSubscribedSubredditData.get(viewHolder.getAdapterPosition()).getName());
                    intent.putExtra(ViewSubredditDetailActivity.EXTRA_SUBREDDIT_VALUE_KEY, mSubscribedSubredditData.get(viewHolder.getAdapterPosition()).getId());
                    intent.putExtra(ViewSubredditDetailActivity.EXTRA_QUERY_BY_ID_KEY, true);
                    mContext.startActivity(intent);
                }
            }
        });
        if(!mSubscribedSubredditData.get(i).getIconUrl().equals("")) {
            glide.load(mSubscribedSubredditData.get(i).getIconUrl()).into(((SubredditViewHolder) viewHolder).iconCircleImageView);
        } else {
            glide.load(R.drawable.subreddit_default_icon).into(((SubredditViewHolder) viewHolder).iconCircleImageView);
        }
        ((SubredditViewHolder) viewHolder).subredditNameTextView.setText(mSubscribedSubredditData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        if(mSubscribedSubredditData != null) {
            return mSubscribedSubredditData.size();
        }
        return 0;
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        glide.clear(((SubredditViewHolder) holder).iconCircleImageView);
    }

    void setSubscribedSubreddits(List<SubscribedSubredditData> subscribedSubreddits){
        mSubscribedSubredditData = subscribedSubreddits;
        notifyDataSetChanged();
    }


    private class SubredditViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView iconCircleImageView;
        private final TextView subredditNameTextView;

        public SubredditViewHolder(View itemView) {
            super(itemView);
            iconCircleImageView = itemView.findViewById(R.id.subreddit_icon_circle_image_view_item_subscribed_subreddit);
            subredditNameTextView = itemView.findViewById(R.id.subreddit_name_text_view_item_subscribed_subreddit);
        }
    }
}
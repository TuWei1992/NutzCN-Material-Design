package net.wendal.nutzbook.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.wendal.nutzbook.R;
import net.wendal.nutzbook.model.entity.TopicSimple;
import net.wendal.nutzbook.ui.activity.TopicActivity;
import net.wendal.nutzbook.ui.activity.UserDetailActivity;
import net.wendal.nutzbook.util.FormatUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailItemAdapter extends RecyclerView.Adapter<UserDetailItemAdapter.ViewHolder> {

    private Activity activity;
    private LayoutInflater inflater;
    private List<TopicSimple> topicList;

    public UserDetailItemAdapter(Activity activity, @NonNull List<TopicSimple> topicList) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        this.topicList = topicList;
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.activity_user_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user_detail_item_img_avatar)
        protected ImageView imgAvatar;

        @Bind(R.id.user_detail_item_tv_title)
        protected TextView tvTitle;

        @Bind(R.id.user_detail_item_tv_login_name)
        protected TextView tvLoginName;

        @Bind(R.id.user_detail_item_tv_last_reply_time)
        protected TextView tvLastReplyTime;

        private TopicSimple topic;

        protected ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void update(int position) {
            topic = topicList.get(position);

            tvTitle.setText(topic.getTitle());
            Picasso.with(activity).load(topic.getAuthor().getAvatarUrl()).placeholder(R.drawable.image_placeholder).into(imgAvatar);
            tvLoginName.setText(topic.getAuthor().getLoginName());
            tvLastReplyTime.setText(FormatUtils.getRecentlyTimeText(topic.getLastReplyAt()));
        }

        @OnClick(R.id.user_detail_item_img_avatar)
        protected void onBtnAvatarClick() {
            UserDetailActivity.openWithTransitionAnimation(activity, topic.getAuthor().getLoginName(), imgAvatar);
        }

        @OnClick(R.id.user_detail_item_btn_item)
        protected void onBtnItemClick() {
            TopicActivity.open(activity, topic.getId());
        }

    }

}

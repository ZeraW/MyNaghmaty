package com.example.abdallah.chatdemo.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.abdallah.chatdemo.Models.Conversation;
import com.example.abdallah.chatdemo.Models.User;
import com.example.abdallah.chatdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder>  {
    Conversation data;
    User currentUser;
    Context context;
    int  LastPosition=-1;
    RecyclerViewClickListener ClickListener ;
    public MessagesAdapter(){}
    public MessagesAdapter(User user,Conversation data, Context context){
        this.data =new Conversation();
        this.data = data;
        this.context=context;
        currentUser=user;
    }


    public void setClickListener(RecyclerViewClickListener clickListener){
       this.ClickListener= clickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_messge,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        if(currentUser.getUserID().equals(data.getMessageList().get(position).getSender().getUserID())) {
            String ss = data.getMessageList().get(position).getBody();
            holder.textView.setText(ss);
            holder.textView.setTextColor(Color.BLACK);

            if(currentUser.getUserID().equals("admin"))
                Picasso.with(context).load(R.drawable.gms_icon).placeholder(R.drawable.gms_icon)
                        .error(R.drawable.gms_icon).into(holder.imageView);
            else
                Picasso.with(context).load(currentUser.getProfileImg()).placeholder(R.drawable.facebook_circle)
                     .error(R.drawable.facebook_circle).into(holder.imageView);
            //setAnimation(holder.cardView, position);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                holder.cardView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
            //chat_gms 0xffffff
                holder.textCardView.setBackgroundResource(R.drawable.card_send);

        }else{
            String ss =data.getMessageList().get(position).getBody();
            holder.textView.setText(ss);
            holder.textView.setTextColor(Color.BLACK);


            if(data.getMessageList().get(position).getSender().getUserID().equals("admin"))
                Picasso.with(context).load(R.drawable.gms_icon).placeholder(R.drawable.gms_icon)
                        .error(R.drawable.gms_icon).into(holder.imageView);
            else
                Picasso.with(context).load(data.getMessageList().get(position).getSender().getProfileImg()).placeholder(R.drawable.facebook_circle)
                        .error(R.drawable.facebook_circle).into(holder.imageView);
            //setAnimation(holder.cardView, position);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                holder.cardView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
           /* RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_START);
            params.setMargins(5,5,5,5);
            holder.cardView.setLayoutParams(params);*/
            //chat_client 0xffffff
            //holder.textCardView.setBackgroundColor(0x527db0d3);
                holder.textCardView.setBackgroundResource(R.drawable.card_receive);

        }

    }

    @Override
    public int getItemCount() {
        if(data==null || data.getMessageList()==null)
            return 0;
        return  data.getMessageList().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        CircleImageView imageView;
        RelativeLayout cardView;
        CardView textCardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView=(TextView)itemView.findViewById(R.id.msg_body_txt);
            imageView=(CircleImageView) itemView.findViewById(R.id.user_img);
            cardView=(RelativeLayout) itemView.findViewById(R.id.card);
            textCardView=(CardView) itemView.findViewById(R.id.text_card);
        }

        @Override
        public void onClick(View view) {
            if(ClickListener!=null)
            ClickListener.ItemClicked(view ,getAdapterPosition());
        }

        public void clearAnimation()
        {
            cardView.clearAnimation();
        }
    }

    public interface RecyclerViewClickListener
    {

        public void ItemClicked(View v, int position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {

        if (position > LastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            LastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
             holder.clearAnimation();
    }



}


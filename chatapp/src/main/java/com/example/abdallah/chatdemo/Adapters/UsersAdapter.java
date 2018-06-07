package com.example.abdallah.chatdemo.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.abdallah.chatdemo.Models.User;
import com.example.abdallah.chatdemo.R;
import com.example.abdallah.chatdemo.Utils.Constants;
import com.example.abdallah.chatdemo.Utils.MySharedPreferences;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder>  {
    ArrayList<User> data;
    Context context;
    int  LastPosition=-1;
    RecyclerViewClickListener ClickListener ;
    public UsersAdapter(){}
    public UsersAdapter(ArrayList<User> data, Context context){
        this.data =new ArrayList<>();
        this.data = data;
        this.context=context;
    }


    public void setClickListener(RecyclerViewClickListener clickListener){
       this.ClickListener= clickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String flag= MySharedPreferences.getUserSetting(Constants.unreadMessages+data.get(position).getUserID());
        if (flag != null && flag.equals("1")) {
            holder.relativeLayout.setVisibility(View.VISIBLE);
        }else{
            holder.relativeLayout.setVisibility(View.INVISIBLE);
        }

        String ss=data.get(position).getName();
        holder.textView.setText(ss);
        Picasso.with(context).load(data.get(position).getProfileImg()).placeholder(R.drawable.facebook_circle)
                .error(R.drawable.facebook_circle).into(holder.imageView);
        setAnimation(holder.cardView,position);

    }

    @Override
    public int getItemCount() {
        if(data==null)
            return 0;
        return  data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        CircleImageView imageView;
        CardView cardView;
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView=(TextView)itemView.findViewById(R.id.user_name_txt);
            imageView=(CircleImageView) itemView.findViewById(R.id.user_img);
            cardView=(CardView) itemView.findViewById(R.id.card);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.msg_num_view);
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


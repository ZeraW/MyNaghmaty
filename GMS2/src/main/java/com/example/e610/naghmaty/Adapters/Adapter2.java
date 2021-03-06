package com.example.e610.naghmaty.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.e610.naghmaty.Models.CategoriesResponse.CategoriesResponse;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.MyViewHolder>  {
    final String basicImgUrl="http://gms-sms.com:89";
    CategoriesResponse categoriesResponse;
    Context context;
    int  LastPosition=-1;
    int id=0;
    RecyclerViewClickListener ClickListener ;
    public Adapter2(){}
    public Adapter2(int id , CategoriesResponse categoriesResponse, Context context){
        this.id=id;
        this.categoriesResponse =new CategoriesResponse();
        this.categoriesResponse = categoriesResponse;
        this.context=context;
    }

    public CategoriesResponse swapCursor(CategoriesResponse cursor) {
        if (categoriesResponse == cursor) {
            return null;
        }
        CategoriesResponse oldCursor = categoriesResponse;
        this.categoriesResponse = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }


    public void setClickListener(RecyclerViewClickListener clickListener){
        this.ClickListener= clickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gallary,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String name="";
        int langType= SharedPrefUtils.getSharedPrefValue(context);

        if(langType==1)
            name=categoriesResponse.data.get(id).products.data.get(position).arName;
        else
            name=categoriesResponse.data.get(id).products.data.get(position).enName;


        //holder.textView.setText(name);
        String str=basicImgUrl+categoriesResponse.data.get(id).products.data.get(position).image;
        //String str= ApiClient.BASE_URL+categoriesResponse.data.get(id).products.data.get(position).image;


        Picasso.with(context).load(str).placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(holder.imageView);

        setAnimation(holder.cardView,position);

    }


    @Override
    public int getItemCount() {
        if(categoriesResponse==null||categoriesResponse.data==null ||
                categoriesResponse.data.get(id).products==null || categoriesResponse.data.get(id).products.data==null)
            return 0;
        return categoriesResponse.data.get(id).products.data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //TextView textView;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //textView=(TextView)itemView.findViewById(R.id.textView);
            imageView=(ImageView) itemView.findViewById(R.id.img);
            cardView=(CardView) itemView.findViewById(R.id.card);
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


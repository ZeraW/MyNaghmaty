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
import android.widget.TextView;

import com.example.e610.naghmaty.Models.Products.Products;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>  {
    final String basicImgUrl="http://gms-sms.com:89";
    Products  products;
    Context context;
    int  LastPosition=-1;
    RecyclerViewClickListener ClickListener ;
    public ProductsAdapter(){}
    public ProductsAdapter(Products products, Context context){
        this.products =new Products();
        this.products = products;
        this.context=context;
    }

    public Products swapCursor(Products cursor) {
        if (products == cursor) {
            return null;
        }
        Products oldCursor = products;
        this.products = cursor;
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_products,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String name="";
        int langType= SharedPrefUtils.getSharedPrefValue(context);

        if(langType==1)
            name=products.getData().get(position).getArName();
        else
            name=products.getData().get(position).getEnName();

        holder.textView.setText(name);
        String str=basicImgUrl+products.getData().get(position).getLogo();

        Picasso.with(context).load(str).placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(holder.imageView);

        setAnimation(holder.cardView,position);

    }

    @Override
    public int getItemCount() {
        if(products==null||products.getData()==null)
            return 0;
        return products.getData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView=(TextView)itemView.findViewById(R.id.textView);
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


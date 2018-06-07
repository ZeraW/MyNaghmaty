package com.example.fahmy.naghmaty.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.fahmy.naghmaty.Activities.DetailsProjectsActivity;
import com.example.fahmy.naghmaty.Models.ModelServices;
import com.example.fahmy.naghmaty.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Fahmy on 12/5/2017.
 */

public class AdapterRecyclerService extends RecyclerView.Adapter<AdapterRecyclerService.Myholder> {
    Context context;
    ArrayList<Object> object;
    ArrayList<String> detailPhoto = new ArrayList<>();
    int x;
    View row;

    public AdapterRecyclerService(Context context, ArrayList<Object> object, int x, ArrayList<String> detailPhoto) {
        this.context = context;
        this.object = object;
        this.x = x;
        this.detailPhoto = detailPhoto;

    }

    @Override
    public AdapterRecyclerService.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (x == 1) {
             row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_service, parent, false);
        } else  {

             row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_project, parent, false);

        }

        return new AdapterRecyclerService.Myholder(row);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerService.Myholder holder, final int position) {


        object.get(position);
        final ModelServices model = (ModelServices) object.get(position);
     /*   if (((x == 1) || (x == 4)) && ((position == 3))) {
            holder.title.setText("أنتاج المحتوى البصري... ");

        } else {*/
        holder.title.setText(model.getTitle());

    //}

        Picasso.with(context).load(model.getLogo()).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(holder.imageview);
        if ((x == 1)  || (x == 3)) {

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailsProjectsActivity.class);


                    try {

                        intent.putExtra("pos", position);
                        intent.putExtra("Position", x);
                        intent.putExtra("Modeltext", model.getText());
                        intent.putExtra("Modeltitle", model.getTitle());
                        intent.putExtra("Modellogo", detailPhoto.get(position));

                    }catch (Exception e){
                        e.getMessage();
                    }
/*
                    Log.e("Menna", detailPhoto.get(position));
*/
                    context.startActivity(intent);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
            return object.size();


    }

    class Myholder extends RecyclerView.ViewHolder {
        RelativeLayout linearLayout;
        TextView title, text;
        ImageView imageview;


        public Myholder(View itemView) {
            super(itemView);
            linearLayout = (RelativeLayout) itemView.findViewById(R.id.frame);
            title = (TextView) itemView.findViewById(R.id.text_row);
            imageview = (ImageView) itemView.findViewById(R.id.image_row);

        }
    }
}

package com.example.fahmy.naghmaty.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fahmy.naghmaty.Models.ModelOurTeam;
import com.example.fahmy.naghmaty.R;
import com.example.e610.naghmaty.Utils.ContactUsUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Fahmy on 12/5/2017.
 */

public class AdapterRecyclerOurTeam extends RecyclerView.Adapter<AdapterRecyclerOurTeam.Myholder> {
    Context context;
    ArrayList<Object> object;
    int x;
    View row;

    public AdapterRecyclerOurTeam(Context context, ArrayList<Object> object) {
        this.context = context;
        this.object = object;


    }

    @Override
    public AdapterRecyclerOurTeam.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {


        row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_our_team, parent, false);


        return new AdapterRecyclerOurTeam.Myholder(row);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerOurTeam.Myholder holder, final int position) {
        Log.v("ObjectOur", "" + object.size());
        final ModelOurTeam modelOurTeam = (ModelOurTeam) object.get(position);

        holder.title.setText(modelOurTeam.getTitle());
        holder.text.setText(modelOurTeam.getText());

        //Picasso.with(context).load(modelOurTeam.getLogo()).into(holder.imageview);

        if((position==2)){
            holder.imagecall.setVisibility(View.VISIBLE);
            holder.Linearcall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*
                    Toast.makeText(context, "Hi From here"+position, Toast.LENGTH_SHORT).show();
*/

                    ContactUsUtils contactUsUtils=new ContactUsUtils(context);
                    contactUsUtils.phoneCall(modelOurTeam.getNumber());

             /*   Log.v("TAG",modelOurTeam.getNumber());
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+modelOurTeam.getNumber()));

                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);*/
                }
            });

        }
        else{
            holder.imagecall.setVisibility(View.INVISIBLE);

        }
        if(modelOurTeam.getGender().equals("male")){
            Picasso.with(context).load(R.drawable.boy).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(holder.imageview);
        }
        else
        {
            Picasso.with(context).load(R.drawable.girl).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(holder.imageview);


        }



    }

    @Override
    public int getItemCount() {
        return object.size();


    }

    class Myholder extends RecyclerView.ViewHolder {
        RelativeLayout linearLayout;
        LinearLayout Linearcall;
        TextView title, text;
        ImageView imageview,imagecall;


        public Myholder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_employee);
            Linearcall = (LinearLayout) itemView.findViewById(R.id.call);
            text = (TextView) itemView.findViewById(R.id.text_employee2);
            imageview = (ImageView) itemView.findViewById(R.id.img_employee);
            imagecall = (ImageView) itemView.findViewById(R.id.call_image);



        }
    }
}

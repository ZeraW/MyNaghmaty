package com.example.e610.naghmaty.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.e610.naghmaty.Activities.GalleryActivity;
import com.example.e610.naghmaty.Activities.SubDirectoryActivity;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;



public class ViewPagerFragment extends Fragment {



    View view;
    String imgUrl = "";
    ImageView imageView;
    int pageNum;

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* if(!imgUrl.contains("e610")) {
            view = inflater.inflate(R.layout.row_gallary, container, false);
              imageView=(ImageView)view.findViewById(R.id.img);
        }else {
            view = inflater.inflate(R.layout.row_about_us, container, false);
              imageView=(ImageView)view.findViewById(R.id.img1);
        }*/

        view = inflater.inflate(R.layout.row_gallary, container, false);
        imageView = (ImageView) view.findViewById(R.id.img);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!imgUrl.contains("e610") && !imgUrl.contains("child")) {
                    Intent intent = new Intent(getActivity(), GalleryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "gallery");
                    bundle.putInt("index", pageNum);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else if (imgUrl.contains("child")) {
                    Intent intent = new Intent(getActivity(), SubDirectoryActivity.class);
                    Bundle bundle = getArguments();
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                    //Toast.makeText(getActivity(),"^^",Toast.LENGTH_SHORT).show();
                } else {
                   /* Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "gallery");
                    bundle.putInt("index", pageNum);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);*/
                }

            }
        });

        if (!imgUrl.contains("e610")) {
            if (imgUrl.equals(""))
                imgUrl = "asd";
            Picasso.with(getActivity()).load(imgUrl).placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(imageView);
        } else {
            int langType = SharedPrefUtils.getSharedPrefValue(getActivity());
            if (langType == 1) {
                if (pageNum == 0) {
                    Picasso.with(getActivity()).load(R.drawable.about_us_ar).placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(imageView);
                }
                /* else if (pageNum == 1) {
                    Picasso.with(getActivity()).load(R.drawable.ar3).placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).into(imageView);
                } else if (pageNum == 2) {
                    Picasso.with(getActivity()).load(R.drawable.ar2).placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).into(imageView);
                }*/
            } else {
                if (pageNum == 0) {
                    Picasso.with(getActivity()).load(R.drawable.about_us_en).placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).fit().centerInside().into(imageView);
                }
                /*else if (pageNum == 1) {
                    Picasso.with(getActivity()).load(R.drawable.en3).placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).into(imageView);
                } else if (pageNum == 2) {
                    Picasso.with(getActivity()).load(R.drawable.en2).placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder).into(imageView);
                }*/
            }
        }
        Log.d("img_url", imgUrl);

        return view;
    }

}

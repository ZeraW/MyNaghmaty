package com.example.e610.naghmaty.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;


import java.util.ArrayList;
import java.util.List;


public class AboutUsFragment extends Fragment {

    private ViewPager viewPager;
    TabLayout tabLayout;
    private View view;
    FrameLayout frameLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_about_us, container, false);
        frameLayout=(FrameLayout) view.findViewById(R.id.frameLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        Bundle bundle=getArguments();
        String type=bundle.getString("type");
        index=bundle.getInt("index",0);
        if(!type.equals("main")){
            FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(layoutParams);
        }else{
            frameLayout=(FrameLayout) view.findViewById(R.id.frameLayout);
        }
        //getLocalData();

        viewPager=setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.setCurrentItem(index);

        countDownTimer = new CountDownTimer(360000000, 10000){
           // int i = 0;
              int i =index;

            public void onTick(long millisUntilFinished) {

                if (i < 3) {
                    i++;
                    viewPager.setCurrentItem(i);
                }else{
                    i = 0;
                    viewPager.setCurrentItem(i);
                }


            }

            public void onFinish() {
                countDownTimer=getCountDownTimerInstance();
                countDownTimer.start();
            }
        }.start();


        TextView textView= view.findViewById(R.id.about_us_title);
        int langType= SharedPrefUtils.getSharedPrefValue(getActivity());
        if(langType==1){
            textView.setText("عن الشركه");
        }else{
            textView.setText("About Us");
        }

        return view ;
    }

    int index=0;
    CountDownTimer countDownTimer;
    private CountDownTimer getCountDownTimerInstance(){

        return new CountDownTimer(360000000, 6000) {
            int i = 0;

            public void onTick(long millisUntilFinished) {
                if (i <3) {
                    viewPager.setCurrentItem(i);
                    i++;
                } else
                    i = 0;

            }

            public void onFinish() {
                countDownTimer=getCountDownTimerInstance();
                countDownTimer.start();
            }
        };

    }







    final String basicImgUrl="android.resource://com.example.e610.gmsapp2/";
    ViewPagerAdapter adapter;
    private ViewPager setupViewPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        int i = 0;
        ViewPagerFragment fragment = new ViewPagerFragment();

        fragment.setImgUrl(basicImgUrl);
        fragment.setPageNum(i);
        adapter.addFragment(fragment,  "");

       /* i = 1;
        ViewPagerFragment fragment1 = new ViewPagerFragment();
        fragment1.setImgUrl(basicImgUrl);
        fragment1.setPageNum(i);
        adapter.addFragment(fragment1, "");

        i = 2;
        ViewPagerFragment fragment2 = new ViewPagerFragment();
        fragment2.setImgUrl(basicImgUrl);
        fragment2.setPageNum(i);
        adapter.addFragment(fragment2,  "");*/

        viewPager.setAdapter(adapter);

        return viewPager;
    }

      /*
    class ViewPagerAdapter extends FragmentPagerAdapter {
        this line cause logic error "display fragments in view pager when i reset it in wrong way"
        */

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            index=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}

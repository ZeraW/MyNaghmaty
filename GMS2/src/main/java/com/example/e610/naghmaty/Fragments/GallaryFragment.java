package com.example.e610.naghmaty.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.e610.naghmaty.Data.CompanyCategory;
import com.example.e610.naghmaty.Data.LocalData;
import com.example.e610.naghmaty.Models.Gallery.Gallery;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.Callbacks;
import com.example.e610.naghmaty.Utils.JsonParsingUtils;
import com.example.e610.naghmaty.Utils.NetworkState;
import com.example.e610.naghmaty.Utils.RetrofitLib.GetCompanyGallery;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GallaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GallaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GallaryFragment extends Fragment   {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GallaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GallaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GallaryFragment newInstance(String param1, String param2) {
        GallaryFragment fragment = new GallaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private ViewPager viewPager;
    TabLayout tabLayout;
    private View view;
    RelativeLayout frameLayout;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_gallary, container, false);
        View view1=inflater.inflate(R.layout.row_gallary, container, false);
        CardView cardView=(CardView) view1.findViewById(R.id.card);
        frameLayout=(RelativeLayout) view.findViewById(R.id.frameLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager1);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        textView = (TextView) view.findViewById(R.id.gallery_title);
        Bundle bundle=getArguments();
        String type=bundle.getString("type");
        index=bundle.getInt("index",0);
        if(!type.equals("main")){
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(layoutParams);
            frameLayout.setBackground(getResources().getDrawable(R.color.background));
            textView.setBackground(getResources().getDrawable(R.color.background));
            //frameLayout.setBackgroundColor(getResources().getColor(R.color.background));
            //cardView.setCardBackgroundColor(getResources().getColor(R.color.background));
        }else{
            frameLayout=(RelativeLayout) view.findViewById(R.id.frameLayout);
            textView = (TextView) view.findViewById(R.id.gallery_title);
            int langType= SharedPrefUtils.getSharedPrefValue(getActivity());
            if(langType==1){
                textView.setText("المعرض");
            }else{
                textView.setText("Gallery");
            }
        }
        getRemoteData();

        return view;
    }

    int index=0;
    CountDownTimer countDownTimer;
    private CountDownTimer getCountDownTimerInstance(){

     return new CountDownTimer(360000000, 10000) {
            int i = 0;

            public void onTick(long millisUntilFinished) {
                if (i < gallery.getData().size()) {
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

    boolean aBoolean=false;
    Gallery gallery;
    private void getRemoteData(){
        if(NetworkState.ConnectionAvailable(getActivity())){
            GetCompanyGallery  getCompanyGallery=new GetCompanyGallery();
            getCompanyGallery.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {

                    if(isDataloaded)
                        return;

                    isDataloaded=true;

                    try{
                        gallery=(Gallery)obj;
                        viewPager=setupViewPager(viewPager,gallery);
                        tabLayout.setupWithViewPager(viewPager, true);
                        viewPager.setCurrentItem(index);


                        String jsonStr= JsonParsingUtils.getJson(gallery);
                        CompanyCategory companyCategory=new CompanyCategory();
                        companyCategory.setId("gallery");
                        companyCategory.setTitle("gallery");
                        companyCategory.setJsonContent(jsonStr);
                        LocalData localData=new LocalData(getActivity(),LocalData.insert,companyCategory);
                        localData.addCallBacks(new Callbacks() {
                            @Override
                            public void OnSuccess(Object obj) {

                            }

                            @Override
                            public void OnFailure(String error) {

                            }
                        });
                        localData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                        countDownTimer = new CountDownTimer(360000000, 10000) {
                            //int i =0;
                            int i = index;
                            public void onTick(long millisUntilFinished) {

                                if (i < gallery.getData().size()-1) {
                                    if(!aBoolean) {
                                        aBoolean=true;
                                        return;
                                    }
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

                    }catch (Exception e){
                        Log.e("asd",e.getMessage(),e);
                    }
                }

                @Override
                public void OnFailure(String error) {

                }
            });

            getCompanyGallery.start();
            checkInterntetWeakness();

        }else {
            CompanyCategory companyCategory=new CompanyCategory();
            companyCategory.setId("");
            companyCategory.setTitle("gallery");
            companyCategory.setJsonContent("");
            LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
            localData.addCallBacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    try {
                        CompanyCategory category = (CompanyCategory) obj;
                        gallery = (Gallery) JsonParsingUtils.getObject(category.getJsonContent(), Gallery.class);
                        viewPager = setupViewPager(viewPager, gallery);
                        tabLayout.setupWithViewPager(viewPager, true);
                        viewPager.setCurrentItem(index);
                    }catch (Exception e){}
                }

                @Override
                public void OnFailure(String error) {

                }
            });
            localData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }



    private boolean isDataloaded=false;
    private void checkInterntetWeakness(){
        AsyncTask asyncTask=new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Object o) {
                if(isDataloaded)
                    return;

                CompanyCategory companyCategory=new CompanyCategory();
                companyCategory.setId("");
                companyCategory.setTitle("gallery");
                companyCategory.setJsonContent("");
                LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
                localData.addCallBacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        try {
                            CompanyCategory category = (CompanyCategory) obj;
                            gallery = (Gallery) JsonParsingUtils.getObject(category.getJsonContent(), Gallery.class);
                            viewPager = setupViewPager(viewPager, gallery);
                            tabLayout.setupWithViewPager(viewPager, true);
                            viewPager.setCurrentItem(index);
                        }catch (Exception e){}
                    }

                    @Override
                    public void OnFailure(String error) {

                    }
                });
                localData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                isDataloaded=true;
                super.onPostExecute(o);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    final String basicImgUrl="http://gms-sms.com:89";
    ViewPagerAdapter adapter;
    private ViewPager setupViewPager(ViewPager viewPager, Gallery gallery) {

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        for (int i =0; i <gallery.getData().size() ; i++) {
            ViewPagerFragment fragment = new ViewPagerFragment();
            fragment.setImgUrl(basicImgUrl+ gallery.getData().get(i).getImage());
            fragment.setPageNum(i);
            adapter.addFragment(fragment, "");
        }
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

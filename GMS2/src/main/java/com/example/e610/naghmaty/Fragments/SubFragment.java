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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.e610.naghmaty.Data.CompanyCategory;
import com.example.e610.naghmaty.Data.LocalData;
import com.example.e610.naghmaty.Models.SubDirectory.SubDirectory;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.Callbacks;
import com.example.e610.naghmaty.Utils.JsonParsingUtils;
import com.example.e610.naghmaty.Utils.NetworkState;
import com.example.e610.naghmaty.Utils.RetrofitLib.GetCompanySubDirectory;
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
public class SubFragment extends Fragment   {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public SubFragment() {
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
        view =inflater.inflate(R.layout.fragment_sub, container, false);
        frameLayout=(RelativeLayout) view.findViewById(R.id.frameLayout);
        viewPager=(ViewPager)view.findViewById(R.id.viewPager2);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        textView = (TextView) view.findViewById(R.id.sub_text);
        Bundle bundle=getArguments();
        String type=bundle.getString("type");
        index=bundle.getInt("index",0);
        if(!type.equals("main")){
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(layoutParams);
        }else{
            frameLayout=(RelativeLayout) view.findViewById(R.id.frameLayout);
        }
        getRemoteData();

        int langType= SharedPrefUtils.getSharedPrefValue(getActivity());
        if(langType==1){
            textView.setText("الشركات الفرعيه");
        }else{
            textView.setText("Subsidiaries");
        }

        return view;
    }

    int index=0;
    CountDownTimer countDownTimer;
    private CountDownTimer getCountDownTimerInstance(){

     return new CountDownTimer(360000000, 10000) {
            int i = 0;

            public void onTick(long millisUntilFinished) {
                if (i < subDirectory.getData().size()) {
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

    SubDirectory  subDirectory;
    private void getRemoteData(){
        if(NetworkState.ConnectionAvailable(getActivity())){
            GetCompanySubDirectory  getCompanySubDirectory=new GetCompanySubDirectory();
            getCompanySubDirectory.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    try{
                        if(isDataloaded)
                            return;

                        isDataloaded=true;
                        subDirectory=(SubDirectory)obj;
                        viewPager=setupViewPager(viewPager,subDirectory);
                        tabLayout.setupWithViewPager(viewPager, true);
                        viewPager.setCurrentItem(index);

                        String jsonStr= JsonParsingUtils.getJson(subDirectory);
                        CompanyCategory companyCategory=new CompanyCategory();
                        companyCategory.setId("subDirectory");
                        companyCategory.setTitle("subDirectory");
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

                                if (i < subDirectory.getData().size()-1) {
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

            getCompanySubDirectory.start();
            checkInterntetWeakness();

        }else {
            CompanyCategory companyCategory=new CompanyCategory();
            companyCategory.setId("");
            companyCategory.setTitle("subDirectory");
            companyCategory.setJsonContent("");
            LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
            localData.addCallBacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    try {
                        CompanyCategory category = (CompanyCategory) obj;
                        subDirectory = (SubDirectory) JsonParsingUtils.getObject(category.getJsonContent(), SubDirectory.class);
                        viewPager = setupViewPager(viewPager, subDirectory);
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
                companyCategory.setTitle("subDirectory");
                companyCategory.setJsonContent("");
                LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
                localData.addCallBacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        try {
                            CompanyCategory category = (CompanyCategory) obj;
                            subDirectory = (SubDirectory) JsonParsingUtils.getObject(category.getJsonContent(), SubDirectory.class);
                            viewPager = setupViewPager(viewPager, subDirectory);
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
    String f_url="1421627937856967";
    //String f_url="https://www.facebook.com/TeleOneEg/";
    //String f_url2="https://www.facebook.com/GlobalMediaServicesGms/";
    String f_url2="382277495185350";
    //String f_url3="https://www.facebook.com/MRMUSICOFFICIALTV/";
    String f_url3="706091392821895";
    String w_url="http://www.teleone.tech/";
    String w_url2="http://www.gms-group.company/";
    String w_url3="none";

    private ViewPager setupViewPager(ViewPager viewPager, SubDirectory subDirectory) {

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        for (int i =0; i <subDirectory.getData().size() ; i++) {
            ViewPagerFragment fragment = new ViewPagerFragment();
            String imgUrl=basicImgUrl+ subDirectory.getData().get(i).getLogo();
            Bundle bundle=new Bundle();
            bundle.putSerializable("data",subDirectory.getData().get(i));
            fragment.setArguments(bundle);
            fragment.setImgUrl(imgUrl);
            Log.d("subImgUrl",imgUrl);
            fragment.setPageNum(i);

            switch (i){
                case 0:
                    bundle.putString("f_url",f_url2);
                    bundle.putString("w_url",w_url2);
                    break;
                case 1:
                    bundle.putString("f_url",w_url3);
                    bundle.putString("w_url",w_url3);
                    break;
                case 2:
                    bundle.putString("f_url",f_url3);
                    bundle.putString("w_url",w_url3);
                    break;
                case 3:
                    bundle.putString("f_url",f_url);
                    bundle.putString("w_url",w_url);
                    break;
                default:
                    bundle.putString("f_url",w_url3);
                    bundle.putString("w_url",w_url3);
                    break;
            }

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

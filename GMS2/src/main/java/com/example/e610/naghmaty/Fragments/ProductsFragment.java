package com.example.e610.naghmaty.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.e610.naghmaty.Activities.DetailedActivity;
import com.example.e610.naghmaty.Adapters.ProductsAdapter;
import com.example.e610.naghmaty.Data.CompanyCategory;
import com.example.e610.naghmaty.Data.LocalData;
import com.example.e610.naghmaty.Models.Products.Products;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.Callbacks;
import com.example.e610.naghmaty.Utils.JsonParsingUtils;
import com.example.e610.naghmaty.Utils.NetworkState;
import com.example.e610.naghmaty.Utils.RetrofitLib.GetCompanyProducts;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment implements ProductsAdapter.RecyclerViewClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ProductsAdapter productsAdapter;
    private RecyclerView productsRecyclerView;

    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
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
        //com.gmsproduction.carsapp
        linksGooglePlay.add("com.semsmshehab.gmsproduction");
        linksGooglePlay.add("com.gmsproduction.tarekelsheikh");
        linksGooglePlay.add("com.music.test.musicpro");
        linksGooglePlay.add("com.gmsproduction.elmoled.elmoled");
        linksGooglePlay.add("com.gmsproduction.elwadi");
        linksGooglePlay.add("com.gmsproduction.far3onap");
        linksGooglePlay.add("com.gmsproduction.far3onap");
        linksGooglePlay.add("com.gmsproduction.emsakya");
        linksGooglePlay.add("com.gmsproduction.baldnanews");
    }

    Products products;
    private void getRemoteData(){
        if(NetworkState.ConnectionAvailable(getActivity())){
            GetCompanyProducts getCompanyProducts=new GetCompanyProducts();
            getCompanyProducts.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    try{
                        if(isDataloaded)
                            return;

                        isDataloaded=true;
                        if(obj!=null) {
                            products = new Products();
                            products=(Products)obj;
                            productsAdapter=new ProductsAdapter(products,getActivity());
                            productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                            productsAdapter.setClickListener(ProductsFragment.this);
                            productsRecyclerView.setAdapter(productsAdapter);
                            productsRecyclerView.scrollToPosition(products.getData().size()/2);
                            productsAdapter.notifyDataSetChanged();


                            String jsonStr= JsonParsingUtils.getJson(products);
                            CompanyCategory companyCategory=new CompanyCategory();
                            companyCategory.setId("products");
                            companyCategory.setTitle("products");
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

                        }

                    }catch (Exception e){
                        Log.e("asd",e.getMessage(),e);
                    }
                }

                @Override
                public void OnFailure(String error) {

                }
            });

            getCompanyProducts.start();
            checkInterntetWeakness();

        }else {
            CompanyCategory companyCategory=new CompanyCategory();
            companyCategory.setId("");
            companyCategory.setTitle("products");
            companyCategory.setJsonContent("");
            LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
            localData.addCallBacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    try {
                        CompanyCategory category = (CompanyCategory) obj;
                        products = (Products) JsonParsingUtils.getObject(category.getJsonContent(), Products.class);
                        productsAdapter = new ProductsAdapter(products, getActivity());
                        productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        productsAdapter.setClickListener(ProductsFragment.this);
                        productsRecyclerView.setAdapter(productsAdapter);
                        productsRecyclerView.scrollToPosition(products.getData().size() / 2);
                        productsAdapter.notifyDataSetChanged();
                    }catch(Exception e){}

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
                companyCategory.setTitle("products");
                companyCategory.setJsonContent("");
                LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
                localData.addCallBacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        try {
                            CompanyCategory category = (CompanyCategory) obj;
                            products = (Products) JsonParsingUtils.getObject(category.getJsonContent(), Products.class);
                            productsAdapter = new ProductsAdapter(products, getActivity());
                            productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            productsAdapter.setClickListener(ProductsFragment.this);
                            productsRecyclerView.setAdapter(productsAdapter);
                            productsRecyclerView.scrollToPosition(products.getData().size() / 2);
                            productsAdapter.notifyDataSetChanged();
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

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_products, container, false);
        productsRecyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        getRemoteData();

        TextView textView= view.findViewById(R.id.products_title);
        int langType= SharedPrefUtils.getSharedPrefValue(getActivity());
        if(langType==1){
            textView.setText("المنتجات");
        }else{
            textView.setText("Products");
        }


        return view;
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
    ArrayList<String> linksGooglePlay = new ArrayList<>();
    String el3abhaUrl="http://www.el3abha.com";
    String url_1,url_2,url_3,url_4,url_5,url_6,url_7,url_8,url_9,url_10;

    @Override
    public void ItemClicked(View v, int position) {
        Intent intent=new Intent(getContext(),DetailedActivity.class);
        Log.e("Position=",""+position);
        intent.putExtra("position",position);
        intent.putExtra("img",products.getData().get(position).getLogo());
        int langType= SharedPrefUtils.getSharedPrefValue(getActivity());
        if(langType==1){
            intent.putExtra("name",products.getData().get(position).getArName());
            intent.putExtra("content",products.getData().get(position).getArDescription());
        }else {
            intent.putExtra("name",products.getData().get(position).getEnName());
            intent.putExtra("content",products.getData().get(position).getEnDescription());
        }

        switch (position){
            case 0:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 1:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 2:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 3:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 4:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 5:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 6:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 7:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 8:
                intent.putExtra("url",linksGooglePlay.get(position));
                break;
            case 9:
                intent.putExtra("url",el3abhaUrl);
                break;
            default:
                intent.putExtra("url","no_url");


        }

        //intent.putExtra("data",products);
        /* Bundle bundle=new Bundle();*/
        /* bundle.putSerializable("data",products);
        bundle.putInt("position",position);
        intent.putExtras(bundle);*/
        getActivity().startActivity(intent);
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
}

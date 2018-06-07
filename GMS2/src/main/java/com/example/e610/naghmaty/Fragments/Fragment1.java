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
import com.example.e610.naghmaty.Adapters.Adapter1;
import com.example.e610.naghmaty.Data.CompanyCategory;
import com.example.e610.naghmaty.Data.LocalData;
import com.example.e610.naghmaty.Models.CategoriesResponse.CategoriesResponse;
import com.example.e610.naghmaty.Models.Clients.Clients;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.Callbacks;
import com.example.e610.naghmaty.Utils.JsonParsingUtils;
import com.example.e610.naghmaty.Utils.NetworkState;
import com.example.e610.naghmaty.Utils.RetrofitLib.GetCategories;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;


public class Fragment1 extends Fragment implements Adapter1.RecyclerViewClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    int id;
    public void setId(int id){
        this.id=id;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView clientsRecyclerView;
    private Adapter1 clientsAdapter;
    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static com.example.e610.naghmaty.Fragments.ClientsFragment newInstance(String param1, String param2) {
        com.example.e610.naghmaty.Fragments.ClientsFragment fragment = new com.example.e610.naghmaty.Fragments.ClientsFragment();
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


    private void getRemoteData(){
        if(NetworkState.ConnectionAvailable(getActivity())){
            GetCategories getClientsTask=new GetCategories();
            getClientsTask.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {

                    if(isDataloaded)
                        return;

                    isDataloaded=true;

                    try{
                        if(obj!=null) {
                            clients = new CategoriesResponse();
                            clients=(CategoriesResponse) obj;
                            clientsAdapter=new Adapter1(id,clients,getActivity());
                            clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                            clientsAdapter.setClickListener(Fragment1.this);
                            clientsRecyclerView.setAdapter(clientsAdapter);
                            //clientsRecyclerView.scrollToPosition(clients.data.get(id).products.data.size()/2);
                            clientsAdapter.notifyDataSetChanged();
                            textView.setText(clients.data.get(id).arName);
                            String jsonStr= JsonParsingUtils.getJson(clients);
                            CompanyCategory companyCategory=new CompanyCategory();
                            companyCategory.setId(id+"");
                            companyCategory.setTitle(id+"");
                            companyCategory.setJsonContent(jsonStr);
                            LocalData localData=new LocalData(getActivity(), LocalData.insert,companyCategory);
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

            getClientsTask.start();
            checkInterntetWeakness();
        }else {
            CompanyCategory companyCategory=new CompanyCategory();
            companyCategory.setId("");
            companyCategory.setTitle(id+"");
            companyCategory.setJsonContent("");
            LocalData localData=new LocalData(getActivity(), LocalData.select,companyCategory);
            localData.addCallBacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    try {
                        CompanyCategory category = (CompanyCategory) obj;
                        clients = (CategoriesResponse) JsonParsingUtils.getObject(category.getJsonContent(), CategoriesResponse.class);
                        clientsAdapter = new Adapter1(id,clients, getActivity());
                        clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        clientsAdapter.setClickListener(Fragment1.this);
                        clientsRecyclerView.setAdapter(clientsAdapter);
                        //clientsRecyclerView.scrollToPosition(clients.data.get(id).products.data.size() / 2);
                        clientsAdapter.notifyDataSetChanged();
                        textView.setText(clients.data.get(id).arName);
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
                companyCategory.setTitle(id+"");
                companyCategory.setJsonContent("");
                LocalData localData=new LocalData(getActivity(), LocalData.select,companyCategory);
                localData.addCallBacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        try {
                            CompanyCategory category = (CompanyCategory) obj;
                            clients = (CategoriesResponse) JsonParsingUtils.getObject(category.getJsonContent(), Clients.class);
                            clientsAdapter = new Adapter1(id,clients, getActivity());
                            clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            clientsAdapter.setClickListener(Fragment1.this);
                            clientsRecyclerView.setAdapter(clientsAdapter);
                            //.scrollToPosition(clients.data.get(id).products.data.size() / 2);
                            clientsAdapter.notifyDataSetChanged();
                            textView.setText(clients.data.get(id).arName);
                        }catch(Exception e){}
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
    CategoriesResponse clients=new CategoriesResponse();
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_clients, container, false);
        clientsRecyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        textView=(TextView) view.findViewById(R.id.clients_title);
        getRemoteData();

        int langType= SharedPrefUtils.getSharedPrefValue(getActivity());
        if(langType==1){
            textView.setText("");
        }else{
            textView.setText("");
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

    @Override
    public void ItemClicked(View v, int position) {
        if (getActivity() != null) {
            //Toast.makeText(getActivity(),clients.data.get(position),Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getContext(),DetailedActivity.class);
            Log.e("Position=",""+position);
            intent.putExtra("position",position);
            intent.putExtra("img",clients.data.get(id).products.data.get(position).image);

            //intent.putExtra("data",products);
        /* Bundle bundle=new Bundle();*/
        /* bundle.putSerializable("data",products);
        bundle.putInt("position",position);
        intent.putExtras(bundle);*/
            getActivity().startActivity(intent);
        }
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

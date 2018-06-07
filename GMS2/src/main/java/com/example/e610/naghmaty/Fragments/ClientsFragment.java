package com.example.e610.naghmaty.Fragments;

import android.content.Context;
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
import android.widget.Toast;

import com.example.e610.naghmaty.Adapters.ClientsAdapter;
import com.example.e610.naghmaty.Data.CompanyCategory;
import com.example.e610.naghmaty.Data.LocalData;
import com.example.e610.naghmaty.Models.Clients.Clients;
import com.example.e610.naghmaty.R;
import com.example.e610.naghmaty.Utils.Callbacks;
import com.example.e610.naghmaty.Utils.JsonParsingUtils;
import com.example.e610.naghmaty.Utils.NetworkState;
import com.example.e610.naghmaty.Utils.RetrofitLib.GetClientsTask;
import com.example.e610.naghmaty.Utils.SharedPrefUtils;



public class ClientsFragment extends Fragment implements ClientsAdapter.RecyclerViewClickListener {

    private OnFragmentInteractionListener mListener;
    private RecyclerView clientsRecyclerView;
    private ClientsAdapter clientsAdapter;
    public ClientsFragment() {
        // Required empty public constructor
    }







    private void getRemoteData(){
        if(NetworkState.ConnectionAvailable(getActivity())){
            GetClientsTask getClientsTask=new GetClientsTask();
            getClientsTask.setCallbacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {

                    if(isDataloaded)
                        return;

                    isDataloaded=true;

                    try{
                        if(obj!=null) {
                            clients = new Clients();
                            clients=(Clients)obj;
                            clientsAdapter=new ClientsAdapter(clients,getActivity());
                            clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                            clientsAdapter.setClickListener(ClientsFragment.this);
                            clientsRecyclerView.setAdapter(clientsAdapter);
                            clientsRecyclerView.scrollToPosition(clients.getData().size()/2);
                            clientsAdapter.notifyDataSetChanged();

                            String jsonStr= JsonParsingUtils.getJson(clients);
                            CompanyCategory companyCategory=new CompanyCategory();
                            companyCategory.setId("clients");
                            companyCategory.setTitle("clients");
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

            getClientsTask.start();
            checkInterntetWeakness();
        }else {
            CompanyCategory companyCategory=new CompanyCategory();
            companyCategory.setId("");
            companyCategory.setTitle("clients");
            companyCategory.setJsonContent("");
            LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
            localData.addCallBacks(new Callbacks() {
                @Override
                public void OnSuccess(Object obj) {
                    try {
                        CompanyCategory category = (CompanyCategory) obj;
                        clients = (Clients) JsonParsingUtils.getObject(category.getJsonContent(), Clients.class);
                        clientsAdapter = new ClientsAdapter(clients, getActivity());
                        clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        clientsAdapter.setClickListener(ClientsFragment.this);
                        clientsRecyclerView.setAdapter(clientsAdapter);
                        clientsRecyclerView.scrollToPosition(clients.getData().size() / 2);
                        clientsAdapter.notifyDataSetChanged();
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
                companyCategory.setTitle("clients");
                companyCategory.setJsonContent("");
                LocalData localData=new LocalData(getActivity(),LocalData.select,companyCategory);
                localData.addCallBacks(new Callbacks() {
                    @Override
                    public void OnSuccess(Object obj) {
                        try {
                            CompanyCategory category = (CompanyCategory) obj;
                            clients = (Clients) JsonParsingUtils.getObject(category.getJsonContent(), Clients.class);
                            clientsAdapter = new ClientsAdapter(clients, getActivity());
                            clientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            clientsAdapter.setClickListener(ClientsFragment.this);
                            clientsRecyclerView.setAdapter(clientsAdapter);
                            clientsRecyclerView.scrollToPosition(clients.getData().size() / 2);
                            clientsAdapter.notifyDataSetChanged();
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
    Clients clients=new Clients();
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
            textView.setText("العملاء");
        }else{
            textView.setText("Clients");
        }

        return view;

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
        if (getActivity() != null)
            Toast.makeText(getActivity(),clients.getData().get(position).getEnName(),Toast.LENGTH_SHORT).show();
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

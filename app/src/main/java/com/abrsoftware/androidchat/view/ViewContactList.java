package com.abrsoftware.androidchat.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abrsoftware.androidchat.R;
import com.abrsoftware.androidchat.adapter.ContactListAdapter;
import com.abrsoftware.androidchat.domain.implementation.LoginPresenterImpl;
import com.abrsoftware.androidchat.domain.useCases.LoginView;
import com.abrsoftware.androidchat.entities.Contact;

import java.util.ArrayList;
import java.util.List;


public class ViewContactList extends Fragment implements ContactListAdapter.onItemClickListenerItem {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private LoginPresenterImpl presenter;
    private LoginView loginPresenter;
    private RecyclerView recycler;
    private LinearLayoutManager linearLayoutManager;

    public ViewContactList(LoginView view) {
        this.loginPresenter = view;
        // Required empty public constructor
    }

    public ViewContactList() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ViewContactList newInstance(String param1, String param2) {
        ViewContactList fragment = new ViewContactList();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_contact_list, container, false);
        presenter = new LoginPresenterImpl(loginPresenter);
        presenter.onCreate();
        Contact contact = new Contact("abr999@hotmail.com","online","");
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        ContactListAdapter contactListAdapter = new ContactListAdapter(contactList,this);
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(contactListAdapter);
        contactListAdapter.notifyDataSetChanged();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
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
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onclick(View v) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setToolBar(){

    }


    public void setAdapterValues(){

    }
}

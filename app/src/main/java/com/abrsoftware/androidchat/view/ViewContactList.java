package com.abrsoftware.androidchat.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abrsoftware.androidchat.R;
import com.abrsoftware.androidchat.adapter.ContactListAdapter;
import com.abrsoftware.androidchat.domain.implementation.ContactLictPresenterImpl;
import com.abrsoftware.androidchat.domain.implementation.LoginPresenterImpl;
import com.abrsoftware.androidchat.domain.useCases.ContactListPresenter;
import com.abrsoftware.androidchat.domain.useCases.ContactListView;
import com.abrsoftware.androidchat.domain.useCases.LoginView;
import com.abrsoftware.androidchat.entities.Contact;
import com.abrsoftware.androidchat.entities.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ViewContactList extends Fragment implements ContactListAdapter.onItemClickListenerItem, ContactListView {

    private OnFragmentInteractionListener mListener;
    private LoginPresenterImpl presenter;
    private ContactListPresenter presenterContact;
    private RecyclerView recycler;
    private LinearLayoutManager linearLayoutManager;

    //ButterKnife
    @Bind(R.id.fabAdd)
    public FloatingActionButton addContact;
    private ContactListAdapter contactListAdapter;


    public ViewContactList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_contact_list, container, false);
        ButterKnife.bind(this, rootView);
        /*presenter = new LoginPresenterImpl(loginPresenter);
        presenter.onCreate();*/
        presenterContact = new ContactLictPresenterImpl(this);
        presenterContact.onCreate();
        String toolBarTitle = presenterContact.getCurrentUserMail().length() > 0 ? presenterContact.getCurrentUserMail(): getResources().getString(R.string.toolbar_contactList);
        setToolBar(rootView,toolBarTitle);
        setAdapterValues(rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        presenterContact.onDestroy();
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        presenterContact.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenterContact.onPause();
        super.onPause();
    }

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

    @OnClick(R.id.fabAdd)
    public void addContact(){
        Log.d("msj","ViewContacList");
    }

    @Override
    public void onContactAdded(User user) {
        contactListAdapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        contactListAdapter.update(user);
    }

    @Override
    public void onContactDeleted(User user) {
        contactListAdapter.remove(user);
    }

    @Override
    public void onItemclick(User user) {

    }

    @Override
    public void onItemlongClickListener(User user) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setToolBar(View rootView, String userMail){
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(userMail);
    }


    public void setAdapterValues(View rootView){
        /*User user = new User("abr@7.com",true,"");
        List<User> users = new ArrayList<>();
        users.add(user);*/
        contactListAdapter = new ContactListAdapter(new ArrayList<User>(),this);
        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(contactListAdapter);
        contactListAdapter.notifyDataSetChanged();
    }
}

package com.abrsoftware.androidchat.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abrsoftware.androidchat.R;
import com.abrsoftware.androidchat.contactlist.ContactListActivity;
import com.abrsoftware.androidchat.domain.implementation.LoginPresenterImpl;
import com.abrsoftware.androidchat.domain.useCases.LoginPresenter;
import com.abrsoftware.androidchat.domain.useCases.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ViewLogin extends Fragment implements LoginView{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.btnLogin)
    public CardView btnLogin;

    @Bind(R.id.btnRegister)
    public CardView btnRegister;

    @Bind(R.id.inputMail)
    public EditText inputMail;

    @Bind(R.id.inputPass)
    public EditText inputPassword;

    @Bind(R.id.progressBar)
    public ProgressBar progressBar;

    private LoginPresenter presenter;

    public ViewLogin() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.layout_login, container, false);
        ButterKnife.bind(this, rootview);
        presenter = new LoginPresenterImpl(this);
        presenter.checkForAuthenticateUser();
        return rootview;
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
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgresBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenProgresBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handleSingOut() {

    }

    @OnClick(R.id.btnLogin)
    @Override
    public void handleSingIn() {
        presenter.validateLogin(inputMail.getText().toString(), inputPassword.getText().toString());
    }


    @Override
    @OnClick(R.id.btnRegister)
    public void handleSingUp() {
        Log.d("msj","hola");
        presenter.registerNewUser(inputMail.getText().toString(), inputPassword.getText().toString());
    }


    public void hello(){
        Log.d("msj","hola");
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(getActivity(), ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msjError = String.format(getString(R.string.login_error_messagge_singin), error);
        inputPassword.setError(msjError);
    }

    @Override
    public void newUserSucces() {
        Snackbar.make(getView(), R.string.login_notice_messagge_singup, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msjError = String.format(getString(R.string.login_error_messagge_singup), error);
        inputPassword.setError(msjError);
    }

    private void setInputs(boolean enable){
        inputMail.setEnabled(enable);
        inputPassword.setEnabled(enable);
        btnLogin.setEnabled(enable);
        btnRegister.setEnabled(enable);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

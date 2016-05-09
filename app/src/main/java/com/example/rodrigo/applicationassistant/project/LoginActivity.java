package com.example.rodrigo.applicationassistant.project;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.rodrigo.applicationassistant.R;
import com.example.rodrigo.applicationassistant.project.Activitys.MainActivity;
import com.example.rodrigo.applicationassistant.project.Models.UserModel;
import com.example.rodrigo.applicationassistant.project.Preferences.UserPreferences;
import com.example.rodrigo.applicationassistant.project.Service.UserService;

import static android.Manifest.permission.READ_CONTACTS;

//Login
public class LoginActivity extends AppCompatActivity {

    //Referecias UI
    private EditText mEmailView, mPasswordView;
    private TextInputLayout mInputEmail, mInputPassword;
    private Button mEmailSignInButton;
    private ScrollView loginForm;
    private ProgressBar loginProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginForm = (ScrollView) findViewById(R.id.login_form);
        loginProgress = (ProgressBar) findViewById(R.id.login_progress);
        mInputEmail = (TextInputLayout) findViewById(R.id.inputEmail);
        mInputPassword = (TextInputLayout) findViewById(R.id.inputPassword);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        mEmailView.addTextChangedListener(new MyTextWatcher(mEmailView));
        mPasswordView.addTextChangedListener(new MyTextWatcher(mPasswordView));


        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        UserPreferences userPreferences = new UserPreferences(LoginActivity.this);
        //Verificamos si el usuario esta logueado]
        if (!userPreferences.isFirstTime()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //Validamos el formulario
    private void submitForm() {

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        new UserService(LoginActivity.this).loginUser(mEmailView.getText().toString(),
                mPasswordView.getText().toString(),
                loginForm,
                loginProgress);
    }

    //
    private boolean validateEmail() {
        if (mEmailView.getText().toString().trim().isEmpty()) {
            mInputEmail.setError(getString(R.string.error_email_empty));
            requestFocus(mEmailView);
            return false;
        } else if (!mEmailView.getText().toString().contains("@")) {
            mInputEmail.setError(getString(R.string.error_email_empty));
            requestFocus(mEmailView);
            return false;
        } else {
            mInputEmail.setErrorEnabled(false);
        }
        return true;
    }

    //
    private boolean validatePassword() {
        if (mPasswordView.getText().toString().trim().isEmpty()) {
            mInputPassword.setError(getString(R.string.error_invalid_password));
            requestFocus(mPasswordView);
            return false;
        } else {
            mInputPassword.setErrorEnabled(false);
        }

        return true;
    }

    //
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    //
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {

            switch (view.getId()) {
                case R.id.email:
                    validateEmail();
                    break;
                case R.id.password:
                    validatePassword();
                    break;
            }
        }
    }
}


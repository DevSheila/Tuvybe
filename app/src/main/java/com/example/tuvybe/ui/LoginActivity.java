package com.example.tuvybe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuvybe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.signUpTextView)
    TextView mSignUpTextView;
    @BindView(R.id.inputUseremail)
    EditText mUserEmail;
    @BindView(R.id.inputUserpassword)
    EditText mUserPassword;
    @BindView(R.id.signInButton)
    Button mSignInButton;
    @BindView(R.id.firebaseProgressBar)
    ProgressBar mSignInProgressBar;
    @BindView(R.id.loadingTextView) TextView mLoadingSignUp;

    //animation variables
    Animation topAnim, bottomAnim;

    //Firebase variables
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {


                    Intent intent = new Intent(LoginActivity.this, EventsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("username", user.getDisplayName());
                    startActivity(intent);
                    finish();
                }
            }
        };
        ButterKnife.bind(this);
        mSignUpTextView.setOnClickListener(this);
        mSignInButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v==mSignUpTextView){
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
            finish();

        }
        if(v== mSignInButton){
            loginWithPassword();
            showProgressBar();
        }
    }

    private void loginWithPassword() {
        String email = mUserEmail.getText().toString().trim();
        String password = mUserPassword.getText().toString().trim();
        if (email.equals("")) {
            mUserEmail.setError("Please enter your email");
            return;
        }
        if (password.equals("")) {
            mUserPassword.setError("Password cannot be blank");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressBar();
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        Toast.makeText(LoginActivity.this, "Login Successful",
                                Toast.LENGTH_SHORT).show();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference databaseUsers = database.getReference("Users");
                        String id = mAuth.getCurrentUser().getUid();
                        String uName = mAuth.getCurrentUser().getDisplayName();
                        DatabaseReference username = databaseUsers.child(id).child("username");

                        Intent intent = new Intent(LoginActivity.this, EventsActivity.class);
                        intent.putExtra("username", uName);


                        Log.d("namee",uName);
                        startActivity(intent);
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void showProgressBar() {
        mSignInProgressBar.setVisibility(View.VISIBLE);
        mLoadingSignUp.setVisibility(View.VISIBLE);
        mLoadingSignUp.setText("Log in you in");
    }

    private void hideProgressBar() {
        mSignInProgressBar.setVisibility(View.GONE);
        mLoadingSignUp.setVisibility(View.GONE);
    }
}

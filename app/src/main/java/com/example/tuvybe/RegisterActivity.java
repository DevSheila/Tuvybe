package com.example.tuvybe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = RegisterActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.inputUsername)
    EditText mUserName;
    @BindView(R.id.inputUseremail)
    EditText mUserEmail;
    @BindView(R.id.inputUserpassword)
    EditText mUserPassword;
    @BindView(R.id.inputUserconfirmpassword)
    EditText mUserConfirmPassword;
    @BindView(R.id.signUpButton)
    Button mSignUpButton;
    @BindView(R.id.signInTextView)
    TextView mSignInTextView;

    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mSignUpButton.setOnClickListener(this);
        mSignInTextView.setOnClickListener(this);
//        createAuthStateListener();


    }

    @Override
    public void onClick(View v) {
        if(v == mSignInTextView){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(v== mSignUpButton){
            createNewUser();
        }

    }

    private void createNewUser(){
        final String name =mUserName.getText().toString().trim();
        final String email = mUserEmail.getText().toString().trim();
        String password = mUserPassword.getText().toString().trim();
        String confirmPassword = mUserConfirmPassword.getText().toString().trim();
        mName = mUserName.getText().toString().trim();



        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {

                createFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));
                Log.d(TAG, "Authentication successful");


                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                intent.putExtra("username",name );
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createAuthStateListener() {
        mAuthListener = firebaseAuth -> {
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();
            }

        };
    }


    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mUserEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mUserConfirmPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mUserConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }


    private void createFirebaseUserProfile(final FirebaseUser user) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
//                            Log.d(TAG, user.getDisplayName());
                            Toast.makeText(RegisterActivity.this, "The display name has been set", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
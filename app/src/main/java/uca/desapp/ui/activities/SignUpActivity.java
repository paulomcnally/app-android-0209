package uca.desapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tumblr.remember.Remember;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.desapp.R;
import uca.desapp.api.Api;
import uca.desapp.models.User;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private static final String EMAIL = "email";
    private static final String USER_ID = "user_id";

    private EditText email;
    private EditText password;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        initViews();

        // initialize sign up
        signUp();
    }

    private void initViews() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.sign_up);
    }

    private void signUp() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpAction();
            }
        });
    }

    private void signUpAction() {
        if(email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(
                    R.string.activity_sign_up_message_empty_email),
                    Toast.LENGTH_LONG).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(
                    R.string.activity_sign_up_message_empty_password),
                    Toast.LENGTH_LONG).show();
        } else {
            // instance suer
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());

            // make http request
            Call<User> call = Api.instance().signUp(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.body() != null) {
                        Remember.putString(EMAIL, response.body().getEmail());
                        //Remember.putInt(USER_ID, response.body().getId());

                        String myEmail = Remember.getString(EMAIL, "");
                       // int myUserId = Remember.getInt(USER_ID, 0);

                        //String.format(Locale.US, "%d", myUserId)
                        Log.i(TAG, myEmail);
                        //Log.i(TAG, "" + myUserId);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }

}

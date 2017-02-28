package uca.desapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uca.desapp.R;
import uca.desapp.api.Api;
import uca.desapp.models.SignInResponseModel;
import uca.desapp.models.User;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in)
    public void signIn(View view) {
        if(email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(
                    R.string.activity_sign_up_message_empty_email),
                    Toast.LENGTH_LONG).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(
                    R.string.activity_sign_up_message_empty_password),
                    Toast.LENGTH_LONG).show();
        } else {
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            Call<SignInResponseModel> call = Api.instance().signIn(user);
            call.enqueue(new Callback<SignInResponseModel>() {
                @Override
                public void onResponse(Call<SignInResponseModel> call, Response<SignInResponseModel> response) {
                    Log.i("tag", response.body().getId());
                }

                @Override
                public void onFailure(Call<SignInResponseModel> call, Throwable t) {

                }
            });
        }
    }

}

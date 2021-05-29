package com.example.pushnotificationassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class SignUpActivity extends AppCompatActivity {
    private EditText editFirstName;
    private EditText editSecondName;
    private EditText editEmail;
    private EditText editPassword;
    private Button btnSignup;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editFirstName = findViewById(R.id.EditFirstN);
        editSecondName = findViewById(R.id.EditSecondN);
        editEmail = findViewById(R.id.EditEmail);
        editPassword = findViewById(R.id.EditPassword);
        btnSignup = findViewById(R.id.btnSignUP);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "{" +
                        "\"firstName\"" + ":" + "\"" + editFirstName.getText().toString() + "\"," +
                        "\"secondName\"" + ":" + "\"" + editSecondName.getText().toString() + "\"," +
                        "\"email\"" + ":" + "\"" + editEmail.getText().toString() + "\"," +
                        "\"password\"" + ":" + "\"" + editPassword.getText().toString() + "\"," +
                        "}";
                Send(data);
            }
        });
    }


    private void Send(String data) {

        String save = data;

        String URL = "https://mcc-users-api.herokuapp.com/add_new_user";
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Log.d("TAG", "requestQueue: " + requestQueue);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("TAG", "onResponse: " + obj.toString());

                } catch (JSONException e) {

                    Log.d("TAG", "Server Error");

                }
            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onErrorResponse: " + error);

            }
        }) {


            @Override

            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                Log.d("TAG", "save: " + save);

                try {
                    return save == null ? null : save.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };

        requestQueue.add(stringRequest);

    }

    public void registerAccount(View view) {

        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);

        startActivity(intent);


    }
}








package com.buksu.json_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView myResult;
    private Button myButton;
    private RequestQueue myQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myResult = findViewById(R.id.textView);
        myButton = findViewById(R.id.button);
        myQueue = Volley.newRequestQueue(this);

    }

    public void getJSON(View v){
        getRequest();
    }


    private void getRequest(){
        String url = "https://raw.githubusercontent.com/Sean-Gonzaga/json/main/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("student");
                            JSONArray jsonArray2 = response.getJSONArray("section");

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject student = jsonArray.getJSONObject(i);

                                for(int j = 0; j < jsonArray2.length(); j++){
                                    JSONObject section = jsonArray2.getJSONObject(j);

                                String myName = student.getString("name");
                                int myAge = student.getInt("age");
                                String myQuote = student.getString("quote");
                                String mySection = section.getString("section");
                                String myCode = section.getString("code");
                                String myEmail = section.getString("email");

                                myResult.append(myName +", "+ String.valueOf(myAge)+ ", "+ myQuote +","+"\n\n" + mySection + ", "+ myCode + ", "+ myEmail +"\n\n");

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        myQueue.add(request);
    }
}
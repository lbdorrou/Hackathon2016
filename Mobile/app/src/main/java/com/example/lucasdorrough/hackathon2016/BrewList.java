package com.example.lucasdorrough.hackathon2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.*;


import cz.msebera.android.httpclient.Header;

public class BrewList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_list);

        TestConnect();
    }

    private void TestConnect(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://echo.getpostman.com/get?test=123", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String plainResponse = new String(response);
                Log.v("HttpSuccess", plainResponse);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.v("HttpError", errorResponse.toString());
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }
}

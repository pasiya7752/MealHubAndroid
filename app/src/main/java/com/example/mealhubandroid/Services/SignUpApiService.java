package com.example.mealhubandroid.Services;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SignUpApiService {

    private static final String BASE_URL = "http://192.168.42.253:8080/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getAll(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url),responseHandler);
    }

    public static void SignUp(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+getAbsoluteUrl(url));
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}

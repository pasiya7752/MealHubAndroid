package com.example.mealhubandroid.Services;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MenuReaderApiService extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.42.253:8080/";

    FileService fileService;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getSuggestions(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


//    public void uploadToServer(String filePath) {
//
//        File file = new File(filePath);
//        // Create a request body with file and image media type
//        RequestBody fileReqBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        // Create MultipartBody.Part using file request-body,file name and part name
//        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), fileReqBody);
//        //Create request body with text description and text media type
////        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "image-type");
//        //
//        Call<FileInfo> call = fileService.upload(body);
//        call.enqueue(new Callback<FileInfo>() {
//            @Override
//            public void onResponse(Call<FileInfo> call, Response<FileInfo> response) {
//                System.out.println(response.getClass());
//            }
//
//            @Override
//            public void onFailure(Call<FileInfo> call, Throwable t) {
//
//            }
//        });
//    }


}
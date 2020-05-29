package com.mahendra.serviceworker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mahendra.serviceworker.service.ServiceWorker;
import com.mahendra.serviceworker.service.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ServiceWorker mWorker;
    private OkHttpClient okHttpClient;
    private AppCompatImageView ivOne;
    private AppCompatImageView ivTwo;


    private List<String> getUrls(){
        List<String> urls = new ArrayList<String>();
        urls.add("https://i.ytimg.com/vi/8BxOkc_Y16I/maxresdefault.jpg");
        urls.add("https://i.ytimg.com/vi/0KEv38tAWm4/maxresdefault.jpg");
        urls.add("https://i.ytimg.com/vi/ELCDXFM9fqo/maxresdefault.jpg");
        urls.add("https://i.ytimg.com/vi/Az8z5_ZfGXo/maxresdefault.jpg");
        urls.add("https://i.ytimg.com/vi/OiRFKw9J4Qg/maxresdefault.jpg");
        return urls;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AppCompatButton btnOne = findViewById(R.id.btnOne);
        final AppCompatButton btnTwo = findViewById(R.id.btnTwo);
        ivOne = findViewById(R.id.ivOne);
        ivTwo = findViewById(R.id.ivTwo);
        mWorker = new ServiceWorker("worker_one");
        okHttpClient = new OkHttpClient();
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImageOneAndSet();
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchImageTwoAndSet();
            }
        });
    }

    private String getRandomUrl() {
        return getUrls().get(new Random().nextInt(getUrls().size()));
    }

    private void fetchImageOneAndSet() {
        mWorker.add(new Task<Bitmap>(){
            @Override
            public Bitmap onExecuteTask() {
                Request request = new Request.Builder().url(getRandomUrl()).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if(response.body() != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        return bitmap;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void onTaskComplete(Bitmap bitmap) {
                if(bitmap == null)
                    Toast.makeText(MainActivity.this,"Failed to get image",Toast.LENGTH_LONG).show();
                else
                    ivOne.setImageBitmap(bitmap);
            }
        });
    }


    private void fetchImageTwoAndSet() {
        mWorker.add(new Task<Bitmap>(){
            @Override
            public Bitmap onExecuteTask() {
                Request request = new Request.Builder().url(getRandomUrl()).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    if(response.body() != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        return bitmap;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void onTaskComplete(Bitmap bitmap) {
                if(bitmap == null)
                    Toast.makeText(MainActivity.this,"Failed to get image",Toast.LENGTH_LONG).show();
                else
                    ivTwo.setImageBitmap(bitmap);
            }
        });
    }
}

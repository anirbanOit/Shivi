package com.example.project1;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.list;

/**
 * Created by OPTLPTP111 on 08-09-2017.
 */

public class Main2Activity extends AppCompatActivity implements UsersAdapter.ClickListner {

    private static final String TAG = Main2Activity.class.getSimpleName();
    UsersAdapter adapter;
    List<Post> users;
    ImageView image;
    Bitmap decodedImage;
    TextView age;
    EditText search;
    RecyclerView recyclerView;
    List<Post> filteredList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        search = (EditText) findViewById(R.id.search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        APIService apiService =
                RetrofitClient.getClient().create(APIService.class);

        Call<List<Post>> call = apiService.doGetListResources();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                //int statusCode = response.code();
                users = response.body();

                adapter = new UsersAdapter(users, R.layout.fetch, getApplicationContext());
                adapter.setClickListner(Main2Activity.this);
                recyclerView.setAdapter(adapter);
                //addTextListener();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        addTextListener();
    }

    @Override
    public void showDialog(View v, int position) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog);
        // set the custom dialog components - text, image and button
        ImageView image = (ImageView) dialog.findViewById(R.id.pic);
        image.setImageBitmap(decodeImage(users.get(position).getPhoto().toString()));
        TextView desc = (TextView) dialog.findViewById(R.id.dep);
        desc.setText(users.get(position).getDept());
        dialog.show();
    }

    public Bitmap decodeImage(String imageString) {
        byte[] imageBytes = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT);
        decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }


    public void addTextListener() {

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {
                String s = query.toString().toLowerCase();
                filteredList.clear();
                if (s.isEmpty()) {
                    filteredList = users;
                }
                else {
                    for (Post p : users) {
                        if (p.getFirstName().toString().toLowerCase().contains(s) || p.getLastName().toString().toLowerCase().contains(s)) {
                            filteredList.add(p);
                        }

                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                adapter = new UsersAdapter(filteredList, R.layout.fetch, Main2Activity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();  // data set changed
            }
        });
    }


}




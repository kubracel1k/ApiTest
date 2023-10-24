package com.example.apitest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detailImageView);
        TextView titleTextView = findViewById(R.id.detailTitleView);

        int itemId = getIntent().getIntExtra("selectedItemId", 0);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Photo> call = apiService.getPhotoDetail(itemId);
        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                if (response.isSuccessful()) {
                    Photo item = response.body();
                    String photoUrl = item.getUrl();

                    Glide.with(DetailActivity.this).load(photoUrl).into(imageView);

                    titleTextView.setText(item.getTitle());
                } else {
                    // Hata işleme
                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                // Hata işleme
            }
        });
    }
}

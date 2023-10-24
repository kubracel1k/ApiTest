package com.example.apitest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Photo>> call = apiService.getPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    List<Photo> photos = response.body();

                    // RecyclerView için yeni bir liste oluştur ve verileri buraya ekle
                    List<Photo> photoList = new ArrayList<>(photos);

                    adapter = new PhotoAdapter(photoList, new PhotoAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Photo photo) {
                            // Tıklanan öğenin detay sayfasına yönlendirilmesi burada yapılabilir.
                            //  Detay sayfasına gitmek için yeni bir Intent oluşturun ve startActivity(intent) başlat
                            String title = photo.getTitle();
                            String url = photo.getUrl();
                            int selectedItemId = photo.getId();

                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("title", title);
                            intent.putExtra("url", url);
                            intent.putExtra("selectedItemId", selectedItemId);

                            startActivity(intent);
                        }
                        @Override
                        public void onTitleClick(Photo photo) {
                            String title = photo.getTitle();
                            String url = photo.getUrl();
                            int selectedItemId = photo.getId();

                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("title", title);
                            intent.putExtra("url", url);
                            intent.putExtra("selectedItemId", selectedItemId);

                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                } else {

                }

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
            }
        });
    }
}

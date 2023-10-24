package com.example.apitest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<Photo> photoList;
    private OnItemClickListener listener;

    public PhotoAdapter(List<Photo> photoList, OnItemClickListener listener) {
        this.photoList = photoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_item, parent, false);
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.bind(photo, listener);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Photo photo);
        void onTitleClick(Photo photo);
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView,imageView2;
        private TextView titleTextView,titleTextView2;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            titleTextView2 = itemView.findViewById(R.id.titleTextView2);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }

        public void bind(final Photo photo, final OnItemClickListener listener) {
            titleTextView.setText(photo.getTitle());
            titleTextView2.setText(photo.getTitle());
           Glide.with(itemView.getContext()).load(photo.getUrl()).into(imageView);
           Glide.with(itemView.getContext()).load(photo.getUrl()).into(imageView2);

            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTitleClick(photo);
                }
            });
            titleTextView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTitleClick(photo);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(photo);
                }
            });
        }
    }
}

package com.example.itubeapplication.viewModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itubeapplication.R;
import com.example.itubeapplication.video.Video;

public class VideoAdapter extends ListAdapter<Video,VideoAdapter.MyViewHolder> {

    // variables
    Context context;
    VideoViewModel videoViewModel;

    // constructor
    public VideoAdapter(@NonNull DiffUtil.ItemCallback<Video> diffCallback,
                        Context context, VideoViewModel videoViewModel) {
        super(diffCallback);
        this.context = context;
        this.videoViewModel = videoViewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.MyViewHolder holder, int position) {
        Video current = getItem(position);
        holder.video_name.setText(current.getName());
        holder.video_id.setText(String.valueOf(current.getId()));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // variables
        private TextView video_id, video_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            video_id = itemView.findViewById(R.id.video_id);
            video_name = itemView.findViewById(R.id.video_name);
        }
    }

    // detect if the contents of the video list have changed
    public static class videoDiff extends DiffUtil.ItemCallback<Video>{
        @Override
        public boolean areItemsTheSame(@NonNull Video old, @NonNull Video next) {
            return old == next;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Video old, @NonNull Video next) {
            return old.getName().equals(next.getName());
        }
    }

}
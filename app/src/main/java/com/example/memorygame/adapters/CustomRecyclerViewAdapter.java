package com.example.memorygame.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.memorygame.R;
import com.example.memorygame.models.Image_;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder> {

    private int currentPosition = -1;

    public interface OnRecyclerViewImageSelectedListener<T> {
        void imageClicked(int position, T t);
    }

    private OnRecyclerViewImageSelectedListener<Image_> mImageClickListener;

    private List<Image_> imageUrls;
    private Context context;

    public CustomRecyclerViewAdapter(List<Image_> imageUrls,Context context,OnRecyclerViewImageSelectedListener<Image_> mImageClickListener) {
        this.imageUrls = imageUrls;
        this.context = context;
        this.mImageClickListener = mImageClickListener;
    }

    @NonNull
    @Override
    public CustomRecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_single_recycler_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomRecyclerViewAdapter.CustomViewHolder holder, int position) {
        Image_ image = imageUrls.get(position);

        if (image.isClickStatus()) {
            holder.img_default.setVisibility(View.GONE);
            holder.img_game.setVisibility(View.VISIBLE);
            if(!image.getSrc().equals("")) {
                Picasso.with(context)
                        .load(image.getSrc())
                        .into(holder.img_game);
            }
        } else if(!image.isCheckStatus()) {
            holder.img_default.setVisibility(View.GONE);
            holder.img_game.setVisibility(View.VISIBLE);
            image.setCheckStatus(true);
            if(!image.getSrc().equals("")) {
                Picasso.with(context)
                        .load(image.getSrc())
                        .into(holder.img_game);
            }

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.img_default.setVisibility(View.VISIBLE);
                    holder.img_game.setVisibility(View.GONE);
                    //Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
                }
            }, 2000);

        } else {
            holder.img_default.setVisibility(View.VISIBLE);
            holder.img_game.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_game;
        private ImageView img_default;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            img_game = itemView.findViewById(R.id.img_game);
            img_default = itemView.findViewById(R.id.img_default);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mImageClickListener != null) {
                        mImageClickListener.imageClicked(getLayoutPosition(), imageUrls.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}

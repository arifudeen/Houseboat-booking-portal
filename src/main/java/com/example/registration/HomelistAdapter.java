package com.example.registration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HomelistAdapter extends RecyclerView.Adapter<HomelistAdapter.HomeViewHolder> {

    private Context mCtx;
    private List<Homelist> homelists;

    public HomelistAdapter(Context mCtx, List<Homelist> homelists) {
        this.mCtx = mCtx;
        this.homelists = homelists;
    }

    @NonNull
    @Override
    public HomelistAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_homelist, null);


        return new HomelistAdapter.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomelistAdapter.HomeViewHolder holder, int position) {

        Homelist homelist = homelists.get(position);
        String img = homelist.getImage();
//        try{
//            URL url = new URL(img);
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            holder.imageView.setImageBitmap(bmp);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
        Glide.with(mCtx)
                .load(homelist.getImage())
                .into(holder.imageView);

        holder.tv_pname.setText(homelist.getBoatname());
        /*holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, view_pack.class);
                String pid_str= String.valueOf(homelist.getPid());

                *//*intent.putExtra("id", pid_str);
                intent.putExtra("image", homelist.getImage());
                intent.putExtra("boat_name", homelist.getBoatname());
                intent.putExtra("package", homelist.getPackagee());
                intent.putExtra("desccription", homelist.getDesccription());
                intent.putExtra("rate", homelist.getRate());

                mCtx.startActivity(intent);*//*
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return homelists.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context context;
        TextView tv_pname;
        ImageView imageView;
        RelativeLayout layout;

        public HomeViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.iv_home);
            tv_pname = itemView.findViewById(R.id.tv_pname);
            layout = itemView.findViewById(R.id.lay);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent();

            for (int i = 0; i < getItemCount(); i++) {
                if (v == itemView) {
                    intent = new Intent(context, view_pack.class);
                    intent.putExtra("pacid", homelists.get(getLayoutPosition()).getPid());
                    intent.putExtra("image", homelists.get(getLayoutPosition()).getImage());
                    intent.putExtra("boat_name", homelists.get(getLayoutPosition()).getBoatname());
                    intent.putExtra("owner_name", homelists.get(getLayoutPosition()).getOwnerName());
                    intent.putExtra("package", homelists.get(getLayoutPosition()).getPackagee());
                    intent.putExtra("desccription", homelists.get(getLayoutPosition()).getDesccription());
                    intent.putExtra("rate", homelists.get(getLayoutPosition()).getRate());
                    context.startActivity(intent);
                    break;
                }
            }
        }
    }

}
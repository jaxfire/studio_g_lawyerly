package com.jaxfire.lawyerly;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    ArrayList<Lawyer> lawyers;
    Context context;
    View view;
    ViewHolder viewHolder;

    public RecyclerViewAdapter(Context context, ArrayList<Lawyer> lawyers){
        this.lawyers = lawyers;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView profile_image;
        public TextView nameTextView;
        public TextView specialityTextView;
        public TextView ratingTextView;
        public TextView costTextView;
        public ImageView verifiedImageView;

        public ViewHolder(final View view){

            super(view);

            profile_image = (ImageView) view.findViewById(R.id.profile_image);
            nameTextView = (TextView) view.findViewById(R.id.name);
            specialityTextView = (TextView) view.findViewById(R.id.speciality);
            ratingTextView = (TextView) view.findViewById(R.id.rating);
            costTextView = (TextView) view.findViewById(R.id.cost);
            verifiedImageView = (ImageView) view.findViewById(R.id.verified_icon);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(view.getContext(), LawyerDetails.class);

                    view.getContext().startActivity(intent);

                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if(position == 0){
            type = 0;
        } else {
            type = 1;
        }
        return type;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        if (viewType == 0){
            view = LayoutInflater.from(context).inflate(R.layout.header, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.recyclerview_items, parent, false);
        }

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        if (holder.getItemViewType() == 0){

        } else {

            holder.profile_image.setImageResource(lawyers.get(position).getProfile_image_id());
            holder.nameTextView.setText(lawyers.get(position).getName());
            holder.specialityTextView.setText(lawyers.get(position).getSpeciality());
            holder.ratingTextView.setText(lawyers.get(position).getRating());
            holder.costTextView.setText(lawyers.get(position).getCost());

            if (lawyers.get(position).getVerified()) {
                holder.verifiedImageView.setVisibility(View.VISIBLE);
            } else {
                holder.verifiedImageView.setVisibility(View.INVISIBLE);
            }

        }

    }

    @Override
    public int getItemCount(){

        return lawyers.size();
    }
}
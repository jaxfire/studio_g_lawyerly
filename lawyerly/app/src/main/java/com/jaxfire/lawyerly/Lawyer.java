package com.jaxfire.lawyerly;

import android.content.Context;

import java.util.ArrayList;

public class Lawyer {

    private String name, speciality, rating, cost;
    private boolean featured, favourite, verified;
    private int profile_image_id;

    private Lawyer(String rating, String cost){

        this.name = "Lawyer's name";
        this.speciality = "Speciality";
        this.rating = rating;
        this.cost = cost;

    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getRating() {
        return rating;
    }

    public String getCost() {
        return cost;
    }

    public boolean getFeatured(){ return featured; }

    public boolean getFavourite(){ return favourite; }

    public int getProfile_image_id(){
        return profile_image_id;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean getVerified(){
        return verified;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setProfile_image_id(int profile_image_id) {
        this.profile_image_id = profile_image_id;
    }

    public static ArrayList<Lawyer> generateTemplateLayers(Context context, int quantity){

        String[] ratings = {"4.8", "4.5", "4.1", "4.6"};
        String[] costs = {"60.90", "55.50", "90.00", "65.50"};

        ArrayList<Lawyer> lawyers = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {

            lawyers.add(new Lawyer(ratings[i % ratings.length], costs[i % costs.length]));
            if (i < 5){
                lawyers.get(i).setFeatured(true);
            }
            if (Math.random() > 0.4){
                lawyers.get(i).setVerified(true);
            }
            lawyers.get(i).setProfile_image_id(context.getResources().getIdentifier("profile_" + String.valueOf(i + 1), "drawable", context.getPackageName()));
        }

        lawyers.get(5).setFavourite(true);

        return lawyers;

    }
}
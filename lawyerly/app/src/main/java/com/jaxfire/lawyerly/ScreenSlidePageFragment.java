package com.jaxfire.lawyerly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ScreenSlidePageFragment extends Fragment {

    private ArrayList<Lawyer> lawyers;

    public static ScreenSlidePageFragment newInstance(int position, ArrayList<Lawyer> lawyers) {

        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        ArrayList<Lawyer> lawyerSubset = new ArrayList<>();

        /* For this concept app only. Create different subsets of the main lawyer list depending on
           which ViewPager screen is required... FEATURED, ALL or FAVS */
        if (position == 0){

            for (Lawyer lawyer : lawyers) {

                if (lawyer.getFeatured()){
                    lawyerSubset.add(lawyer);
                }

            }

        } else if (position == 1){

            lawyerSubset = lawyers;

        } else {

            for (Lawyer lawyer : lawyers) {

                if (lawyer.getFavourite()){
                    lawyerSubset.add(lawyer);
                }

            }

        }

        //Add a dummy element to the first position. This will be replaced by the outline illustration.
        lawyerSubset.add(0, lawyers.get(lawyers.size() - 1));

        fragment.lawyers = lawyerSubset;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_with_recyclerview, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerView.Adapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lawyers);
        recyclerView.setAdapter(recyclerViewAdapter);

        // Create the parallax effect on the outline illustration
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View view = recyclerView.getChildAt(0);
                if(view != null && recyclerView.getChildAdapterPosition(view) == 0)  {
                    view.setTranslationY(-view.getTop() / 3);
                }
            }
        });

        return rootView;
    }

}
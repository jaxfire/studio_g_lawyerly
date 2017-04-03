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

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Lawyer> lawyers;
    MainActivity mainActivity;

    public static ScreenSlidePageFragment newInstance(int position, ArrayList<Lawyer> lawyers, MainActivity mainActivity) {

        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();

        ArrayList<Lawyer> lawyerSubset = new ArrayList<>();

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

        lawyerSubset.add(0, lawyers.get(lawyers.size() - 1)); //Add a dummy element to the first position
        fragment.lawyers = lawyerSubset;
        fragment.mainActivity = mainActivity;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), lawyers);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View view = recyclerView.getChildAt(0);
                if(view != null && recyclerView.getChildAdapterPosition(view) == 0)  {
                    view.setTranslationY(-view.getTop() / 2);
                }
            }
        });

        return rootView;
    }

}
package com.example.lab4news.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4news.Adapter.CategoryAdapter;
import com.example.lab4news.Category;
import com.example.lab4news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Динара on 13.10.2017.
 */

public class CategoryFragment extends Fragment {
    CategoryAdapter categoryAdapter;
    List<Category> categoryList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categoryfragment, container, false);


        for(int i=0; i<6; i++){
            Category category = new Category("IT");
            categoryList.add(category);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        GridLayoutManager llm = new GridLayoutManager(this.getContext(), 2);

        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(categoryAdapter);

        return view;
    }
}


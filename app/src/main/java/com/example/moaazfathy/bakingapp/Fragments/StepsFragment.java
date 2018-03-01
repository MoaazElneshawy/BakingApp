package com.example.moaazfathy.bakingapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chootdev.recycleclick.RecycleClick;
import com.example.moaazfathy.bakingapp.Adapters.StepsListAdapter;
import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Models.Steps;
import com.example.moaazfathy.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoaazFathy on 05-Feb-18.
 */

public class StepsFragment extends Fragment {

    @BindView(R.id.steps_rv)
    RecyclerView mStepsRV;

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    private List<Steps> steps;
    StepsListAdapter adapter;
    private Parcelable layoutManagerSavedState;

    OnStepClickListener onStepClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            layoutManagerSavedState = savedInstanceState.getParcelable(Constants.LIST_STATE);
        }
        setUpRV();
        return view;
    }

    private void setUpRV() {
        mStepsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StepsListAdapter(steps, getActivity());
        mStepsRV.setAdapter(adapter);
        if (layoutManagerSavedState != null) {
            mStepsRV.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
        }
        RecycleClick.addTo(mStepsRV).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                onStepClickListener.onStepClicked(steps.get(position).getDescription(), steps.get(position).getVideoURL(), steps.get(position).getThumbnailURL());
            }
        });
    }


    public interface OnStepClickListener {
        void onStepClicked(String description, String video, String thumbnail);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onStepClickListener = (OnStepClickListener) context;
        } catch (Exception e) {
            Log.e("StepsFragment", e.getMessage());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.LIST_STATE, mStepsRV.getLayoutManager().onSaveInstanceState());
    }

}

package com.example.moaazfathy.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.Models.Steps;
import com.example.moaazfathy.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoaazFathy on 05-Feb-18.
 */

public class StepsDetailsAdapter extends RecyclerView.Adapter<StepsDetailsAdapter.IngredientsViewHolder> {

    List<Steps> steps;
    Context context;

    public StepsDetailsAdapter(List<Steps> steps, Context context) {
        this.steps = steps;
        this.context = context;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_step_detail, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.mStepDescription.setText(steps.get(position).getDescription());
        String video = steps.get(position).getVideoURL();
        if (TextUtils.isEmpty(video) || video.isEmpty()) {
            holder.mStepVideo.setText("No Video");
        } else {
            holder.mStepVideo.setText(video);
        }
    }

    @Override
    public int getItemCount() {
        if (steps.size() > 0)
            return steps.size();
        else
            return 0;
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_video)
        TextView mStepVideo;
        @BindView(R.id.step_description)
        TextView mStepDescription;


        public IngredientsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.example.moaazfathy.bakingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Models.Steps;
import com.example.moaazfathy.bakingapp.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoaazFathy on 07-Feb-18.
 */

public class StepDetailsFragment extends Fragment {


    @BindView(R.id.step_video_player)
    SimpleExoPlayerView mVideoPlayer;
    @BindView(R.id.step_video)
    TextView mStepVideo;
    @BindView(R.id.step_description)
    TextView mStepDescription;
    @BindView(R.id.step_thumbnail)
    ImageView mThumbnail;
    long position;
    private SimpleExoPlayer mPlayer;
    private String description, video, thumbnail;

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideo(String video) {
        this.video = video;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_details, container, false);
        ButterKnife.bind(this, view);
        position = C.TIME_UNSET;
        if (savedInstanceState != null) {
            description = savedInstanceState.getString(Constants.DESCRIPTION);
            video = savedInstanceState.getString(Constants.VIDEO);
            position = savedInstanceState.getLong(Constants.VIDEO_STATE);
            thumbnail = savedInstanceState.getString(Constants.THUMBNAIL);
            Log.e("StepDetFrag , getSave", position + "");
        }
        setupUi();

        return view;
    }

    private void setupUi() {
        if (description != null) {
            mStepDescription.setText(description);
        } else {
            mStepDescription.setText("No Description Added");
        }
        if (video != null) {
            if (TextUtils.isEmpty(video) || video.isEmpty()) {
                mStepVideo.setText("No Video");
                mStepVideo.setVisibility(View.VISIBLE);
                mVideoPlayer.setVisibility(View.GONE);
            } else {
                mVideoPlayer.setVisibility(View.VISIBLE);
                mStepVideo.setVisibility(View.GONE);
                setupMediaPlayer(Uri.parse(video));
            }
        } else {
            mStepVideo.setText("No Video");
            mStepVideo.setVisibility(View.VISIBLE);
            mVideoPlayer.setVisibility(View.GONE);
        }
        if (!(thumbnail == null || TextUtils.isEmpty(thumbnail))) {
            imageLoader(thumbnail);
        }
    }

    private void setupMediaPlayer(Uri uri) {
        if (mPlayer == null) {
            TrackSelector selector = new DefaultTrackSelector();
            LoadControl control = new DefaultLoadControl();
            mPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), selector, control);
            mVideoPlayer.setPlayer(mPlayer);
            Log.e("StepDetFrag , setUp", position + "");
            String user = Util.getUserAgent(getActivity(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getActivity(), user), new DefaultExtractorsFactory()
                    , null, null);
            if (position != C.TIME_UNSET)
                mPlayer.seekTo(position);
            mPlayer.prepare(mediaSource);
            mPlayer.setPlayWhenReady(true);
        }
    }

    private void stopPlayer() {
        if (mPlayer != null) {
            position = mPlayer.getCurrentPosition();
            mPlayer.stop();
            mPlayer.release();
            Log.e("StepDetFrag , Stop", position + "");
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        stopPlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.VIDEO, video);
        outState.putString(Constants.DESCRIPTION, description);
        outState.putString(Constants.THUMBNAIL, thumbnail);
        outState.putLong(Constants.VIDEO_STATE, position);
        Log.e("StepDetFrag , outState", position + "");

    }

    private void imageLoader(String url) {
        try {
            Picasso.with(getActivity())
                    .load(url)
                    .error(R.mipmap.cheef_logo)
                    .into(mThumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        try {
            savedInstanceState.getLong(Constants.VIDEO_STATE);
            savedInstanceState.getString(Constants.VIDEO);
            savedInstanceState.getString(Constants.THUMBNAIL);
            savedInstanceState.getString(Constants.DESCRIPTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

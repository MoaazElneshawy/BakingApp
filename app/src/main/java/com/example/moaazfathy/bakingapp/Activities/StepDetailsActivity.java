package com.example.moaazfathy.bakingapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moaazfathy.bakingapp.Constants;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailsActivity extends AppCompatActivity {

    @BindView(R.id.activity_step_video_player)
    SimpleExoPlayerView mVideoPlayer;
    @BindView(R.id.activity_step_video)
    TextView mNoVideo;
    @BindView(R.id.activity_step_description)
    TextView mDescription;
    @BindView(R.id.thumbnail_imageView)
    ImageView mThumbnail;

    private SimpleExoPlayer mPlayer;
    private String description, video, thumbnail;
    long position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        ButterKnife.bind(this);

        try {
            ActionBar bar = this.getSupportActionBar();
            bar.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        position = C.TIME_UNSET;
        Log.e("ppsi onCreate", "**" + position);
        if (savedInstanceState != null) {
            description = savedInstanceState.getString(Constants.DESCRIPTION);
            video = savedInstanceState.getString(Constants.VIDEO);
            position = savedInstanceState.getLong(Constants.VIDEO_STATE);
            thumbnail = savedInstanceState.getString(Constants.THUMBNAIL);
            Log.e("ppsi saveinstance", "**" + position);

        } else {
            Intent intent = this.getIntent();
            if (intent != null) {
                description = intent.getStringExtra(Constants.DESCRIPTION);
                video = intent.getStringExtra(Constants.VIDEO);
                thumbnail = intent.getStringExtra(Constants.THUMBNAIL);
            }
        }
        if (!(thumbnail == null || TextUtils.isEmpty(thumbnail))) {
            imageLoader(thumbnail);
        }

        Log.e("th", "**" + thumbnail);
        setupUi();
    }

    private void setupUi() {
        if (description != null) {
            mDescription.setText(description);
        }
        if (video != null) {
            if (TextUtils.isEmpty(video) || video.isEmpty()) {
                mNoVideo.setText("No Video");
                mNoVideo.setVisibility(View.VISIBLE);
                mVideoPlayer.setVisibility(View.GONE);
            } else {
                mVideoPlayer.setVisibility(View.VISIBLE);
                mNoVideo.setVisibility(View.GONE);
                setupMediaPlayer(Uri.parse(video));
            }
        }
    }

    private void setupMediaPlayer(Uri uri) {
        if (mPlayer == null) {
            TrackSelector selector = new DefaultTrackSelector();
            LoadControl control = new DefaultLoadControl();
            mPlayer = ExoPlayerFactory.newSimpleInstance(this, selector, control);
            mVideoPlayer.setPlayer(mPlayer);

            String user = Util.getUserAgent(this, "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(this, user), new DefaultExtractorsFactory()
                    , null, null);
            if (position != C.TIME_UNSET)
                mPlayer.seekTo(position);
            Log.e("ppsi setUp", "**" + position);

            mPlayer.prepare(mediaSource);
            mPlayer.setPlayWhenReady(true);
        }
    }

    private void stopPlayer() {
        if (mPlayer != null) {
            position = mPlayer.getCurrentPosition();
            Log.e("ppsi stoppleaye", "**" + position);
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlayer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.DESCRIPTION, description);
        outState.putString(Constants.VIDEO, video);
        outState.putLong(Constants.VIDEO_STATE, position);
        outState.putString(Constants.THUMBNAIL, thumbnail);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        description = savedInstanceState.getString(Constants.DESCRIPTION);
        video = savedInstanceState.getString(Constants.VIDEO);
        position = savedInstanceState.getLong(Constants.VIDEO_STATE);
        thumbnail = savedInstanceState.getString(Constants.THUMBNAIL);

    }


    private void imageLoader(String url) {
        try {
            Picasso.with(this)
                    .load(url)
                    .error(R.mipmap.cheef_logo)
                    .into(mThumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(video == null || TextUtils.isEmpty(video))){
            setupMediaPlayer(Uri.parse(video));
        }
    }

}

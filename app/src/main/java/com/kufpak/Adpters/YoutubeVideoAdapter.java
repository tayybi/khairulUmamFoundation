package com.kufpak.Adpters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.kufpak.CRM.PlayVideo;
import com.kufpak.Others.ServiceUrls;
import com.kufpak.Others.YoutubeVideoModel;
import com.kufpak.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeViewHolder> {
    private static final String TAG = YoutubeVideoAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList;
    public ImageView imageView;


    public YoutubeVideoAdapter(Context context, ArrayList<YoutubeVideoModel> youtubeVideoModelArrayList) {
        this.context = context;
        this.youtubeVideoModelArrayList = youtubeVideoModelArrayList;
    }

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_view, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, final int position) {

        final YoutubeVideoModel youtubeVideoModel = youtubeVideoModelArrayList.get(position);
        holder.setIsRecyclable(false);
        holder.videoTitle.setText(youtubeVideoModel.getTitle());

        Picasso.with(context)
                                .load(youtubeVideoModel.getThumbNail()) // image url goes here
                                .placeholder(imageView.getDrawable())
                                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(view.getContext(), PlayVideo.class);
                intent.putExtra("videoId",youtubeVideoModel.getVideoId());
                view.getContext().startActivity(intent);
            }
        });


        /*  initialize the thumbnail image view , we need to pass Developer Key */
//        holder.videoThumbnailImageView.initialize(ServiceUrls.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
//                //when initialization is sucess, set the video id to thumbnail to load
//                youTubeThumbnailLoader.setVideo(youtubeVideoModel.getVideoId());
//
//                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
//                    @Override
//                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
//                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
//                        youTubeThumbnailLoader.release();
//                    }
//
//                    @Override
//                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
//                        //print or show error when thumbnail load failed
//                        Log.e(TAG, "Youtube Thumbnail Error");
//                    }
//                });
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
//                //print or show error when initialization failed
//                Log.e(TAG, "Youtube Initialization Failure");
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoModelArrayList != null ? youtubeVideoModelArrayList.size() : 0;
    }
    public class YoutubeViewHolder extends RecyclerView.ViewHolder {

//        public YouTubeThumbnailView videoThumbnailImageView;
        public TextView videoTitle;

        public YoutubeViewHolder(View itemView) {
            super(itemView);
//            videoThumbnailImageView = itemView.findViewById(R.id.video_thumbnail_image_view);
            videoTitle = itemView.findViewById(R.id.video_title_label);
            imageView = itemView.findViewById(R.id.ivthumb);
        }
    }
}

////////////

//
//public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.VideoViewHolder> {
//
//
//    List<YouTubeVideos> youtubeVideoList;
//
//    public YoutubeVideoAdapter() {
//    }
//
//    public YoutubeVideoAdapter(List<YouTubeVideos> youtubeVideoList) {
//        this.youtubeVideoList = youtubeVideoList;
//    }
//
//    @Override
//    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.video_view, parent, false);
//
//        return new VideoViewHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(VideoViewHolder holder, int position) {
//
//        holder.ivThumbnail.loadData( youtubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8" );
//        holder.tvDesc.loadData( youtubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8" );
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return youtubeVideoList.size();
//    }
//
//    public class VideoViewHolder extends RecyclerView.ViewHolder{
//
//        WebView videoWeb;
//        public YouTubeThumbnailView;
//        TextView tvDesc;
//
//        public VideoViewHolder(View itemView) {
//            super(itemView);
//
//            ivThumbnail=itemView.findViewById(R.id.yt_thumbnail);
//            tvDesc=itemView.findViewById(R.id.tvDec);
////            videoWeb = (WebView) itemView.findViewById(R.id.videoWebView);
////
////            videoWeb.getSettings().setJavaScriptEnabled(true);
////            videoWeb.setWebChromeClient(new WebChromeClient() {
////
////
////            } );
//        }
//    }
//}

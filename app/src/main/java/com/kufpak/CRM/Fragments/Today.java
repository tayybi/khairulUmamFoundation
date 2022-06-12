package com.kufpak.CRM.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kufpak.Adpters.YoutubeVideoAdapter;
import com.kufpak.Others.ServiceUrls;
import com.kufpak.Others.SingletonVolley;
import com.kufpak.Others.YoutubeVideoModel;
import com.kufpak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Today extends Fragment {

    Boolean isScrolling = false;
    Context context;
    RecyclerView recyclerView;
    int currentItems, totalItems, scrollOutItems;
    String token = "";
    SwipeRefreshLayout swipeRefreshLayout;
    List<YoutubeVideoModel> videoList;
    Dialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();

        init();
    }

    private void init() {
        videoList=new ArrayList<>();
    }


    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(com.kufpak.R.layout.today,null);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        searchVideo(ServiceUrls.dataAPI);

        swipeRefreshLayout=v.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onRefresh() {
                try {
                    searchVideo(ServiceUrls.dataAPI);
                    swipeRefreshLayout.setRefreshing(false);
                }catch (Exception e){
                    Log.i("Exception",""+e);
                }
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return v;
    }

    public void  setVideoRecycler(List list) {

        YoutubeVideoAdapter recyclerTransforList = new YoutubeVideoAdapter(getContext(), (ArrayList<YoutubeVideoModel>) list);
        recyclerTransforList.notifyDataSetChanged();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerTransforList);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    searchVideo(ServiceUrls.dataAPI);
                }
            }
        });
    }

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void searchVideo(String url){
        if(token != ""){
            url= url+ "&pageToken="+token;
        }
        if(token == null){
            return;
        }
        //progressDialog= CheckConnectivity.dialogForProgres(context);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response){
                Log.i("response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.i("jsonobject", String.valueOf(jsonObject));
                 //   JSONArray jsonArray=jsonObject.getJSONArray("customer_info");
                    token=jsonObject.getString("nextPageToken");
                    Log.i("token", token);
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonVideoId = jsonObject1.getJSONObject("id");
                        JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                        JSONObject jsonObjectdefault = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");
                        YoutubeVideoModel videoDetails = new YoutubeVideoModel();
                        String videoid = jsonVideoId.getString("videoId");
                        Log.i(" New Video Id", videoid);
                        videoDetails.setThumbNail(jsonObjectdefault.getString("url"));
                        videoDetails.setTitle(jsonsnippet.getString("title"));
                        videoDetails.setDuration(jsonsnippet.getString("description"));
                        videoDetails.setVideoId(videoid);

                        videoList.add(videoDetails);
                    }
                    setVideoRecycler(videoList);
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
               // progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", ""+error);
                Toast.makeText(context,"Slow Internet Connection",Toast.LENGTH_SHORT).show();
               // progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        SingletonVolley.getInstance(context.getApplicationContext()).addToRequestQueue(stringRequest);
    }

}

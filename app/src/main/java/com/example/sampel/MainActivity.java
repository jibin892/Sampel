package com.example.sampel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cleveroad.fanlayoutmanager.FanLayoutManager;
import com.cleveroad.fanlayoutmanager.FanLayoutManagerSettings;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout location_pik;
    ListAdapter listAdapter;
    private int overallXScroll = 0;
    ArrayList<ListModel> listModels;
    FanLayoutManager mFanLayoutManager;
    private final String[] heading = {"Labs", "Labs", "Nurse", "Labs", "Nurse"};
    private final int[] images = {R.drawable.shape_test, R.drawable.shape_test, R.drawable.shape_test, R.drawable.shape_test, R.drawable.shape_test};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.scrolling_rounded);

        listModels = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ListModel listModel = new ListModel();
            listModel.setHed(heading[i]);
            listModel.setImage(images[i]);
            listModels.add(listModel);
        }
        listAdapter = new ListAdapter(listModels);


//        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);

        FanLayoutManagerSettings fanLayoutManagerSettings = FanLayoutManagerSettings
                .newBuilder(getApplicationContext())
                .withFanRadius(true)
                .withAngleItemBounce(0)
                .withViewWidthDp(315)
                .withViewHeightDp(345)
                .build();
        mFanLayoutManager = new FanLayoutManager(getApplicationContext(), fanLayoutManagerSettings);
        recyclerView.setLayoutManager(mFanLayoutManager);
        mFanLayoutManager.getSelectedItemPosition();
        recyclerView.setHasFixedSize(true);
        recyclerView.setClipToPadding(false);
        recyclerView.setClipChildren(false);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setAdapter(listAdapter);


        //...
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                overallXScroll = overallXScroll + dx;
                View firstVisibleItem = mFanLayoutManager.findViewByPosition(mFanLayoutManager.getSelectedItemPosition());


            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    final int offset = mFanLayoutManager.getSelectedItemPosition();
//                    Toast.makeText(Home.this, String.valueOf(offset), Toast.LENGTH_SHORT).show();

                    Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tilt);
                    recyclerView.startAnimation(rotate);
                } else {
                    // Do something
                }
            }
        });


    }

    static class ListModel {
        private String hed, desc, test;
        int image, laotian;

        public ListModel() {
        }

        public ListModel(String hed, String desc, String test, int image,int laotian) {
            this.hed = hed;
            this.desc = desc;
            this.test = test;
            this.image = image;
            this.laotian =laotian;
        }

        public String getHed() {
            return hed;
        }

        public void setHed(String hed) {
            this.hed = hed;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public int getLaotian() {
            return laotian;
        }

        public void setLaotian(int laotian) {
            this.laotian = laotian;
        }
    }

    static class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

        ArrayList<ListModel> modelArrayList;

        public ListAdapter(ArrayList<ListModel> modelArrayList) {
            this.modelArrayList = modelArrayList;

        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView mImage;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                mImage = itemView.findViewById(R.id.image);

            }
        }
        View view;
        @NonNull
        @Override
        public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scrolling_home_rounded, parent, false);



            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
            ListModel listModel = modelArrayList.get(position);
            holder.mImage.setImageResource(listModel.getImage());





//              if(position>-1){
//
//                  holder.lottieAnimationView.playAnimation();
//                  holder.lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
//
//
//              }

        }
        @Override
        public int getItemCount() {
            return modelArrayList.size();
        }

    }
}


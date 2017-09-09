package in.vit.yearbook.View.Introduction;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import in.vit.yearbook.R;

public class SplashGridAdapter extends RecyclerView.Adapter<SplashGridAdapter.GridViewHolder>{

    private List<Integer> imagesList = new ArrayList<>() ;
    private Context context ;

    public SplashGridAdapter(Context context) {
        this.context = context ;
        initializeArray() ;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_splash_grid, parent, false) ;
        return new GridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {

        holder.ivMemory.setImageDrawable(context.getResources().getDrawable(imagesList.get(position)));

        setScaleAnimation(holder.itemView, position);
    }

    private void setScaleAnimation(View view, int position) {

        Random random = new Random() ;

        Log.i("TAG", "setScaleAnimation: " + random.nextFloat());
        ScaleAnimation anim = new ScaleAnimation(random.nextFloat(), (float)(random.nextFloat()+1.0), random.nextFloat(), (float)(random.nextFloat()+1.0), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(5000);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMemory ;

        public GridViewHolder(View itemView) {
            super(itemView);

            ivMemory = (ImageView) itemView.findViewById(R.id.item_splash_grid_image) ;
        }

    }

    private void initializeArray() {
        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;

        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;

        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;

        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;

        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;
        imagesList.add(R.drawable.yb2017_cover) ;
        notifyDataSetChanged();
    }


}

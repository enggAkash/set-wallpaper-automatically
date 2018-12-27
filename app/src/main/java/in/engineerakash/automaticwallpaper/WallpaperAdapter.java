package in.engineerakash.automaticwallpaper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperViewHolder> implements ItemTouchHelperAdapter {

    TypedArray wallpaperArr;
    Context context;
    RequestOptions glideOptions;

    public WallpaperAdapter(Context context, TypedArray wallpaperArr) {
        this.context = context;
        this.wallpaperArr = wallpaperArr;

        glideOptions = new RequestOptions()
                .placeholder(R.drawable.wallpaper_placeholder)
                .error(R.drawable.wallpaper_broken);
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wallpaper_layout, viewGroup, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder wallpaperViewHolder, int i) {

        Drawable resource = wallpaperArr.getDrawable(i);

        Glide.with(context)
                .setDefaultRequestOptions(glideOptions)
                .load(resource)
                .into(wallpaperViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return wallpaperArr.length();
    }

    @Override
    public void onItemDismiss(int position) {
//        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(mItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(mItems, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}

class WallpaperViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.wallpaper_iv);
    }
}

package in.engineerakash.automaticwallpaper;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView wallpaperRv;
    private Spinner intervalSpinner;
    private Button setWallpaperBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wallpaperRv = findViewById(R.id.wallpaper_rv);
        intervalSpinner = findViewById(R.id.interval_time_spinner);
        setWallpaperBtn = findViewById(R.id.set_wallpaper_btn);

        setWallpaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean wallpaperSetSuccessfully = setWallpaper();
                if (wallpaperSetSuccessfully)
                    Toast.makeText(MainActivity.this, "Wallpaper Set Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Failed to Set Wallpaper", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean setWallpaper() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SET_WALLPAPER) != PackageManager.PERMISSION_GRANTED) {

            this.requestPermissions(new String[]{Manifest.permission.SET_WALLPAPER}, 1);

            return false;
        }


        String selectedInterval = (String) intervalSpinner.getSelectedItem();

        Log.d(TAG, "setWallpaper: " + selectedInterval);

        switch (selectedInterval) {
            case "1 Hour":

                break;
            case "6 Hour":

                break;
            case "8 Hour":

                break;
            case "12 Hour":

                break;
            case "24 Hour":

                break;
            default:
                return false;
        }

        Drawable drawable = getResources().obtainTypedArray(R.array.wallpapers).getDrawable(0);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        // image from res/drawable

        WallpaperManager manager = WallpaperManager.getInstance(this);

        try {
            manager.setBitmap(bitmap);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMotivation();
    }

    private void loadMotivation() {
        TypedArray wallpapers = getResources().obtainTypedArray(R.array.wallpapers);

        WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(this, wallpapers);
        wallpaperRv.setLayoutManager(new LinearLayoutManager(this));
        wallpaperRv.setAdapter(wallpaperAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    setWallpaper();
                else
                    Toast.makeText(this, "We need permission to set Wallpaper", Toast.LENGTH_LONG).show();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    class WallpaperAdapter extends RecyclerView.Adapter<WallpaperViewHolder> {

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
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.wallpaper_iv);
        }
    }

}

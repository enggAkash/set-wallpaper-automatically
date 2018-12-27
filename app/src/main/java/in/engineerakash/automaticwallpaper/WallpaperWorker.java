package in.engineerakash.automaticwallpaper;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.IOException;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WallpaperWorker extends Worker {
    private static final String TAG = "akt";

    Context context;

    public WallpaperWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: ");
        // Do the work here--in this case, compress the stored images.
        // In this example no parameters are passed; the task is
        // assumed to be "compress the whole library."
        setWallpaper();

        // Indicate success or failure with your return value:
        return Result.success();

        // (Returning Result.retry() tells WorkManager to try this task again
        // later; Result.failure() says not to try again.)


    }

    private boolean setWallpaper() {


        String selectedInterval = (String) "";// intervalSpinner.getSelectedItem();

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
//            default:
//                return false;
        }

        int index = (int) (1 + (6 * Math.random()));
        Log.d(TAG, "setWallpaper: ab kiski baari " + index);

        Drawable drawable = context.getResources().obtainTypedArray(R.array.wallpapers).getDrawable(index);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        // image from res/drawable

        WallpaperManager manager = WallpaperManager.getInstance(context);

        try {
            manager.setBitmap(bitmap);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


}

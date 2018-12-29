package in.engineerakash.automaticwallpaper.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import in.engineerakash.automaticwallpaper.model.Wallpaper;

import static android.content.Context.MODE_PRIVATE;

public final class Helper {
    private static final String TAG = "Helper";

    public static boolean isFirstTime(Context context) {
        Log.d(TAG, "isFirstTime: ");
        SharedPreferences sp = context.getSharedPreferences(Constant.wallpaperSp, MODE_PRIVATE);
        return sp.getBoolean(Constant.isFirstTime, true);
    }

    public static void putDrawableToInternalFileStorage(Context context) {
        Log.d(TAG, "putDrawableToInternalFileStorage: ");

        int[] wallpaperDrawables = Constant.inBuiltWallpaper;

        ImageSaver imageSaver = new ImageSaver(context);
        imageSaver.setDirectoryName(Constant.internalDirectoryName);

        SharedPreferences sp = context.getSharedPreferences(Constant.wallpaperSp, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Set<String> wPSet = new HashSet<String>();
        Wallpaper wp = null;

        for (int i = 0; i < wallpaperDrawables.length; i++) {
            Drawable drawable = context.getDrawable(wallpaperDrawables[i]);
            Bitmap bitmap = null;
            if (drawable != null)
                bitmap = ((BitmapDrawable) drawable).getBitmap();

            wp = new Wallpaper(i, "Motivation " + (i + 1), "",
                    "drawable" + i + ".jpg", null, false);

            imageSaver
                    .setFileName(wp.getFilePath())
                    .save(bitmap);

            wPSet.add(wp.getFilePath());
        }

        editor.putStringSet(Constant.wallpaperSet, wPSet);
        editor.apply();
    }

}

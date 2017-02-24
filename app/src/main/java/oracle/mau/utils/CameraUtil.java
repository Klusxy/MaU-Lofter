package oracle.mau.utils;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.format.DateFormat;
import android.util.Log;

public final class CameraUtil {

    private static final String TAG = "MicroMsg.SDK.CameraUtil";

    public static String filePath = null;

    private CameraUtil() {
        // can't be instantiated
    }

    public static boolean takePhotoLarger(final Activity activity, final int cmd) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.i("TestFile",
                    "SD card is not avaiable/writeable right now.");
            return false;
        }
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        filePath = dir + name;
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File cameraDir = new File(dir);
        if (!cameraDir.exists()) {
            return true;
        }

        final File file = new File(filePath);
        final Uri outputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        try {
            activity.startActivityForResult(intent, cmd);

        } catch (final ActivityNotFoundException e) {
            return false;
        }
        return true;
    }

    public static String getResultPhotoPathLarger() {
        return filePath;
    }

    public static void takePhoto(final Activity activity, final int cmd) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, cmd);
    }

    public static String getResultPhotoPath(Context context, final Intent intent) {
        return resolvePhotoFromIntent(context, intent);
    }

    public static String resolvePhotoFromIntent(final Context ctx,
                                                final Intent data) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.i("TestFile", "SD card is not avaiable/writeable right now.");
            return "";
        }
        String name = new DateFormat().format("yyyyMMdd_hhmmss",
                Calendar.getInstance(Locale.CHINA))
                + ".jpg";
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
        System.out.println(bitmap.getWidth() + " " + bitmap.getHeight());
        FileOutputStream b = null;

        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsoluteFile() + "");
        if (!file.exists())
            file.mkdirs();// 创建文件夹
        String fileName = Environment.getExternalStorageDirectory()
                .getAbsoluteFile() + "/" + name;

        try {
            b = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

}


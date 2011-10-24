package com.call4paperz.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.call4paperz.R;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class BitmapGenerator extends AsyncTask<String, ProgressDialog, Bitmap> {

    private static HashMap<String, Bitmap> imagens = new HashMap<String, Bitmap>();

    private final Context context;
    private final ImageView imageView;
    private Bitmap defaultBitmap;

    public BitmapGenerator(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
        defaultBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_image);

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String imageURL = params[0];

        if( ( imageURL == null ) || (imageURL.trim().equals("")) || imageURL.contains("no-image") ) {
            return defaultBitmap;
        }

        if ( imagens.containsKey(params[0]) ) {
            return imagens.get(params[0]);
        }

        InputStream is = null;
        try {
            is = new URL(imageURL).openStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(is);
            if (decodeStream == null) {
                throw new Exception("decodeStream returned null");
            }
            imagens.put(params[0], decodeStream);
            return decodeStream;
        } catch (Exception e) {
            e.printStackTrace();        // TODO
        } finally {
            try {
                if( is != null ) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();    // TODO
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (result != null) {
            imageView.setImageBitmap(result);
        }
    }

}

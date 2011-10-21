package com.call4paperz.util;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class BitmapGenerator extends AsyncTask<String, ProgressDialog, Bitmap> {

    private final ImageView imageView;

    public BitmapGenerator(ImageView imageView) {
        this.imageView = imageView;

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String imageURL = params[0];

        if( ( imageURL == null ) || (imageURL.trim().equals("")) || imageURL.contains("no-image") ) {
            // TODO Defaul image
        }

        InputStream is = null;
        try {
            is = new URL(imageURL).openStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(is);
            if (decodeStream == null) {
                throw new Exception("decodeStream returned null");
            }
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

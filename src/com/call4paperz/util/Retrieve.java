package com.call4paperz.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.call4paperz.exception.NotConnectionException;
import com.call4paperz.exception.RetrieveException;
import com.call4paperz.model.Event;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class Retrieve {

    private Context context;
    private final String EVENTS_URL = "http://call4paperz.com/events.jsonp";

    public Retrieve(Context context) {
        this.context = context;
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    private String getDataFrom(String url) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = null;
        InputStream is = null;
        StringBuffer sb = new StringBuffer();
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                is = entity.getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return sb.toString();

    }

    public List<Event> events() throws NotConnectionException, RetrieveException {

        if (!isOnline()) {
            throw new NotConnectionException();
        }

        List<Event> events = new ArrayList<Event>();

        try {

            String data = getDataFrom(EVENTS_URL).replaceAll("[(]", "").replaceAll("[)]", "");

            JSONArray jsonData = new JSONArray(data);

            for (int i = 0; i < jsonData.length(); i++) {
                Event event = new Event().fromJSON(jsonData.getJSONObject(i));
                events.add(event);
            }

        } catch (JSONException e) {
            throw new RetrieveException(e.getMessage());
        }

        return events;

    }

}

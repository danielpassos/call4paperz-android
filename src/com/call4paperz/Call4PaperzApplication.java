package com.call4paperz;

import android.app.Application;
import com.call4paperz.model.Event;
import com.google.gson.GsonBuilder;
import org.jboss.aerogear.android.Pipeline;
import org.jboss.aerogear.android.impl.pipeline.PipeConfig;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

public class Call4PaperzApplication extends Application {

    private Pipeline pipeline;

    @Override
    public void onCreate() {
        super.onCreate();

        try {

            URL baseURL = new URL("http://call4paperz.com");

            GsonBuilder gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES);

            pipeline = new Pipeline(baseURL);

            PipeConfig pipeConfigEvent = new PipeConfig(baseURL, Event.class);
            pipeConfigEvent.setName("events");
            pipeConfigEvent.setEndpoint("events.jsonp");
            pipeConfigEvent.setGsonBuilder(gsonBuilder);
            pipeline.pipe(Event.class, pipeConfigEvent);

        } catch (MalformedURLException e) {
            //
        }

    }

    public Pipeline getPipeline() {
        return pipeline;
    }
}

package com.call4paperz.android.model;

import java.io.Serializable;

public class Picture implements Serializable {

    private PictureUrl cropped;
    private PictureUrl big;
    private String url;

    public PictureUrl getCropped() {
        return cropped;
    }

    public void setCropped(PictureUrl cropped) {
        this.cropped = cropped;
    }

    public PictureUrl getBig() {
        return big;
    }

    public void setBig(PictureUrl big) {
        this.big = big;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static final class PictureUrl implements Serializable {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}

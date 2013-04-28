package com.call4paperz.android.exception;

import android.util.Log;

public class RetrieveException extends Exception {

    public RetrieveException(String message) {
        Log.e("Call4paper4Android", message);
    }

}

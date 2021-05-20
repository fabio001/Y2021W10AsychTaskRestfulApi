package com.iatli.y2021w10asychtaskrestfulapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("deprecation")
public class FakeDownloader extends AsyncTask<String, Integer, String> {
    private static String TAG= "ASYNCW10";
    private AppCompatActivity mainActivity;
    private ProgressDialog progressDialog;


    public FakeDownloader(AppCompatActivity mActivity){
        mainActivity = mActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "OnPreExecute");

        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setTitle("Downloader");
        progressDialog.setMessage("Fake Download");
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.d(TAG, "DoInBackground with param: " + strings[0]);
        //suppose that code pieces to download something from internet
        int i =0;
        while (i<10){
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i*10);
            i++;
        }


        return strings[0] + " doinbackground";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        int value = values[0];

        Log.d(TAG, "publishUpdate:" + value);

        progressDialog.setProgress(value);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute");
        progressDialog.dismiss();
        Log.d(TAG, "Result is :" + s);
    }
}

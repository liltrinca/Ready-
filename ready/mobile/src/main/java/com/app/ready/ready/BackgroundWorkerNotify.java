package com.app.ready.ready;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BackgroundWorkerNotify extends AsyncTask<String, Void, String> {
    Context context;
    public boolean stopNotify = false;

    BackgroundWorkerNotify (Context ctx) {
        context = ctx;
    }


    @Override
    protected String doInBackground(String... params) {
            String retrieveUrl = "http://nelfabbri.com/ready/Placar/aut.php";
            String codCliente = params[0];
            try {
                URL url = new URL(retrieveUrl + "?" + codCliente);
                HttpURLConnection httpConex = (HttpURLConnection) url.openConnection();
                httpConex.setRequestMethod("POST");
                httpConex.setDoOutput(true);
                httpConex.setDoInput(true);
                InputStream inputStream = httpConex.getInputStream();
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = buffReader.readLine()) != null) {
                    result += line;
                }
                buffReader.close();
                inputStream.close();
                httpConex.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
            if (result.equals("1")) {
                stopNotify = true;
                notification();
            }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void notification() {
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context);
        nBuilder.setSmallIcon(R.drawable.img_notification);
        nBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        nBuilder.setContentTitle("Ready!");
        nBuilder.setContentText("Your order is Ready!");

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(1, nBuilder.build());
    }

    public boolean getStopNotify(){
        return this.stopNotify;
    }
}

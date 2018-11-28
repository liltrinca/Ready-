package com.app.ready.ready;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker (Context ctx) {
        context = ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        //Login
        String type = params[0];
        String loginUrl = "http://nelfabbri.com/ready/login.php";
        if(type.equals("login")){
            try {
                String username = params[1];
                String pass = params[2];
                URL url = new URL(loginUrl);
                HttpURLConnection httpConex = (HttpURLConnection) url.openConnection();
                httpConex.setRequestMethod("POST");
                httpConex.setDoOutput(true);
                httpConex.setDoInput(true);
                OutputStream outStream = httpConex.getOutputStream();
                BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        +URLEncoder.encode("pass", "UTF-8")+"="+URLEncoder.encode(pass, "UTF-8");
                buffWriter.write(post_data);
                buffWriter.flush();
                buffWriter.close();
                outStream.close();
                InputStream inputStream = httpConex.getInputStream();
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while((line = buffReader.readLine()) != null ){
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
        }
        //Login
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //algo
            }
        });
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

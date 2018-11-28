package com.app.ready.ready;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityQRSite extends AppCompatActivity {
    private WebView wbViewQR;
    public String readURL;
    public String codCliente;
    public boolean stop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_site);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Camera Scan");
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                if (URLUtil.isValidUrl(result.getContents())) {
                    readURL = result.getContents();
                    int start = readURL.indexOf("?");
                    codCliente = readURL.substring(start + 1);
                    toast(codCliente);
                    toast(readURL);

                    wbViewQR = (WebView) findViewById(R.id.wbView);
                    wbViewQR.setWebViewClient(new MyBrowser());
                    wbViewQR.getSettings().setLoadsImagesAutomatically(true);
                    wbViewQR.getSettings().setJavaScriptEnabled(true);
                    wbViewQR.getSettings().setDisplayZoomControls(false);
                    wbViewQR.loadUrl(readURL);

                    //notification();
                    //BackgroundWorkerNotify bNotify = new BackgroundWorkerNotify(wbViewQR.getContext());
                    //bNotify.execute(readURL);
                    callAsynchronousTask();
                } else {
                    toast("O código fornecido não é uma URL");
                    toast("Código fornecido: " + result.getContents());
                    Intent backToHome = new Intent(ActivityQRSite.this, MainActivity.class);
                    startActivity(backToHome);
                }
            } else {
                toast("Não recebi nenhum código");
                Intent backToHome = new Intent(ActivityQRSite.this, MainActivity.class);
                startActivity(backToHome);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private class MyBrowser extends WebViewClient {
        public boolean overrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        final Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            BackgroundWorkerNotify bNotify = new BackgroundWorkerNotify(ActivityQRSite.this);
                            if (bNotify.getStopNotify() == true) {
                                bNotify.cancel(true);
                            } else {
                                bNotify.execute(codCliente);
                            }
                        } catch (Exception e) {

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 20000); //execute in every 15s
    }
}

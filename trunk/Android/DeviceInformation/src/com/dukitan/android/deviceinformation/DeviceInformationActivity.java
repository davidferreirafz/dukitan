package com.dukitan.android.deviceinformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeviceInformationActivity extends Activity
{
    private TextView screenSize;
    private TextView screenDensity;
    private TextView screenDensityDPI;
    private TextView country;
    private TextView language;
    private TextView osVersion;
    private TextView product;
    HttpResponse     response;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        screenSize = (TextView) findViewById(R.id.ScreenSize);
        screenDensity = (TextView) findViewById(R.id.ScreenDensity);
        screenDensityDPI = (TextView) findViewById(R.id.ScreenDPI);
        country = (TextView) findViewById(R.id.Country);
        language = (TextView) findViewById(R.id.Language);
        osVersion = (TextView) findViewById(R.id.OsVersion);
        product = (TextView) findViewById(R.id.Product);

        info();

        final Button ok = (Button) findViewById(R.id.buttonPublish);

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                publicar();
            }
        });

    }

    protected void info()
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Configuration c = getApplicationContext().getResources().getConfiguration();

        country.setText(c.locale.getDisplayCountry());
        language.setText(c.locale.getDisplayLanguage());
        osVersion.setText(android.os.Build.VERSION.SDK + ";" + android.os.Build.VERSION.CODENAME + ";" + android.os.Build.VERSION.RELEASE);

        product.setText(android.os.Build.MANUFACTURER + ";" + android.os.Build.MODEL + ";" + android.os.Build.BOARD);

        screenSize.setText(metrics.widthPixels + "x" + metrics.heightPixels);
        screenDensity.setText(metrics.density + "");
        screenDensityDPI.setText(metrics.densityDpi + "");
    }

    protected void publicar()
    {

        ProgressDialog dialog = ProgressDialog.show(this, "", "Publish. Please wait...", true);

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://deviceinformation.dukitan.com/coleta.php");

        try { // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

            nameValuePairs.add(new BasicNameValuePair("screenSize", screenSize.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("screenDensityDPI", screenDensityDPI.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("screenDensity", screenDensity.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("country", country.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("language", language.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("product", product.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("osVersion", osVersion.getText().toString()));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            Log.e(getLocalClassName(), e.getMessage());
        } catch (IOException e) {
            Log.e(getLocalClassName(), e.getMessage());
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        dialog.dismiss();

        if (response.getStatusLine().getStatusCode() != 200) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialogError);

            AlertDialog alert = builder.create();
            alert.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialogSuccess);

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.menu_about:
                showCustomDialog(R.layout.about, R.string.dialogAboutTitle);
                return true;

            case R.id.menu_credit:
                showCustomDialog(R.layout.credit, R.string.dialogCreditTitle);
                return true;
        }

        return false;
    }

    private void showCustomDialog(int layoutResID, int titleResID)
    {

        final Dialog dialog = new Dialog(this);

        dialog.setContentView(layoutResID);

        dialog.setTitle(titleResID);

        final Button ok = (Button) dialog.findViewById(R.id.button_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
package com.dukitan.android.deviceinformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dukitan.android.framework.Application;

public class DeviceInformationActivity extends Application
{
    private TextView screenSize;
    private TextView screenDensity;
    private TextView screenDensityDPI;
    private TextView country;
    private TextView language;
    private TextView osVersion;
    private TextView product;
    HttpResponse     response;
    private String   DI_UID = "";

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

        createUID();
    }

    protected void info()
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Configuration c = getApplicationContext().getResources().getConfiguration();

        country.setText(c.locale.getDisplayCountry() + ";" + c.locale.getISO3Country());
        language.setText(c.locale.getDisplayLanguage() + ";" + c.locale.getISO3Language());
        osVersion.setText(android.os.Build.VERSION.SDK + ";" + android.os.Build.VERSION.CODENAME + ";" + android.os.Build.VERSION.RELEASE);
        product.setText(android.os.Build.MANUFACTURER + ";" + android.os.Build.MODEL + ";" + android.os.Build.BOARD + ";" + android.os.Build.PRODUCT
                + ";" + android.os.Build.ID);
        screenSize.setText(metrics.widthPixels + "x" + metrics.heightPixels);
        screenDensity.setText(metrics.density + "");
        screenDensityDPI.setText(metrics.densityDpi + "");
    }

    protected void publicar()
    {
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
            nameValuePairs.add(new BasicNameValuePair("DI_UID", DI_UID));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        if ((response == null) || (response.getStatusLine().getStatusCode() != 200)) {
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

    private void createUID()
    {
        final String PREFS_NAME = "DeviceInformationFile";

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        DI_UID = settings.getString("DI_UID", "");

        if ((DI_UID == null) || ("".equals(DI_UID))) {

            Calendar cal = Calendar.getInstance();
            StringBuffer uid = new StringBuffer();
            uid.append(cal.get(Calendar.YEAR));
            uid.append(cal.get(Calendar.MONTH));
            uid.append(cal.get(Calendar.DAY_OF_MONTH));
            uid.append(cal.getTimeInMillis());
            DI_UID = uid.toString();

            SharedPreferences.Editor editor = settings.edit();
            editor.putString("DI_UID", DI_UID);

            // Commit the edits!
            editor.commit();
        }

    }
}
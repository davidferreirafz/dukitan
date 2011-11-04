package com.dukitan.android.framework;

import com.dukitan.android.deviceinformation.R;

import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Application extends Activity
{

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

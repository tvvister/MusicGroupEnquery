package com.maintwister.musicgroupfile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Andrey on 19.07.2016.
 */
public class AboutDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.aboutText)
                .setPositiveButton(R.string.positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AboutDialog.this.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }
}

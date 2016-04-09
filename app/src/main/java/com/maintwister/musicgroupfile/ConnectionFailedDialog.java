package com.maintwister.musicgroupfile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.maintwister.musicgroupfile.model.ICallback;

/**
 * Created by Andrey on 08.04.2016.
 */

public class ConnectionFailedDialog extends DialogFragment {
    private ICallback replyCommand;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.ConnectionFailedMessage)
                .setPositiveButton(R.string.replyButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ConnectionFailedDialog.this.dismiss();
                        replyCommand.handle(null);
                    }
                })
                .setNegativeButton(R.string.CancelButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ConnectionFailedDialog.this.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public void setReplyCommand(ICallback replyCommand) {
        this.replyCommand = replyCommand;
    }
}

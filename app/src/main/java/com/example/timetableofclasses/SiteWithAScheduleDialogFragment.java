package com.example.timetableofclasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SiteWithAScheduleDialogFragment extends DialogFragment  {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Что именно вас интересует?")
                .setView(R.layout.site_with_a_schedule_dialog)
                .setNegativeButton("Закрыть", null)
                .create();
    }
}
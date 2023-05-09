package com.example.wits_academy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class enrollment_password extends AppCompatDialogFragment {

    EditText password;
    Listner listner;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dailog = inflater.inflate(R.layout.enrrollment_password, null);


        builder.setView(dailog)
                .setTitle("Please Enter the Password Provide by Teacher")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ENROLL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String course_password = password.getText().toString();
                        listner.take(course_password);
                    }
                });
        password = dailog.findViewById(R.id.course_password);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner = (Listner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Something went wrong");
        }
    }

    public interface Listner{
        void take(String course_password);
    }
}

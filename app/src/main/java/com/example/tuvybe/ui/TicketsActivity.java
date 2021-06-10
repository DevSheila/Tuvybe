package com.example.tuvybe.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.tuvybe.R;

public class TicketsActivity extends DialogFragment {
    private RadioGroup surveyRadioGroup;
    private ExampleDialogListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_tickets, null);

        builder.setView(view)
                .setTitle("Pick Number Of Tickets")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dismiss();
                    }
                }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {

                int selectedId = surveyRadioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(getContext(), "Please Pick Number Of Tickets", Toast.LENGTH_LONG).show();
                    Log.i("value",selectedId +"");

                }else{

                    RadioButton radioButton = (RadioButton) surveyRadioGroup.findViewById(selectedId);
                    String num_tickets = radioButton.getText().toString();

                    surveyRadioGroup.clearCheck();
                    listener.applyText(num_tickets);

                }

            }
        });

        surveyRadioGroup= view.findViewById(R.id.ratingRadioGroup);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener =(ExampleDialogListener) context;

        }catch(ClassCastException  e){
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyText(String num_tickets);
    }
}

package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class EditDialog extends Dialog {

    private EditText mEditText1, mEditText2;
    private Button mButtonConfirm, mButtonCancel;

    private String[] mItem;
    private OnEditCompleteListener mListener;

    public EditDialog(@NonNull Context context, String[] item) {
        super(context);
        mItem = item;
    }

    public void setOnEditCompleteListener(OnEditCompleteListener listener) {
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_dialog);

        mEditText1 = findViewById(R.id.editText1);
        mEditText2 = findViewById(R.id.editText2);
        mButtonConfirm = findViewById(R.id.buttonConfirm);
        mButtonCancel = findViewById(R.id.buttonCancel);

        mEditText1.setText(mItem[0]);
        mEditText2.setText(mItem[1]);

        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = mEditText1.getText().toString().trim();
                String input2 = mEditText2.getText().toString().trim();
                if (!input1.isEmpty() && !input2.isEmpty()) {
                    mItem[0] = input1;
                    mItem[1] = input2;
                    if (mListener != null) {
                        mListener.onEditComplete(mItem);
                    }
                    dismiss();
                }
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface OnEditCompleteListener {
        void onEditComplete(String[] item);
    }
}
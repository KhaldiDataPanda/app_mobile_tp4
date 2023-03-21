package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText1, mEditText2;
    private Button mButtonAdd;
    private ListView mListView;
    private ArrayList<String[]> mData;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = findViewById(R.id.editText1);
        mEditText2 = findViewById(R.id.editText2);
        mButtonAdd = findViewById(R.id.buttonAdd);
        mListView = findViewById(R.id.listView);

        mData = new ArrayList<>();
        mAdapter = new CustomAdapter(this, R.layout.list_item, mData);
        mListView.setAdapter(mAdapter);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = mEditText1.getText().toString().trim();
                String input2 = mEditText2.getText().toString().trim();
                if (!input1.isEmpty() && !input2.isEmpty()) {
                    String[] item = new String[]{input1, input2};
                    mData.add(item);
                    mAdapter.notifyDataSetChanged();
                    mEditText1.setText("");
                    mEditText2.setText("");
                }
            }
        });
    }
}
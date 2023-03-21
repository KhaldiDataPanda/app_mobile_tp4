package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText1, mEditText2;
    private Button mButtonAdd;
    private ListView mListView;
    private ArrayList<String[]> mData;
    private CustomAdapter mAdapter;

    private ArrayList<ClipData.Item> listsHolder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = findViewById(R.id.editText1);
        mEditText2 = findViewById(R.id.editText2);
        mButtonAdd = findViewById(R.id.buttonAdd);
        mListView = findViewById(R.id.listView);

        listsHolder= new ArrayList<>();
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

    //on create option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.options_list, menu);
        return true;
    }

    //on click option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.create_new_list) {
            listsHolder = getItemList();
            // print list holder
            for (int i = 0; i < listsHolder.size(); i++) {
                Log.d("listHolder", (String) listsHolder.get(i).getText());
            }
            listsHolder = new ArrayList<>();
            Toast.makeText(this, "New list created", Toast.LENGTH_SHORT).show();
            String[] item1 = new String[]{"New Item", "New Item"};
            //mData.clear();
            //mData.add(item1);
            mAdapter.notifyDataSetChanged();
        }
        else if (item.getItemId() == R.id.clear_list_item){
            mData.clear();
            mAdapter.notifyDataSetChanged();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("About");
            builder.setMessage("This is a simple list app");
            builder.setPositiveButton("OK", null);
            builder.show();

        }
        return true;
    }

    private ArrayList<ClipData.Item> getItemList() {
        ArrayList<ClipData.Item> listsHolder = new ArrayList<>();
        for (int i = 0; i < mListView.getCount(); i++) {
            View view = mListView.getChildAt(i);
            EditText nameEditText = view.findViewById(R.id.textView1);
            EditText quantityEditText = view.findViewById(R.id.textView2);
            String name = nameEditText.getText().toString();
            int quantity = Integer.parseInt(quantityEditText.getText().toString());
            ClipData.Item item = new ClipData.Item(name, String.valueOf(quantity));
            listsHolder.add(item);

        }
        return listsHolder;
    }

}

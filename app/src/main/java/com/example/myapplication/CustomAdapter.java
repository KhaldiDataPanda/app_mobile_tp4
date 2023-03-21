package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String[]> mList;
    private OnItemRemovedListener mRemoveListener;
    private OnItemEditedListener mEditListener;

    public CustomAdapter(Context context, int list_item, ArrayList<String[]> list) {
        mContext = context;
        mList = list;
    }

    public void setOnItemRemovedListener(OnItemRemovedListener listener) {
        mRemoveListener = listener;
    }

    public void setOnItemEditedListener(OnItemEditedListener listener) {
        mEditListener = listener;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.textView1 = convertView.findViewById(R.id.textView1);
            holder.textView2 = convertView.findViewById(R.id.textView2);
            holder.buttonRemove = convertView.findViewById(R.id.imageButtonRemove);
            holder.buttonEdit = convertView.findViewById(R.id.imageButtonEdit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String[] item = mList.get(position);
        holder.textView1.setText(item[0]);
        holder.textView2.setText(item[1]);

        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(item);
                notifyDataSetChanged();
                if (mRemoveListener != null) {
                    mRemoveListener.onItemRemoved();
                }
            }
        });

        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDialog editDialog = new EditDialog(mContext, item);
                editDialog.setOnEditCompleteListener(new EditDialog.OnEditCompleteListener() {
                    @Override
                    public void onEditComplete(String[] item) {
                        notifyDataSetChanged();
                        if (mEditListener != null) {
                            mEditListener.onItemEdited();
                        }
                    }
                });
                editDialog.show();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView textView1;
        TextView textView2;
        ImageButton buttonRemove;
        ImageButton buttonEdit;
    }

    public interface OnItemRemovedListener {
        void onItemRemoved();
    }

    public interface OnItemEditedListener {
        void onItemEdited();
    }
}
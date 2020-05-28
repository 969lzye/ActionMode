package com.example.actionmode;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Item> list;
    Context context;
    public MyAdapter(List<Item> list,Context context){
        this.list=list;
        this.context=context;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolader viewHolader;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.list_item,null);
            viewHolader=new ViewHolader();
            viewHolader.imageView=convertView.findViewById(R.id.list_image);
            viewHolader.textView=convertView.findViewById(R.id.list_text);
            convertView.setTag(viewHolader);
        }
        else{
            viewHolader= (ViewHolader) convertView.getTag();
        }
        viewHolader.textView.setText(list.get(position).getName());
        if(list.get(position).isBo()==true){
            viewHolader.textView.setBackgroundColor(Color.RED);
        }
        else
            viewHolader.textView.setBackgroundColor(Color.WHITE);
        return convertView;
    }
    public static void test(){
        ;
    }
    class ViewHolader{
        ImageView imageView;
        TextView textView;
    }
}

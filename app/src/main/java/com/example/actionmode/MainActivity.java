package com.example.actionmode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Item> list;
    private BaseAdapter adapter;
    private String[] content={"One","Two","Three","Four","Five"};
    //private int imageView=R.mipmap.ic_launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        list=new ArrayList<Item>();
        for (int i = 0; i < content.length; i++) {
            list.add(new Item(content[i],false));
        }
        adapter=new MyAdapter(list,MainActivity.this);
        listView = findViewById(R.id.list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setAdapter(adapter);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int num=0;
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if(checked==true){
                    list.get(position).setBo(true);
                    adapter.notifyDataSetChanged();
                    num++;
                }
                else{
                    list.get(position).setBo(false);
                    adapter.notifyDataSetChanged();
                    num--;
                }
                mode.setTitle(num+" selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                getMenuInflater().inflate(R.menu.context_menu,menu);
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
            public void selectAll(){
                for(int i=0;i<content.length;i++){
                    listView.setItemChecked(i,true);
                    list.get(i).setBo(true);
                }
            }
            public void refresh(){
                for(int i=0;i<content.length;i++){
                    list.get(i).setBo(false);
                }
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.all:
                        selectAll();
                        num=5;
                        mode.setTitle(num+" selected");
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.delete:
                        List<Item> list1=new ArrayList<>();
                        for (int i=0;i<content.length;i++){
                            if (list.get(i).isBo()==true) {
                                list1.add(list.get(i));
                                listView.setItemChecked(i,false);
                            }

                        }
                        Boolean ret=list.removeAll(list1);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                num=0;
                refresh();
                adapter.notifyDataSetChanged();
            }
        });

    }


}

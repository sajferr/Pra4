package com.example.m.pra4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] ekipy1 = {"Chelsea","United","Real","Barka"};
    ListView lista;
    int count=0;
    ArrayList arrayList1=new ArrayList();
    List selections= new ArrayList();
    ArrayAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=(ListView)findViewById(R.id.listView);
        for(String item1 : ekipy1){

            arrayList1.add(item1);


        }
      adapter = new ArrayAdapter(getApplicationContext(),R.layout.row,R.id.textView,arrayList1);

        lista.setAdapter(adapter);
        lista.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        lista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked){
                    selections.add(arrayList1.get(position));
                    count++;
                    mode.setTitle(count+"Selected");
                }
                else{
                    selections.remove(arrayList1.get(position));
                    count--;
                    mode.setTitle(count+"Selected");
                }

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.contexty,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete1:
                        for (Object object : selections){
                            String ITEM = object.toString();
                            arrayList1.remove(ITEM);


                        }
                        adapter.notifyDataSetChanged();
                        mode.finish();
                    return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                count=0;
                selections.clear();

            }
        });
    }
}

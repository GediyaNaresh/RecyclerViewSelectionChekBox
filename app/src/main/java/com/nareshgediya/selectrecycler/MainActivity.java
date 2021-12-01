package com.nareshgediya.selectrecycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    RecyclerView recyclerView;
    ArrayList<String> list;
    ArrayList<String> selectedList;
    private int counter = 0;
    MyAdapter myAdapter;
    boolean iscontexualMode = false;
    Toolbar toolbar;
    TextView textToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        textToolBar = findViewById(R.id.textToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        textToolBar.setText("Items");


        list = new ArrayList<>();
        selectedList = new ArrayList<>();
        for (int i = 1; i <15; i++) {
            if (i % 2==0){
                list.add("Even "+i);
            }
        else {
                list.add("Odd " +i);
            }

        }
        recyclerView = findViewById(R.id.recycler);
        myAdapter = new MyAdapter(list, this);
        recyclerView.setAdapter(myAdapter);

      //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
 StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
      //  GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }


    @Override
    public void onBackPressed() {
        if (iscontexualMode){
        removeContexualMode();
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.normal_menu, menu);
        return true;
    }

    @Override
    public boolean onLongClick(View view) {
        iscontexualMode = true;
        myAdapter.notifyDataSetChanged();
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.contexctual_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getColor(R.color.black));
return true;
    }


    public void makeSelectionList(View view, int adapterPosition) {

        if (((CheckBox)view).isChecked()){
            selectedList.add(list.get(adapterPosition));
            Toast.makeText(this, ""+selectedList.size(), Toast.LENGTH_SHORT).show();
            counter++;
            updateCounter();
        }else {
          selectedList.remove(list.get(adapterPosition));
          counter--;
          updateCounter();
        }
    }

    private void updateCounter() {
        if (counter ==0){
            textToolBar.setText("item Selection");
        }else {
            textToolBar.setText(counter+" item Selected");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!selectedList.isEmpty()){
            if (item.getItemId() == R.id.delete){
                myAdapter.RemoveItems(selectedList);
                removeContexualMode();
            }
        }

        else if (item.getItemId() == android.R.id.home){
            removeContexualMode();
            myAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeContexualMode() {
        iscontexualMode = false;
        counter= 0;
        selectedList.clear();
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.normal_menu);
        toolbar.setBackgroundColor(getColor(R.color.purple_500));
        textToolBar.setText("Items");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}
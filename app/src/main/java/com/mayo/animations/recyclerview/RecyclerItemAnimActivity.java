package com.mayo.animations.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mayo.animations.R;
import com.mayo.animations.recyclerview.animators.SlideInOutRightItemAnimator;
import com.mayo.animations.recyclerview.decorators.DividerItemDecoration;

import java.util.ArrayList;

public class RecyclerItemAnimActivity extends AppCompatActivity {
    private ArrayList<String> mNames;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_item_anim);

        mNames = new ArrayList<>();
        mNames.add("Yaagi");
        mNames.add("Yogi");
        mNames.add("Rekha");

        adapter = new MyAdapter();
        adapter.setNames(mNames);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new SlideInOutRightItemAnimator(recyclerView));
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(RecyclerItemAnimActivity.this, "Clicked: " + mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addItem(View v){
        mNames.add(1,"Dumbu");
        adapter.addName(1);
    }

    public void removeItem(View v){
        mNames.remove(1);
        adapter.remove(1);
    }
}

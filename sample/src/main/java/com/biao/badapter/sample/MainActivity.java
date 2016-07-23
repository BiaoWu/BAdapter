package com.biao.badapter.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biao.badapter.BAdapter;
import com.biao.badapter.BDataSource;
import com.biao.badapter.BViewHolder;
import com.biao.badapter.ItemDelegate;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    BDataSource<String> dataSource = new BDataSource<>();
    for (int i = 0; i < 30; i++) {
      dataSource.add("Test Data index : " + i);
    }

    ItemDelegate<String> itemDelegate = new ItemDelegate<String>() {
      @Override public BViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View itemView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new BViewHolder(itemView);
      }

      @Override public void onBind(BViewHolder holder, String s) {
        TextView textView = (TextView) holder.itemView.findViewById(android.R.id.text1);
        textView.setText(s);
      }
    };

    BAdapter adapter = BAdapter.builder()
        .dataSource(dataSource)
        .itemDelegate(itemDelegate)
        .build();

    recyclerView.setAdapter(adapter);
  }
}

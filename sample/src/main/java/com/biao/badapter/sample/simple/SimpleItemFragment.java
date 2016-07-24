package com.biao.badapter.sample.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biao.badapter.BAdapter;
import com.biao.badapter.BDataSource;
import com.biao.badapter.BViewHolder;
import com.biao.badapter.ItemDelegate;
import com.biao.badapter.sample.BFragment;

/**
 * @author biaowu.
 */
public class SimpleItemFragment extends BFragment {

  @Override protected BAdapter buildAdapter() {
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

      @Override public boolean onIntercept(int position, Object o) {
        return true;
      }
    };

    return BAdapter.builder().dataSource(dataSource).itemDelegate(itemDelegate).build();
  }
}

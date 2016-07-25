package com.biao.badapter.sample.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.biao.badapter.BAdapter;
import com.biao.badapter.BDataSource;
import com.biao.badapter.OnItemClickListener;
import com.biao.badapter.sample.BFragment;
import com.biao.badapter.itemdelegate.simple.BSimpleItemDelegate;
import com.biao.badapter.itemdelegate.simple.BSimpleViewHolder;

/**
 * @author biaowu.
 */
public class SimpleItemFragment extends BFragment {

  @Override protected BAdapter buildAdapter() {
    BDataSource<String> dataSource = new BDataSource<>();
    for (int i = 0; i < 30; i++) {
      dataSource.add("Test Data index : " + i);
    }

    BSimpleItemDelegate<String> itemDelegate = new BSimpleItemDelegate<String>() {
      @Override protected View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
      }

      @Override public void onBind(BSimpleViewHolder holder, String s) {
        holder.setText(android.R.id.text1, s);
      }
    };

    itemDelegate.setOnItemClickListener(new OnItemClickListener<String>() {
      @Override public void onItemClick(View view, int position, String s) {
        Toast.makeText(view.getContext(), s, Toast.LENGTH_SHORT).show();
      }
    });

    return BAdapter.builder().dataSource(dataSource).itemDelegate(itemDelegate).build();
  }
}

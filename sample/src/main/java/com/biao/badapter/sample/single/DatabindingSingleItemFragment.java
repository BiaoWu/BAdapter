package com.biao.badapter.sample.single;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.biao.badapter.BAdapter;
import com.biao.badapter.BDataSource;
import com.biao.badapter.OnItemClickListener;
import com.biao.badapter.sample.BFragment;
import com.biao.badapter.sample.databinding.SimpleText1Binding;
import com.biao.delegate.databinding.BDataBindingItemDelegate;
import com.biao.delegate.databinding.BDataBindingViewHolder;

/**
 * sample of BSimpleItemDelegate
 *
 * @author biaowu.
 */
public class DataBindingSingleItemFragment extends BFragment {

  @Override protected BAdapter buildAdapter() {
    BDataSource<String> dataSource = new BDataSource<>();
    for (int i = 0; i < 30; i++) {
      dataSource.add("Test Data index : " + i);
    }

    BDataBindingItemDelegate<SimpleText1Binding, String> itemDelegate =
        new BDataBindingItemDelegate<SimpleText1Binding, String>() {
          @Override
          protected void onBind(BDataBindingViewHolder<SimpleText1Binding> holder, String s) {
            holder.binding.setContent(s);
          }

          @Override
          protected SimpleText1Binding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
            return SimpleText1Binding.inflate(inflater, parent, false);
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

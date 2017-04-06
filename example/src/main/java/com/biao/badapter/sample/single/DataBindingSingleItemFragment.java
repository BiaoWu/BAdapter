package com.biao.badapter.sample.single;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.biao.badapter.BAdapter;
import com.biao.badapter.BDataSource;
import com.biao.badapter.OnItemClickListener;
import com.biao.badapter.sample.BFragment;
import com.biao.badapter.sample.R;
import com.biao.badapter.sample.databinding.SimpleText1Binding;
import com.biao.delegate.databinding.BDataBindingItemDelegate;
import com.biao.delegate.databinding.BDataBindingViewHolder;

/**
 * sample of BSimpleItemDelegate
 *
 * @author biaowu.
 */
public class DataBindingSingleItemFragment extends BFragment {

  @Override
  protected BAdapter buildAdapter() {
    BDataSource<String> dataSource = new BDataSource<>();
    for (int i = 0; i < 30; i++) {
      dataSource.add("index : " + i);
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

    itemDelegate.dispatchViewClick(R.id.image)
        .setOnItemClickListener(
            new OnItemClickListener<BDataBindingViewHolder<SimpleText1Binding>, String>() {
              @Override
              public void onItemClick(View view,
                  BDataBindingViewHolder<SimpleText1Binding> viewHolder, String s) {
                switch (view.getId()) {
                  case R.id.image:
                    Toast.makeText(view.getContext(),
                        "Image Click at position : " + viewHolder.getAdapterPosition(),
                        Toast.LENGTH_SHORT).show();
                    break;
                  default:
                    Toast.makeText(view.getContext(),
                        "Item Click at position : " + viewHolder.getAdapterPosition(),
                        Toast.LENGTH_SHORT).show();
                    break;
                }
              }
            });

    return BAdapter.builder().dataSource(dataSource).itemDelegate(itemDelegate).build();
  }
}

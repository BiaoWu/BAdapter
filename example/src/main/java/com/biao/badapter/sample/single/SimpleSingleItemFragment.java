package com.biao.badapter.sample.single;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.biao.badapter.BAdapter;
import com.biao.badapter.BDataSource;
import com.biao.badapter.OnItemClickListener;
import com.biao.badapter.itemdelegate.simple.BSimpleItemDelegate;
import com.biao.badapter.itemdelegate.simple.BSimpleViewHolder;
import com.biao.badapter.sample.BFragment;
import com.biao.badapter.sample.R;

/**
 * sample of BSimpleItemDelegate
 *
 * @author biaowu.
 */
public class SimpleSingleItemFragment extends BFragment {

  @Override
  protected BAdapter buildAdapter() {
    BDataSource<String> dataSource = new BDataSource<>();
    for (int i = 0; i < 30; i++) {
      dataSource.add("index : " + i);
    }

    BSimpleItemDelegate<String> itemDelegate = new BSimpleItemDelegate<String>() {
      @Override
      protected View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.simple_text_1, parent, false);
      }

      @Override
      public void onBind(BSimpleViewHolder holder, String s) {
        holder.getTextView(R.id.tv_content).setText(s);
      }
    };

    itemDelegate.dispatchViewClick(R.id.image)
        .setOnItemClickListener(new OnItemClickListener<BSimpleViewHolder, String>() {
          @Override
          public void onItemClick(View view, BSimpleViewHolder viewHolder, String s) {
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

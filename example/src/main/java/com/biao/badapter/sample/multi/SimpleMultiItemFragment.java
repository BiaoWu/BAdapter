package com.biao.badapter.sample.multi;

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
 * @author biaowu.
 */
public class SimpleMultiItemFragment extends BFragment {

  @Override
  protected BAdapter buildAdapter() {
    BDataSource<Person> dataSource = new BDataSource<>();
    for (int i = 1; i <= 30; i++) {
      dataSource.add(new Person(i, "Clone Bill No." + i));
    }

    BSimpleItemDelegate<Person> itemDelegate1 = new BSimpleItemDelegate<Person>() {
      @Override
      protected View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.simple_text_1, parent, false);
      }

      @Override
      public void onBind(BSimpleViewHolder holder, Person person) {
        holder.getTextView(R.id.tv_content).setText(person.toString());
      }

      @Override
      public boolean onIntercept(int position, Object o) {
        return position % 2 == 0;
      }
    };

    itemDelegate1.dispatchViewClick(R.id.image)
        .setOnItemClickListener(new OnItemClickListener<BSimpleViewHolder, Person>() {
          @Override
          public void onItemClick(View view, BSimpleViewHolder viewHolder, Person person) {
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

    BSimpleItemDelegate<Person> itemDelegate2 = new BSimpleItemDelegate<Person>() {
      @Override
      protected View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.simple_text_2, parent, false);
      }

      @Override
      public void onBind(BSimpleViewHolder holder, Person person) {
        holder.getTextView(R.id.tv_content).setText(person.toString());
      }

      @Override
      public boolean onIntercept(int position, Object o) {
        return position % 2 != 0;
      }
    };

    itemDelegate2.dispatchViewClick(R.id.image)
        .setOnItemClickListener(new OnItemClickListener<BSimpleViewHolder, Person>() {
          @Override
          public void onItemClick(View view, BSimpleViewHolder viewHolder, Person person) {
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

    return BAdapter.builder()
        .dataSource(dataSource)
        .itemDelegate(itemDelegate1)
        .itemDelegate(itemDelegate2)
        .build();
  }
}

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

  @Override protected BAdapter buildAdapter() {
    BDataSource<Person> dataSource = new BDataSource<>();
    for (int i = 1; i <= 30; i++) {
      dataSource.add(new Person(i, "Clone Bill No." + i));
    }

    BSimpleItemDelegate<Person> itemDelegate1 = new BSimpleItemDelegate<Person>() {
      @Override public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
      }

      @Override public void onBind(BSimpleViewHolder holder, Person person) {
        holder.setText(android.R.id.text1, getString(R.string.type, 1));
        holder.setText(android.R.id.text2, getString(R.string.person, person.id, person.name));
      }

      @Override public boolean onIntercept(int position, Object o) {
        return position % 2 == 0;
      }
    };

    itemDelegate1.dispatchViewClick(android.R.id.text1, android.R.id.text2)
        .setOnItemClickListener(new OnItemClickListener<Person>() {
          @Override public void onItemClick(View view, int position, Person person) {
            switch (view.getId()) {
              case android.R.id.text1:
                Toast.makeText(view.getContext(), "text1 click Type 1 -> " + person.name,
                    Toast.LENGTH_SHORT).show();
                break;
              case android.R.id.text2:
                Toast.makeText(view.getContext(), "text2 click Type 1 -> " + person.name,
                    Toast.LENGTH_SHORT).show();
                break;
              default:
                Toast.makeText(view.getContext(), "itemView click Type 1 -> " + person.name,
                    Toast.LENGTH_SHORT).show();
                break;
            }
          }
        });

    BSimpleItemDelegate<Person> itemDelegate2 = new BSimpleItemDelegate<Person>() {
      @Override public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
      }

      @Override public void onBind(BSimpleViewHolder holder, Person person) {
        holder.setText(android.R.id.text1, getString(R.string.type, 2));
        holder.setText(android.R.id.text2, getString(R.string.person, person.id, person.name));
      }

      @Override public boolean onIntercept(int position, Object o) {
        return position % 2 != 0;
      }
    };

    itemDelegate2.dispatchViewClick(android.R.id.text1, android.R.id.text2)
        .setOnItemClickListener(new OnItemClickListener<Person>() {
          @Override public void onItemClick(View view, int position, Person person) {
            switch (view.getId()) {
              case android.R.id.text1:
                Toast.makeText(view.getContext(), "text1 click Type 2 -> " + person.name,
                    Toast.LENGTH_SHORT).show();
                break;
              case android.R.id.text2:
                Toast.makeText(view.getContext(), "text2 click Type 2 -> " + person.name,
                    Toast.LENGTH_SHORT).show();
                break;
              default:
                Toast.makeText(view.getContext(), "itemView click Type 2 -> " + person.name,
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

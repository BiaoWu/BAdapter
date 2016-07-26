package com.biao.badapter.sample.multi;

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
import com.biao.badapter.sample.databinding.SimpleText2Binding;
import com.biao.delegate.databinding.BDataBindingItemDelegate;
import com.biao.delegate.databinding.BDataBindingViewHolder;

/**
 * @author biaowu.
 */
public class DataBindingMultiItemFragment extends BFragment {

  @Override protected BAdapter buildAdapter() {
    BDataSource<Person> dataSource = new BDataSource<>();
    for (int i = 1; i <= 30; i++) {
      dataSource.add(new Person(i, "Clone Bill No." + i));
    }

    BDataBindingItemDelegate<SimpleText1Binding, Person> itemDelegate1 =
        new BDataBindingItemDelegate<SimpleText1Binding, Person>() {

          @Override
          protected void onBind(BDataBindingViewHolder<SimpleText1Binding> holder, Person person) {
            holder.binding.setContent(person.toString());
          }

          @Override
          protected SimpleText1Binding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
            return SimpleText1Binding.inflate(inflater, parent, false);
          }

          @Override protected boolean onIntercept(int position, Object o) {
            return o instanceof Person && ((Person) o).id % 2 == 0;
          }
        };

    itemDelegate1.dispatchViewClick(R.id.image)
        .setOnItemClickListener(new OnItemClickListener<Person>() {
          @Override public void onItemClick(View view, int position, Person person) {
            switch (view.getId()) {
              case R.id.image:
                Toast.makeText(view.getContext(), "Image Click at position : " + position,
                    Toast.LENGTH_SHORT).show();
                break;
              default:
                Toast.makeText(view.getContext(), "Item Click at position : " + position,
                    Toast.LENGTH_SHORT).show();
                break;
            }
          }
        });
    BDataBindingItemDelegate<SimpleText2Binding, Person> itemDelegate2 =
        new BDataBindingItemDelegate<SimpleText2Binding, Person>() {

          @Override
          protected void onBind(BDataBindingViewHolder<SimpleText2Binding> holder, Person person) {
            holder.binding.setContent(person.toString());
          }

          @Override
          protected SimpleText2Binding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
            return SimpleText2Binding.inflate(inflater, parent, false);
          }

          @Override protected boolean onIntercept(int position, Object o) {
            return o instanceof Person && ((Person) o).id % 2 != 0;
          }
        };

    itemDelegate2.dispatchViewClick(R.id.image)
        .setOnItemClickListener(new OnItemClickListener<Person>() {
          @Override public void onItemClick(View view, int position, Person person) {
            switch (view.getId()) {
              case R.id.image:
                Toast.makeText(view.getContext(), "Image Click at position : " + position,
                    Toast.LENGTH_SHORT).show();
                break;
              default:
                Toast.makeText(view.getContext(), "Item Click at position : " + position,
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

package com.biao.badapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * User Interface
 *
 * {@link BAdapter.Builder#itemDelegate(ItemDelegate)}
 * <pre>
 *       BAdapter adapter = BAdapter.builder()
 *          .itemDelegate(itemDelegate)
 *          //...
 *          .build();
 * </pre>
 *
 * @author biaowu.
 */
public interface ItemDelegate<Data> {
  BViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

  void onBind(BViewHolder holder, Data data);
}
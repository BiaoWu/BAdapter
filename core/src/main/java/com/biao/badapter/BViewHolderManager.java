/*
 * Copyright (C) 2016 BiaoWu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.biao.badapter;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biao.badapter.util.PreConditions;
import java.util.List;

/**
 * implement of {@link ViewHolderManager}
 *
 * @author biaowu.
 */
/* package */class BViewHolderManager implements ViewHolderManager {
  private static final String TAG = "BViewHolderManager";
  private static final String MISS_ITEM_DELEGATE = "Not found the itemDelegate!!";

  private DataSource dataSource;
  private SparseArray<ItemDelegate> itemDelegates;

  private View.OnClickListener onItemClick = new View.OnClickListener() {
    @Override public void onClick(View v) {
      int position = (int) v.getTag(R.id.rv_position);
      int itemType = (int) v.getTag(R.id.rv_view_type);

      onItemViewClick(v, itemType, position);
    }
  };

  /* package */BViewHolderManager(DataSource dataSource, List<ItemDelegate> list) {
    this.dataSource = dataSource;

    int size = list.size();
    itemDelegates = new SparseArray<>(size);

    int viewType = 0;
    for (int i = 0; i < size; i++) {
      itemDelegates.put(++viewType, list.get(i));
    }
  }

  @Override public BViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return PreConditions.checkNotNull(itemDelegates.get(viewType), MISS_ITEM_DELEGATE)
        .onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
  }

  @SuppressWarnings("unchecked") @Override
  public void onBindViewHolder(BViewHolder holder, int position) {
    int viewType = holder.getItemViewType();
    ItemDelegate itemDelegate =
        PreConditions.checkNotNull(itemDelegates.get(viewType), MISS_ITEM_DELEGATE);

    itemDelegate.onBind(holder, dataSource.get(position));

    //set item click
    if (itemDelegate.onItemClickListener != null) {
      holder.itemView.setTag(R.id.rv_position, position);
      holder.itemView.setTag(R.id.rv_view_type, viewType);
      holder.itemView.setOnClickListener(onItemClick);

      int[] ids = itemDelegate.clickViewIds;
      if (ids == null) return;

      for (int id : ids) {
        View view = holder.itemView.findViewById(id);
        if (view != null) {
          view.setTag(R.id.rv_position, position);
          view.setTag(R.id.rv_view_type, viewType);
          view.setOnClickListener(onItemClick);
        } else {
          Log.e(TAG, "Not found the view by id -> " + id);
        }
      }
    }
  }

  @Override public int getItemViewType(int position) {
    for (int i = 0; i < itemDelegates.size(); i++) {
      int viewType = itemDelegates.keyAt(i);
      if (itemDelegates.get(viewType).onIntercept(position, dataSource.get(position))) {
        return viewType;
      }
    }
    throw new IllegalArgumentException("Not found the viewType at position -> " + position);
  }

  @SuppressWarnings("unchecked")
  private void onItemViewClick(View view, int itemType, int position) {
    ItemDelegate itemDelegate =
        PreConditions.checkNotNull(itemDelegates.get(itemType), MISS_ITEM_DELEGATE);

    OnItemClickListener onItemClickListener = itemDelegate.onItemClickListener;
    if (onItemClickListener != null) {
      onItemClickListener.onItemClick(view, position, dataSource.get(position));
    }
  }
}

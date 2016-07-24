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

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.biao.badapter.util.PreConditions;
import java.util.List;

/**
 * implement of {@link ViewHolderManager}
 *
 * @author biaowu.
 */
/* package */class BViewHolderManager implements ViewHolderManager {
  private static final String MISS_ITEM_DELEGATE = "Not found the itemDelegate!!";

  private DataSource dataSource;
  private SparseArray<ItemDelegate> itemDelegates;

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
}

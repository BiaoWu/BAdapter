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

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * implement of {@link ViewHolderManager}
 *
 * @author biaowu.
 */
/* package */class BViewHolderManager implements ViewHolderManager {
  private DataSource dataSource;
  private ItemDelegate itemDelegate;

  /* package */BViewHolderManager(DataSource dataSource, ItemDelegate itemDelegate) {
    this.dataSource = dataSource;
    this.itemDelegate = itemDelegate;
  }

  @Override public BViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return itemDelegate.onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
  }

  @SuppressWarnings("unchecked") @Override
  public void onBindViewHolder(BViewHolder holder, int position) {
    itemDelegate.onBind(holder, dataSource.get(position));
  }

  @Override public int getItemViewType(int position) {
    //// TODO: 16/7/23 multi type
    return 0;
  }
}

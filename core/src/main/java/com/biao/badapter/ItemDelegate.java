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
public abstract class ItemDelegate<Data> {
  /* package */ OnItemClickListener<Data> onItemClickListener;

  /** {@link BViewHolderManager#onCreateViewHolder(ViewGroup, int)} */
  protected abstract BViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

  /** {@link BViewHolderManager#onBindViewHolder(BViewHolder, int)} */
  protected abstract void onBind(BViewHolder holder, Data data);

  /**
   * Pass the Data to the itemDelegate
   *
   * @param position suggest not to use
   * @param o sometimes instanceof Data
   * @return True if the Data was handled by the ItemDelegate, false otherwise.
   */
  protected boolean onIntercept(int position, Object o) {
    return true;
  }

  public void setOnItemClickListener(OnItemClickListener<Data> onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }
}
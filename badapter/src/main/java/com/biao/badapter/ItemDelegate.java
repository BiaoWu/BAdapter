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

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base ItemDelegate
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
public abstract class ItemDelegate<Holder extends RecyclerView.ViewHolder, Data> {
  /* package */ int viewType = -1;
  /* package */ int[] clickViewIds;
  /* package */ OnItemClickListener<Data> onItemClickListener;

  /** {@link BViewHolderManager#onCreateViewHolder(ViewGroup, int)} */
  protected abstract Holder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

  /** {@link BViewHolderManager#onBindViewHolder(RecyclerView.ViewHolder, int)} */
  protected abstract void onBind(Holder holder, Data data);

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

  /**
   * Pass the click to {@link OnItemClickListener#onItemClick(View, int, Object)}
   * <pre>
   *    itemDelegate.dispatchViewClick(id1, id2)
   *      .setOnItemClickListener(new OnItemClickListener<Data>() {
   *         @Override public void onItemClick(View view, int position, Data data) {
   *           switch (view.getId()) {
   *             case id1:
   *               break;
   *             case id2:
   *               break;
   *             default:
   *               break;
   *           }
   *     }
   *   });
   * </pre>
   */
  @SuppressWarnings("unchecked") public <T extends ItemDelegate<Holder, Data>> T dispatchViewClick(
      @IdRes int... ids) {
    clickViewIds = ids;
    return (T) this;
  }

  /**
   * Register a callback to be invoked when this view is clicked.
   *
   * @param onItemClickListener The callback that will run
   */
  public void setOnItemClickListener(OnItemClickListener<Data> onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public int getViewType() {
    return viewType;
  }
}
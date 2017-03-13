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

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import static com.biao.badapter.util.PreConditions.checkNotNull;

/**
 * A graceful RecyclerView Adapter.
 *
 * Create BAdapter by {@link #builder()}
 * example:
 * <pre>
 * BAdapter adapter = BAdapter.builder()
 *      //...
 *      .build();
 * </pre>
 *
 * @author biaowu.
 */
public class BAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private DataSource dataSource;
  private ViewHolderManager viewHolderManager;

  protected BAdapter(DataSource dataSource, ViewHolderManager viewHolderManager) {
    this.dataSource = checkNotNull(dataSource, "dataSource cannot be null!!!");
    this.viewHolderManager = checkNotNull(viewHolderManager, "viewHolderManager cannot be null!!!");
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return viewHolderManager.onCreateViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    viewHolderManager.onBindViewHolder(holder, position);
  }

  @Override public int getItemCount() {
    return dataSource.size();
  }

  @Override public int getItemViewType(int position) {
    return viewHolderManager.getItemViewType(position);
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  /** Create BAdapter {@link Builder} */
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private DataSource dataSource;
    private List<ItemDelegate> itemDelegates;

    public Builder dataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      return this;
    }

    public Builder itemDelegate(ItemDelegate itemDelegate) {
      if (itemDelegates == null) {
        itemDelegates = new ArrayList<>(3);
      }
      itemDelegates.add(itemDelegate);
      return this;
    }

    public BAdapter build() {
      if (dataSource == null) {
        dataSource = new BDataSource();
      }

      ViewHolderManager viewHolderManager = new BViewHolderManager(dataSource,
          checkNotNull(itemDelegates, "itemDelegate cannot be null!"));

      return new BAdapter(dataSource, viewHolderManager);
    }
  }
}

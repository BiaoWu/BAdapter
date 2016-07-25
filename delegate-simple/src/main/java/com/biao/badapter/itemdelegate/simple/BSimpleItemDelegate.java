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
package com.biao.badapter.itemdelegate.simple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.biao.badapter.ItemDelegate;

/**
 * Simple ItemDelegate with {@link BSimpleViewHolder}
 *
 * @author biaowu.
 */
public abstract class BSimpleItemDelegate<Data> extends ItemDelegate<BSimpleViewHolder, Data> {
  @Override
  protected BSimpleViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
    View itemView = onCreateView(inflater, parent);
    return new BSimpleViewHolder(itemView);
  }

  /**
   * create a new itemView for {@link BSimpleViewHolder}
   *
   * @see #onCreateViewHolder(LayoutInflater, ViewGroup)
   */
  protected abstract View onCreateView(LayoutInflater inflater, ViewGroup parent);
}
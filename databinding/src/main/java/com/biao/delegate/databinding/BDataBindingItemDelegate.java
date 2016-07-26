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
package com.biao.delegate.databinding;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.biao.badapter.ItemDelegate;

/**
 * DataBinding ItemDelegate with {@link BDataBindingViewHolder}
 *
 * @author biaowu.
 */
public abstract class BDataBindingItemDelegate<Binding extends ViewDataBinding, Data>
    extends ItemDelegate<BDataBindingViewHolder<Binding>, Data> {

  @Override protected BDataBindingViewHolder<Binding> onCreateViewHolder(LayoutInflater inflater,
      ViewGroup parent) {
    Binding binding = onCreateBinding(inflater, parent);
    return new BDataBindingViewHolder<>(binding);
  }

  /**
   * create a new {@link ViewDataBinding} for {@link BDataBindingViewHolder}
   *
   * @see #onCreateViewHolder(LayoutInflater, ViewGroup)
   */
  protected abstract Binding onCreateBinding(LayoutInflater inflater, ViewGroup parent);
}

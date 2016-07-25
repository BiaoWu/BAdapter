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

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Simple ViewHolder
 *
 * @author biaowu.
 */
public class BSimpleViewHolder extends RecyclerView.ViewHolder {
  private SparseArray<View> views;

  public BSimpleViewHolder(View itemView) {
    super(itemView);
    views = new SparseArray<>();
  }

  public void setText(@IdRes int id, CharSequence text) {
    TextView textView = getView(id);
    textView.setText(text);
  }

  // TODO: 16/7/25 add more help methods
  // ...
  // ...

  @SuppressWarnings("unchecked") public <T extends View> T getView(@IdRes int id) {
    View view = views.get(id);
    if (view == null) {
      view = itemView.findViewById(id);
    }

    return (T) view;
  }
}
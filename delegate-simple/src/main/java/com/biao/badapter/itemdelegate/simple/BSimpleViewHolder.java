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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Simple ViewHolder
 *
 * TODO: 16/7/25 if you need more methods for help, welcome pull request!
 *
 * @author biaowu.
 */
public class BSimpleViewHolder extends RecyclerView.ViewHolder {
  private SparseArray<View> views;

  public BSimpleViewHolder(View itemView) {
    super(itemView);
    views = new SparseArray<>();
  }

  /** return ViewGroup of {@link #getView(int)} */
  public ViewGroup getViewGroup(@IdRes int id) {
    return getView(id);
  }

  /** return TextView of {@link #getView(int)} */
  public TextView getTextView(@IdRes int id) {
    return getView(id);
  }

  /** return ImageView of {@link #getView(int)} */
  public ImageView getImageView(@IdRes int id) {
    return getView(id);
  }

  /** return EditText of {@link #getView(int)} */
  public EditText getEditText(@IdRes int id) {
    return getView(id);
  }

  /**
   * return CompoundButton of {@link #getView(int)}
   *
   * @see Switch
   * @see RadioButton
   * @see CheckBox
   */
  public CompoundButton getCompoundButton(@IdRes int id) {
    return getView(id);
  }

  /** return ProgressBar of {@link #getView(int)} */
  public ProgressBar getProgressBar(@IdRes int id) {
    return getView(id);
  }

  /**
   * return AdapterView of {@link #getView(int)}
   *
   * @see ListView
   * @see GridView
   * @see Spinner
   */
  public AdapterView getAdapterView(@IdRes int id) {
    return getView(id);
  }

  /**
   * Your mistakes:
   * 1. null if not found
   * 2. cast exception
   */
  @SuppressWarnings("unchecked") public <T extends View> T getView(@IdRes int id) {
    View view = views.get(id);
    if (view == null) {
      view = itemView.findViewById(id);
    }
    return (T) view;
  }
}
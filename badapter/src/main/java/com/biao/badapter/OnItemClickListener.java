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

import android.view.View;

/**
 * Interface definition for a callback to be invoked when a view is clicked.
 *
 * @author biaowu.
 */
public interface OnItemClickListener<T> {
  /**
   * Called when a view has been clicked.
   *
   * @param view The view that was clicked.
   * @param position adapter position
   * @param t {@link T}
   */
  void onItemClick(View view, int position, T t);
}
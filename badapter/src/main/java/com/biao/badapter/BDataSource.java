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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Simple DataSource
 *
 * @author biaowu.
 */
public class BDataSource<Data> implements DataSource<Data> {
  private static final String TAG = "SimpleDataSource";

  private ArrayList<Data> dataList;

  public BDataSource() {
    this.dataList = new ArrayList<>();
  }

  public BDataSource(List<Data> dataList) {
    this.dataList = new ArrayList<>(dataList);
  }

  @Override public int size() {
    return dataList.size();
  }

  public void add(Data data) {
    dataList.add(data);
  }

  public void add(int position, Data data) {
    if (!checkPosition(position)) return;

    dataList.add(position, data);
  }

  public void addAll(Collection<Data> collection) {
    if (collection == null) return;

    dataList.addAll(collection);
  }

  public void remove(int position) {
    if (!checkPosition(position)) return;

    dataList.remove(position);
  }

  public void remove(Data data) {
    remove(dataList.indexOf(data));
  }

  public void removeAll(Collection<Data> collection) {
    if (collection == null) return;

    dataList.removeAll(collection);
  }

  public void removeAll() {
    dataList.clear();
  }

  public void update(int position, Data data) {
    if (!checkPosition(position)) return;

    dataList.set(position, data);
  }

  public void replaceAll(Collection<Data> collection) {
    if (collection == null) return;

    dataList.clear();
    dataList.addAll(collection);
  }

  @Override public Data get(int position) {
    if (!checkPosition(position)) return null;

    return dataList.get(position);
  }

  public int getIndex(Data data) {
    return dataList.indexOf(data);
  }

  public List<Data> getDataList() {
    return dataList;
  }

  private boolean checkPosition(int position) {
    return position >= 0 && position < dataList.size();
  }
}
package com.biao.badapter.datasource;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author biaowu.
 */
public class SimpleDataSourceTest {
  private static final String ME = "Bill";
  private static final String YOU = "You";
  private static final String HE = "He";
  private static final String SHE = "She";

  private List<String> testList = Arrays.asList(ME, YOU, HE, SHE);

  private SimpleDataSource<String> dataSource;

  @Before public void setUp() throws Exception {
    dataSource = new SimpleDataSource<>();
  }

  @Test public void test_init_size() throws Exception {
    assertThat(dataSource.size(), is(0));
  }

  @Test public void test_add() throws Exception {
    dataSource.add(ME);
    dataSource.add(ME);
    dataSource.add(ME);
    assertThat(dataSource.size(), is(3));
  }

  @Test public void test_add_position() throws Exception {
    dataSource.add(ME);
    dataSource.add(ME);
    dataSource.add(ME);

    int position = 1;
    String you = "You";

    dataSource.add(position, you);
    assertThat(dataSource.size(), is(4));

    String item = dataSource.get(position);

    assertThat(item, is(you));
  }

  @Test public void test_add_all() throws Exception {
    addTestList();

    int position = 2;
    String item = dataSource.get(position);
    assertThat(item, is(testList.get(position)));
  }

  @Test public void test_remove() throws Exception {
    addTestList();

    dataSource.remove(testList.get(2));
    assertThat(dataSource.size(), is(testList.size() - 1));
  }

  @Test public void test_remove_position() throws Exception {
    addTestList();

    dataSource.remove(2);
    assertThat(dataSource.size(), is(testList.size() - 1));
  }

  @Test public void test_remove_all() throws Exception {
    addTestList();

    dataSource.removeAll();
    assertThat(dataSource.size(), is(0));
  }

  @Test public void test_remove_all_list() throws Exception {
    addTestList();

    List<String> removeList = Arrays.asList(YOU, HE);
    dataSource.removeAll(removeList);
    assertThat(dataSource.size(), is(2));
  }

  @Test public void test_remove_all_list_has_other() throws Exception {
    addTestList();

    String other = "Other";
    List<String> removeList = Arrays.asList(YOU, other);
    dataSource.removeAll(removeList);
    assertThat(dataSource.size(), is(3));
  }

  @Test public void test_update() throws Exception {
    addTestList();

    String other = "Other";
    int position = 1;
    dataSource.update(position, other);
    String item = dataSource.get(position);
    assertThat(item, is(other));
  }

  @Test public void test_replace_all() throws Exception {
    addTestList();

    List<String> replaceList = Arrays.asList(YOU, ME, HE);
    dataSource.replaceAll(replaceList);
    assertThat(dataSource.size(), is(replaceList.size()));
    assertThat(dataSource.get(1), is(ME));
  }

  @Test public void test_get_item() throws Exception {
    List<String> list = Arrays.asList(YOU, ME, HE);
    dataSource.replaceAll(list);
    assertThat(dataSource.get(0), is(YOU));
    assertThat(dataSource.get(1), is(ME));
    assertThat(dataSource.get(2), is(HE));
  }

  @Test public void test_get_index() throws Exception {
    List<String> list = Arrays.asList(YOU, ME, HE);
    dataSource.replaceAll(list);
    assertThat(dataSource.getIndex(YOU), is(0));
    assertThat(dataSource.getIndex(ME), is(1));
    assertThat(dataSource.getIndex(HE), is(2));
  }

  @Test public void test_getDataList() throws Exception {
    List<String> list = Arrays.asList(YOU, ME, HE);
    dataSource.replaceAll(list);
    List<String> dataList = dataSource.getDataList();
    assertThat(dataList.size(), is(list.size()));
  }

  private void addTestList() {
    dataSource.addAll(testList);
    assertThat(dataSource.size(), is(testList.size()));
  }
}
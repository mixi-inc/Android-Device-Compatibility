package jp.mixi.compatibility.android.widget;

import android.test.AndroidTestCase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import jp.mixi.compatibility.android.widget.ArrayAdapterCompat;

public class ArrayAdapterCompatTest extends AndroidTestCase {
    public void testAddAllCollection() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.id.text1);
        List<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        ArrayAdapterCompat.addAll(adapter, list);
        assertFalse(adapter.isEmpty());
        assertEquals(3, adapter.getCount());
        assertEquals("test1", adapter.getItem(0));
        assertEquals("test2", adapter.getItem(1));
        assertEquals("test3", adapter.getItem(2));
    }

    public void testAddAllArray() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.id.text1);
        String[] array = new String[] {"test1", "test2", "test3"};
        ArrayAdapterCompat.addAll(adapter, array);
        assertFalse(adapter.isEmpty());
        assertEquals(3, adapter.getCount());
        assertEquals("test1", adapter.getItem(0));
        assertEquals("test2", adapter.getItem(1));
        assertEquals("test3", adapter.getItem(2));
    }
}
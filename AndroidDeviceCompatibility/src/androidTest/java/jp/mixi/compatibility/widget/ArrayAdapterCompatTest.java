package jp.mixi.compatibility.widget;

import android.content.Context;
import android.os.Build;
import android.test.AndroidTestCase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jp.mixi.compatibility.android.widget.ArrayAdapterCompat;


/**
 * Created by Hideyuki.Kikuma on 2014/10/08.
 */
public class ArrayAdapterCompatTest extends AndroidTestCase {

    public void testAddAllCollection() {
        List<String> list = new ArrayList<String>();
        list.add("foo");
        list.add("bar");
        DummyArrayAdapter<String> adapter = new DummyArrayAdapter<String>(getContext(), 0);
        assertFalse(adapter.callAdd);
        assertFalse(adapter.callAddAll);

        ArrayAdapterCompat.addAll(adapter, list);

        assertTrue(adapter.mCollection.containsAll(list));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertTrue(adapter.callAddAll);
            assertFalse(adapter.callAdd);
        } else {
            assertTrue(adapter.callAdd);
            assertFalse(adapter.callAddAll);
        }
    }

    public void testAddAllArray() {
        String[] list = new String[]{"foo", "bar"};
        DummyArrayAdapter<String> adapter = new DummyArrayAdapter<String>(getContext(), 0);
        assertFalse(adapter.callAdd);
        assertFalse(adapter.callAddAll);

        ArrayAdapterCompat.addAll(adapter, list);

        assertTrue(adapter.mCollection.containsAll(Arrays.asList(list)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            assertTrue(adapter.callAddAll);
            assertFalse(adapter.callAdd);
        } else {
            assertTrue(adapter.callAdd);
            assertFalse(adapter.callAddAll);
        }

    }

    private class DummyArrayAdapter<T> extends ArrayAdapter<T> {
        private Collection<T> mCollection;
        private boolean callAddAll = false;
        private boolean callAdd = false;

        public DummyArrayAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public void addAll(Collection<? extends T> collection) {
            callAddAll = true;
            if (mCollection == null) {
                mCollection = new ArrayList<T>();
            }
            mCollection.addAll(collection);
        }

        @Override
        public void addAll(T... items) {
            callAddAll = true;
            if (mCollection == null) {
                mCollection = new ArrayList<T>();
            }
            Collections.addAll(mCollection, items);
        }

        @Override
        public void add(T object) {
            callAdd = true;
            if (mCollection == null) {
                mCollection = new ArrayList<T>();
            }
            mCollection.add(object);

        }
    }
}

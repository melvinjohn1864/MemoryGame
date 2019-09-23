package com.example.memorygame.livedatas;

import com.example.memorygame.livedatas.RecyclerViewLiveData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;


public class RecyclerViewUpdateLiveData<T> extends RecyclerViewLiveData<T> {

    private static final int ALL_ITEM_CHANGED = 0;
    private static final int SINGLE_ITEM_CHANGED = 1;
    private static final int ITEM_INSERTED = 2;
    private static final int ITEM_REMOVED = 3;
    private static final int UPDATE_WITH_NEW_ITEMS = 4;

    public void observe(@NonNull LifecycleOwner owner, final RecyclerViewItemObserver<T> observer) {
        super.observe(owner, new Observer<RecyclerViewItem<T>>() {
            @Override
            public void onChanged(@Nullable RecyclerViewItem<T> t) {
                if (t == null) {
                    return;
                }
                if (t.changeType == ALL_ITEM_CHANGED) {
                    observer.allItemChanged();
                } else if (t.changeType == SINGLE_ITEM_CHANGED) {
                    observer.singleItemChanged(t.position, t.item);
                } else if (t.changeType == ITEM_INSERTED) {
                    observer.itemInserted(t.position, t.item);
                } else if (t.changeType == ITEM_REMOVED) {
                    observer.itemRemoved(t.position);
                } else if (t.changeType == UPDATE_WITH_NEW_ITEMS) {
                    observer.allItemUpdate(t.items);
                }
            }
        });
    }

    public void itemChanged(int position, T item) {
        super.setValue(getResult(SINGLE_ITEM_CHANGED, position, item, null));
    }

    public void itemInserted(int position, T item) {
        super.setValue(getResult(ITEM_INSERTED, position, item, null));
    }

    public void itemRemoved(int position) {
        super.setValue(getResult(ITEM_REMOVED, position, null, null));
    }

    public void setValue(List<T> items) {
        super.setValue(getResult(UPDATE_WITH_NEW_ITEMS, 0, null, items));
    }

    public void setValue() {
        super.setValue(getResult(ALL_ITEM_CHANGED, 0, null, null));
    }

    private RecyclerViewItem<T> getResult(int changeType, int position, T t, List<T> items) {
        RecyclerViewItem<T> item = new RecyclerViewItem<>();
        item.changeType = changeType;
        item.position = position;
        item.item = t;
        item.items = items;
        return item;
    }

    public interface RecyclerViewItemObserver<T> {
        void allItemChanged();
        void singleItemChanged(int position, T t);
        void itemInserted(int position, T t);
        void itemRemoved(int position);
        void allItemUpdate(List<T> items);
    }
}

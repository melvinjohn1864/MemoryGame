package com.example.memorygame.livedatas;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


public class RecyclerViewLiveData<T> extends MutableLiveData<RecyclerViewLiveData.RecyclerViewItem<T>> {


    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super RecyclerViewItem<T>> observer) {
        super.observe(owner, new Observer<RecyclerViewItem<T>>() {
            @Override
            public void onChanged(@Nullable RecyclerViewItem<T> t) {
                observer.onChanged(t);
            }
        });
    }

    @Override
    public void setValue(RecyclerViewItem<T> t) {
        super.setValue(t);
    }

    public static class RecyclerViewItem<T> {
        int changeType;
        int position;
        T item;
        List<T> items;
    }
}

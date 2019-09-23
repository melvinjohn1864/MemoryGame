package com.example.memorygame.livedatas;


import com.example.memorygame.livedatas.SimpleLiveData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class VoidLiveData extends SimpleLiveData<Void> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final VoidObserver observer) {
        super.observe(owner, new Observer<Void>() {

            @Override
            public void onChanged(@Nullable Void aBoolean) {
                observer.value();
            }
        });
    }

    public interface VoidObserver {
        void value();
    }
}
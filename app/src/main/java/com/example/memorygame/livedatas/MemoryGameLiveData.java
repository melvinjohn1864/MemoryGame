package com.example.memorygame.livedatas;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


public class MemoryGameLiveData<T> extends MutableLiveData<T> {

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, final Observer<? super T> observer) {
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T response) {
                observer.onChanged(response);
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t) {
        super.setValue(t);
    }


    public void postValue(@NonNull T t) {
        super.postValue(t);
    }

}

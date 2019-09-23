package com.example.memorygame.livedatas;


import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;


public class ApiResponseLiveData<T>  extends MemoryGameLiveData<T> {

    public void observe(LifecycleOwner owner, final MemoryGameApiObserver<T> observer) {
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T response) {
                if (response == null) {
                    return;
                }
                observer.showSuccessResponse(response);
            }
        });
    }

    public interface MemoryGameApiObserver<T> {
        void showSuccessResponse(T response);
    }
}

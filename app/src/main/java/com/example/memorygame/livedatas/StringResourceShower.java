package com.example.memorygame.livedatas;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class StringResourceShower extends SimpleLiveData<String> {

    public void observe(@NonNull LifecycleOwner owner, @NonNull final ResourceShowerObserver observer) {
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s == null) {
                    return;
                }

                observer.showString(s);
            }
        });
    }

    public interface ResourceShowerObserver {
        void showString(String message);
    }


}

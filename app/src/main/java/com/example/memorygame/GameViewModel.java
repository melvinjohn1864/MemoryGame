package com.example.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.memorygame.livedatas.ApiResponseLiveData;
import com.example.memorygame.livedatas.RecyclerViewUpdateLiveData;
import com.example.memorygame.livedatas.StringResourceShower;
import com.example.memorygame.livedatas.VoidLiveData;
import com.example.memorygame.models.Image_;
import com.example.memorygame.models.MemoryGameResponse;
import com.example.memorygame.models.Product;
import com.example.memorygame.network.ApiInterface;
import com.example.memorygame.network.RetrofitClientInstance;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameViewModel extends AndroidViewModel {

    private List<Image_> imageUrls = new ArrayList<>();
    private RecyclerViewUpdateLiveData<Image_> mRecyclerViewUpdateTrigger;
    private List<Integer> integerList;
    private ApiResponseLiveData<MemoryGameResponse> memoryGameResponseLiveData;
    private StringResourceShower stringResourceShower;
    private VoidLiveData noMatchMade;

    public GameViewModel(@NonNull Application application) {
        super(application);
        mRecyclerViewUpdateTrigger = new RecyclerViewUpdateLiveData<>();
        stringResourceShower = new StringResourceShower();
        integerList = new ArrayList<>();
        noMatchMade = new VoidLiveData();

    }

    RecyclerViewUpdateLiveData<Image_> getRecyclerViewUpdateTrigger() {
        return mRecyclerViewUpdateTrigger;
    }

    StringResourceShower getStringResourceShower(){
        return stringResourceShower;
    }

    List<Image_> getImages() {
        return imageUrls;
    }

    ApiResponseLiveData<MemoryGameResponse> getMemoryGameResponseLiveData(){
        return memoryGameResponseLiveData;
    }

    VoidLiveData getNoMatchMade(){
        return  noMatchMade;
    }


    //Exploring the api response and getting the image urls
    void exploreMemoryGameResponse(MemoryGameResponse response, int levelOfGame) {
        integerList.clear();
        if (response == null) {
            return;
        }

        List<Product> productList = response.getProducts();


        for (int i = 0; i < levelOfGame * 10; i++) {
            Product product = productList.get(i % 5);
            Image_ image_ = new Image_();
            image_.setProductId(product.getImage().getProductId());
            image_.setSrc(product.getImage().getSrc());
            imageUrls.add(image_);
        }

        Collections.shuffle(imageUrls);
        mRecyclerViewUpdateTrigger.setValue();
    }


    //Method to compare the image in current position and previous position

    public void sendImagePosition(final int currentPosition, int levelOfGame) {
        if (!integerList.contains(currentPosition)) {
            final Image_ image = imageUrls.get(currentPosition);
            image.setClickStatus(true);

            mRecyclerViewUpdateTrigger.itemChanged(currentPosition, image);

            int position = getThePreviousPositionBasedONLevel(levelOfGame);
            if (position == -1) {
                integerList.add(currentPosition);
                calculateScore(levelOfGame);
            } else {
                Image_ previousImage = imageUrls.get(integerList.get(position));
                if (!image.getProductId().equals(previousImage.getProductId())) {
                    revertTheImages(currentPosition,position,levelOfGame);
                    noMatchMade.setValue(null);
                } else {
                    integerList.add(currentPosition);
                    calculateScore(levelOfGame);
                }
            }
        }
    }

    //Function to revert the images into previous states if they dont match
    private void revertTheImages(int currentPosition, int position, int levelOfGame) {
        Image_ image = imageUrls.get(currentPosition);
        image.setClickStatus(false);
        image.setCheckStatus(false);
        mRecyclerViewUpdateTrigger.itemChanged(currentPosition, image);

        //Reverting the images based on level and updating the integer list.
        if (integerList.size() % levelOfGame == 1){
            Image_ previousImage = imageUrls.get(integerList.get(position));
            previousImage.setClickStatus(false);
            mRecyclerViewUpdateTrigger.itemChanged(integerList.get(position),previousImage);
            integerList.remove(position);
        } else if (integerList.size() % levelOfGame == 2) {
            Image_ firstPreviousImage = imageUrls.get(integerList.get(position));
            Image_ secondPreviousImage = imageUrls.get(integerList.get(position + 1));
            firstPreviousImage.setClickStatus(false);
            secondPreviousImage.setClickStatus(false);
            mRecyclerViewUpdateTrigger.itemChanged(integerList.get(position),firstPreviousImage);
            mRecyclerViewUpdateTrigger.itemChanged(integerList.get(position + 1),secondPreviousImage);
            integerList.remove(position);
            integerList.remove(position);
        } else if (integerList.size() % levelOfGame == 3) {
            Image_ firstPreviousImage = imageUrls.get(integerList.get(position));
            Image_ secondPreviousImage = imageUrls.get(integerList.get(position + 1));
            Image_ thirdPreviousImage = imageUrls.get(integerList.get(position + 2));
            firstPreviousImage.setClickStatus(false);
            secondPreviousImage.setClickStatus(false);
            thirdPreviousImage.setClickStatus(false);
            mRecyclerViewUpdateTrigger.itemChanged(integerList.get(position),firstPreviousImage);
            mRecyclerViewUpdateTrigger.itemChanged(integerList.get(position + 1),secondPreviousImage);
            mRecyclerViewUpdateTrigger.itemChanged(integerList.get(position + 2),thirdPreviousImage);
            integerList.remove(position);
            integerList.remove(position);
            integerList.remove(position);
        }
    }

    //Method to calculate the score in each level.
    private void calculateScore(int levelOfGame) {
        if (levelOfGame == 2 && integerList.size() % levelOfGame == 0){
            int score = integerList.size()/2;
            stringResourceShower.setValue(score + "");
        }else if (levelOfGame == 3 && integerList.size() % levelOfGame == 0){
            int score = integerList.size()/3;
            stringResourceShower.setValue(score + "");
        }else if (levelOfGame == 4 && integerList.size() % levelOfGame == 0){
            int score = integerList.size()/4;
            stringResourceShower.setValue(score + "");
        }
    }

    //Method to find the previous position depending on the level of game using the modulo operator.
    private int getThePreviousPositionBasedONLevel(int levelOfGame) {
        if (integerList.size() % levelOfGame == 0){
            return -1;
        } else if (integerList.size() % levelOfGame == 1){
            return integerList.size() - 1;
        } else if (integerList.size() % levelOfGame == 2) {
            return integerList.size() - 2;
        } else if (integerList.size() % levelOfGame == 3) {
            return integerList.size() - 3;
        }

        return 0;

    }

    public void getProductlist(Context context) {
        memoryGameResponseLiveData = callProductApi(context);
    }

    //Api call to retrive product list using retrofit client
    private ApiResponseLiveData<MemoryGameResponse> callProductApi(final Context context) {
        final ApiResponseLiveData<MemoryGameResponse> apiResponseLiveData = new ApiResponseLiveData<>();
        ApiInterface service = RetrofitClientInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<MemoryGameResponse> call = service.getShopifyMemoryGameResponse("1","c32313df0d0ef512ca64d5b336a0d7c6");
        call.enqueue(new Callback<MemoryGameResponse>() {
            @Override
            public void onResponse(Call<MemoryGameResponse> call, Response<MemoryGameResponse> response) {
                apiResponseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MemoryGameResponse> call, Throwable t) {
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        return apiResponseLiveData;
    }


    //Alert giving infos about each level.
    public  void levelAlert(final Context context, String message, final int level, final String title){
        final Activity activity = (Activity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.todoDialogLight);
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(activity,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("TITLE",title);
                bundle.putInt("LEVEL",level);
                intent.putExtras(bundle);
                activity.startActivity(intent);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }


}

package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.example.memorygame.adapters.CustomRecyclerViewAdapter;
import com.example.memorygame.livedatas.ApiResponseLiveData;
import com.example.memorygame.livedatas.RecyclerViewUpdateLiveData;
import com.example.memorygame.livedatas.StringResourceShower;
import com.example.memorygame.livedatas.VoidLiveData;
import com.example.memorygame.models.Image_;
import com.example.memorygame.models.MemoryGameResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomRecyclerViewAdapter.OnRecyclerViewImageSelectedListener {

    private RecyclerView rv_memory_game;
    private CustomRecyclerViewAdapter customRecyclerViewAdapter;
    private int levelOfGame;
    private String title;
    private GameViewModel gameViewModel;
    private int currentPosition = -1;
    private TextView score;
    private TextView title_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();

        levelOfGame = b.getInt("LEVEL", 0);
        title = b.getString("TITLE");

        rv_memory_game = findViewById(R.id.rv_memory_game);
        score = findViewById(R.id.score);
        title_desc = findViewById(R.id.title_desc);

        title_desc.setText(title);

        //Connection with the view model.
        gameViewModel = ViewModelProviders.of(MainActivity.this).get(GameViewModel.class);
        gameViewModel.getStringResourceShower().observe(MainActivity.this, stringShower);
        gameViewModel.getNoMatchMade().observe(MainActivity.this, noMatchMade);

        //Getting the product list by calling the api
        gameViewModel.getProductlist(MainActivity.this);
        Util.showProgressDialog(MainActivity.this);
        gameViewModel.getMemoryGameResponseLiveData().observe(MainActivity.this,memoryGameApiObserver);

        GridLayoutManager manager = new GridLayoutManager(this, 5);
        rv_memory_game.setLayoutManager(manager);


        rv_memory_game.setLayoutManager(manager);
        gameViewModel.getRecyclerViewUpdateTrigger().observe(MainActivity.this, recyclerViewObserver);
        customRecyclerViewAdapter = new CustomRecyclerViewAdapter(gameViewModel.getImages(),this,this);
        rv_memory_game.setAdapter(customRecyclerViewAdapter);
    }


    //Individual recycler view item click which gives the current position.
    @Override
    public void imageClicked(int position, Object o) {
        currentPosition = position;
        gameViewModel.sendImagePosition(currentPosition,levelOfGame);
    }


    //Recycler view update live data to update the changes in recycler view
    private RecyclerViewUpdateLiveData.RecyclerViewItemObserver<Image_> recyclerViewObserver = new RecyclerViewUpdateLiveData.RecyclerViewItemObserver<Image_>(){

        @Override
        public void allItemChanged() {
            customRecyclerViewAdapter.notifyDataSetChanged();
        }

        @Override
        public void singleItemChanged(int position, Image_ resource) {
            customRecyclerViewAdapter.notifyItemChanged(position,resource);

        }

        @Override
        public void itemInserted(int position, Image_ resource) {

        }

        @Override
        public void itemRemoved(int position) {

        }

        @Override
        public void allItemUpdate(List<Image_> items) {

        }
    };

    //Api response live data to get and explore the api response
    private ApiResponseLiveData.MemoryGameApiObserver<MemoryGameResponse> memoryGameApiObserver = new ApiResponseLiveData.MemoryGameApiObserver<MemoryGameResponse>() {
        @Override
        public void showSuccessResponse(MemoryGameResponse response) {
            Util.dismissProgressDialog();
            gameViewModel.exploreMemoryGameResponse(response,levelOfGame);
        }
    };

    //String live data to show the score and play the sound using Media player
    private StringResourceShower.ResourceShowerObserver stringShower = new StringResourceShower.ResourceShowerObserver() {
        @Override
        public void showString(String message) {
            score.setText(message);
            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.quiz_right_answer);
            mediaPlayer.start();
            
            if (message.equalsIgnoreCase("10")){
                showWinAlert();
            }
        }
    };

    //Win Alert after selecting 10pairs
    private void showWinAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.todoDialogLight);
        builder.setMessage("Congratz. You Found 10 Pairs. You Won");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    private VoidLiveData.VoidObserver noMatchMade = new VoidLiveData.VoidObserver() {
        @Override
        public void value() {
            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.quiz_wrong_answer);
            mediaPlayer.start();
        }
    };


}

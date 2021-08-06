package com.example.Adama;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    SongCollection songCollection = new SongCollection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.item_1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_1:
                        return true;
                    case R.id.item_2:
                        startActivity(new Intent(getApplicationContext(),search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.item_3:
                        startActivity(new Intent(getApplicationContext(),my_music.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.item_4:
                        startActivity(new Intent(getApplicationContext(),profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        }

    public void handleSelection(View view) {
        String resourceId = getResources().getResourceEntryName(view.getId());
        int currentArrayIndex = songCollection.searchSongById(resourceId);
        Log.d("Temasek", "button clicked" + resourceId);
        Log.d("Temasek", "button clicked" + currentArrayIndex);
        sendDataToActivity(currentArrayIndex);
    }

    public void sendDataToActivity(int index){
        Intent intent = new Intent(this,PlaySongActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
    }




}
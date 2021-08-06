package com.example.Adama;

import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    SongCollection songCollection = new SongCollection();
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayList<String> list1;

    ArrayAdapter<String> adapter;
    private MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.item_2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_1:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item_2:

                        return true;
                    case R.id.item_3:
                        startActivity(new Intent(getApplicationContext(), my_music.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item_4:
                        startActivity(new Intent(getApplicationContext(), profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        list = new ArrayList<>();
        list.add("S1001");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Lychee");
        list.add("Gavava");
        list.add("Peech");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");
        list.add("S1001");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Lychee");
        list.add("Gavava");
        list.add("Peech");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");
        list.add("S1001");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Lychee");
        list.add("Gavava");
        list.add("Peech");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");
        list.add("S1001");
        list.add("Banana");
        list.add("Pineapple");
        list.add("Orange");
        list.add("Lychee");
        list.add("Gavava");
        list.add("Peech");
        list.add("Melon");
        list.add("Watermelon");
        list.add("Papaya");



        list1 = new ArrayList<>();
        list1.add("S1001");
        list1.add("Banana");
        list1.add("Pineapple");
        list1.add("Orange");
        list1.add("Lychee");
        list1.add("Gavava");
        list1.add("Peech");
        list1.add("Melon");
        list1.add("Watermelon");
        list1.add("Papaya");
        list1.add("Watermelon");
        list1.add("Papaya");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (list.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(search.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        /*listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(ListView parent, View view, int position, long id) {
                Object listItem = list.get(position);
                Intent n = new Intent(getApplicationContext(), PlaySongActivity.class);
                n.putExtra("position", position);
                Toast.makeText(search.this, parent? getItemat+ "", Toast.LENGTH_SHORT).show();
                startActivity(n);
               *//* Toast.makeText(search.this, listItem + "", Toast.LENGTH_SHORT).show();
                String list2 = list1.get(3);
                //Toast.makeText(search.this, list2+"", Toast.LENGTH_SHORT).show();
                int list3 = list.indexOf("orange");
                Toast.makeText(search.this, list3 + "", Toast.LENGTH_SHORT).show();
                for (int item = 0; item < list1.size(); item++) {
                    if (list.get(id) == list1.get(item)) {
                        sendDataToActivity(item);
                        break;

                    }
                }
                //sendDataToActivity(1);
            }
        });*/

    }
}
    /*public void handleSelection(View view) {
        String resourceId = getResources().getResourceEntryName(view.getId());
       /// for(int currentArrayIndex = 0)
        int currentArrayIndex = 1;
                ///songCollection.searchSongById(resourceId);
        Log.d("Temasek", "button clicked" + resourceId);
        Log.d("Temasek", "button clicked" + currentArrayIndex);
        //sendDataToActivity(currentArrayIndex);



    public void sendDataToActivity(int index){
        Intent intent = new Intent(this,PlaySongActivity.class);
        intent.putExtra("index",index);
        startActivity(intent);
  }  }

*/







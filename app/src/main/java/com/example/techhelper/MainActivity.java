package com.example.techhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView cardMap, cardList, cardCamera;
    static ListView listView;
    static ArrayList<String> items;
    static PreviewViewAdapter adapter;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        functionActivity();
    }

    @Override
    public void onRestart() {
        //calls onRestart so the app updates when user returns back to MainActivity
        super.onRestart();
        functionActivity();
    }

    //moved function into new method to allow onRestart
    private void functionActivity() {
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewMain);
        cardMap = (CardView) findViewById(R.id.mapCard);
        cardList = (CardView) findViewById(R.id.listCard);
        cardCamera = (CardView) findViewById(R.id.cameraCard);

        cardMap.setOnClickListener(this);
        cardList.setOnClickListener(this);
        cardCamera.setOnClickListener(this);

        items = new ArrayList<>();

        context = getApplicationContext();

        //setting the list preview
        adapter = new PreviewViewAdapter(getApplicationContext(), items);
        listView.setAdapter(adapter);

        // loads content from file to update preview
        loadContent();
    }


    public void loadContent(){

        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, "list.txt");
        byte[] content = new byte[(int) readFrom.length()];

        //read from file, turn into String, populate preview
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);

            String s = new String(content);
            s = s.substring(1, s.length() - 1);
            String split[] = s.split(", ");
            items = new ArrayList<>(Arrays.asList(split));
            adapter = new PreviewViewAdapter(this, items);
            listView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void onClick(View view) {
        Intent intent;

        //buttons to open new activities
        switch (view.getId()){
            case R.id.mapCard:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.listCard:
                intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.cameraCard:
                intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}
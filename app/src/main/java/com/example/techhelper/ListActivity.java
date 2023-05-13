package com.example.techhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity {

    static ListView listView;
    static ArrayList<String> items;
    static ListViewAdapter adapter;
    static Context context;

    EditText input;
    ImageView enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.add);

        items = new ArrayList<>();

        context = getApplicationContext();
        adapter = new ListViewAdapter(getApplicationContext(), items);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks to see if something was entered, if yes add to Arraylist
                String text = input.getText().toString();
                if (text == null || text.length() == 0) {
                    makeToast("Enter an item!");
                }
                else{
                    addItem(text);
                    input.setText("");
                    makeToast("Added item: "+text+ " to List!");
                    System.out.println(items);
                }
            }
        });
        loadContent();
    }
//methods to save and read content from file to preserve list data on close
//read from file
    public void loadContent(){

        File path = getApplicationContext().getFilesDir();

        File readFrom = new File(path, "list.txt");
        byte[] content = new byte[(int) readFrom.length()];

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);

            String s = new String(content);
            s = s.substring(1, s.length() - 1);
            String split[] = s.split(", ");
            items = new ArrayList<>(Arrays.asList(split));
            adapter = new ListViewAdapter(this, items);
            listView.setAdapter(adapter);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {

        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, "list.txt"));
            writer.write(items.toString().getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onPause();
    }

    public static void addItem(String item){
        items.add(item);
        listView.setAdapter(adapter);
    }

    public static void removeItem(int remove){
        items.remove(remove);
        listView.setAdapter(adapter);
    }

    //toast to inform user
        Toast t;
        private void makeToast(String s){
            if (t != null) t.cancel();
            t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
            t.show();
        }



}
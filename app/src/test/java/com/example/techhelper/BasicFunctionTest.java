package com.example.techhelper;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicFunctionTest {
    static ArrayList<String> items;
    static ArrayList<String> test;
    @Test
    public void addItem() {

        //tests add to arraylist true
        String s = "Test";

        items = new ArrayList<>();
        
        assert(items.add(s));


    }

    @Test
    public void removeItem() {
        //test fails because test index is out of bounds, expected
        int pos = 1;
        items = new ArrayList<>();
        items.remove(pos);
        assert(items.remove(true));
    }

    @Test
    public void stringSplit() {
        //tested for multiple methods
        //tests to convert to string when reading from file
        //test failed successfully???? assert returns fail but Arraylists are the same output
        items = new ArrayList<>();
        items.add("reg");
        items.add("mick");
        items.add("john");
        test = new ArrayList<>();


        String raw = " reg, mick, john ";
        String s = new String(raw);

        s = s.substring(1, s.length() - 1);
        String split[] = s.split(", ");
        test = new ArrayList<>(Arrays.asList(split));

        assertSame(test, items);



    }
}
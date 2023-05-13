package com.example.techhelper;

import static org.junit.Assert.*;

import androidx.test.InstrumentationRegistry;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class IntegratedTest {

    @Test
    public void existContent() {
        //test for file existing
        //test shows it exists
        File isReal = new File(InstrumentationRegistry.getTargetContext().getFilesDir(),"list.txt");
        assert(isReal.exists());
    }

}
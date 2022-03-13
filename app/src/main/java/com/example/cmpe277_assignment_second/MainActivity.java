package com.example.cmpe277_assignment_second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private String TAGNAME = "ThisIsMainActivity";

    EditText edt1,edt2,edt3,edt4,edt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDownloadFileStart(View view){
        Log.e(TAGNAME, "onClickDownloadFileStart");
        ArrayList<String> allFiles = getFiles();

        Intent serviceIntent =  new Intent(this, AndroidBackgroundServices.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("allFiles", allFiles);
        serviceIntent.putExtras(bundle);
        startService(serviceIntent);
    }

    private ArrayList<String> getFiles()
    {

        edt1 = (EditText) findViewById(R.id.pdf1);
        String str1 = edt2.getText().toString();

        edt2 = (EditText) findViewById(R.id.pdf2);
        String str2 = edt2.getText().toString();

        edt3 = (EditText) findViewById(R.id.pdf3);
        String str3 = edt3.getText().toString();

        edt4 = (EditText) findViewById(R.id.pdf4);
        String str4 = edt4.getText().toString();

        edt5 = (EditText) findViewById(R.id.pdf5);
        String str5 = edt5.getText().toString();

        ArrayList<String> allFiles = new ArrayList<String>();
        allFiles.add(str1);
        allFiles.add(str2);
        allFiles.add(str3);
        allFiles.add(str4);
        allFiles.add(str5);
        Log.e(TAGNAME, "get Files" + allFiles);

        return allFiles;
    }
}
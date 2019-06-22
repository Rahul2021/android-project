package com.example.rahulsingh.text_to_speech;

import android.graphics.Color;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static java.util.Locale.UK;

public class MainActivity extends AppCompatActivity  {
    EditText mTextEt;
    Button mStopBtn;
    Button mSpeakBtn;
    TextToSpeech mTTS;

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= (Toolbar)findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Text To Speech ");
        toolbar.setSubtitle(" Developed by Rahul Singh");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.CYAN);


        mTextEt =(EditText) findViewById(R.id.textView3);
        mSpeakBtn =(Button) findViewById(R.id.button1);
        mStopBtn=(Button) findViewById(R.id.button2);


        mTTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!= TextToSpeech.ERROR){
                    mTTS.setLanguage(Locale.UK);

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSpeakBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                String toSpeak =mTextEt.getText().toString().trim();
                if(toSpeak.equals("")){
                    Toast.makeText(MainActivity.this,"Please enter text...",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,toSpeak,Toast.LENGTH_SHORT).show();
                    mTTS.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });
        mStopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mTTS.isSpeaking()){
                    mTTS.stop();
                    mTTS.shutdown();
                }
                else{
                    Toast.makeText(MainActivity.this,"Not Speaking",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    @Override
    protected  void onPause(){
        if(mTTS !=null || mTTS.isSpeaking()){
            mTTS.stop();
        }
        super.onPause();
    }
}

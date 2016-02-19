package me.bowarren.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public boolean go = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                //begin my own
//                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//                SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
//                recognizer.setRecognitionListener(new VoiceRecognitionListener(recognizer, intent));
//                recognizer.startListening(intent);

//                while(true){
//                    if(go==true) {
//                        go=false;
                recognizeSpeech();
                Log.e("fff", "after starting recog");
//                    }
//                }

                //Log.e("fff", "after starting recog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void recognizeSpeech(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, new Long(15000));
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, new Long(15000));
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, new Long(15000));
        SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        recognizer.setRecognitionListener(new VoiceRecognitionListener(recognizer, intent));
        recognizer.startListening(intent);
    }

    class VoiceRecognitionListener implements RecognitionListener {
        //VoiceRecognition mVoiceRecognition;
        //private static final String TAG = "VoiceRecognitionListener";
        SpeechRecognizer recognizer;
        Intent intent;

        public VoiceRecognitionListener(SpeechRecognizer recognizer, Intent intent){
            this.recognizer = recognizer;
            this.intent = intent;
        }

        public void onResults(Bundle data) {
            //Log.d(TAG, "onResults " + data);
            ArrayList<String> matches = data.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            Log.e("ffff", matches.toString());
//            float[] value = data.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);
//            mVoiceRecognition.mText.setText("Results: " + String.valueOf(matches.size()));
//
//            if(value != null) { // CONFIDENCE_SCORES wasn't added until API level 14
//                String[] combined = new String[matches.size()];
//                for(int i = 0; i < matches.size(); i++) // The size of the data and value is the same
//                    combined[i] = matches.get(i).toString() + "\nScore: " + Float.toString(value[i]);
//                mVoiceRecognition.mList.setAdapter(new ArrayAdapter<String>(mVoiceRecognition, android.R.layout.simple_list_item_1,combined));
//            } else
//                mVoiceRecognition.mList.setAdapter(new ArrayAdapter<String>(mVoiceRecognition, android.R.layout.simple_list_item_1,matches));
            recognizeSpeech();

//            new Thread() {
//                public void run() {
//
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            recognizeSpeech();
//                        }
//                    });
//                }
//            }.start();
        }


        String TAG = "FFfff";
        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
//            mVoiceRecognition.mText.setText("Sounding good!");

        }
        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
//            mVoiceRecognition.mText.setText("Waiting for result...");
//            recognizer.stopListening();
//            recognizeSpeech();


        }
        public void onError(int error) {
            Log.d(TAG, "error " + error);
            //error 6 = speech timeout, so start it on that again
        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults");
        }
        public void onReadyForSpeech(Bundle params) {
//            Log.d(TAG, "onReadyForSpeech");
        }
        public void onRmsChanged(float rmsdB) {
//            Log.d(TAG, "onRmsChanged");
        }
    }
}

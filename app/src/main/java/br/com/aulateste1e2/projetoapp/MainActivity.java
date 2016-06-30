package br.com.aulateste1e2.projetoapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech ttsobject; // cria objeto para a fala
    int result;
    EditText et;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);

        /*
        * TextToSpeech(Context,TextToSpeech.OnInitListener Listener )
        * Context: Local em que instância está executando
        * TextToSpeech.OnInitListener: Inicializa quando a engine do TextToSpeech é inicializada. Em caso de falha o listener pode ser chamado imediatamente,
        * antes da instância do TextToSpeech ser totalmente construída.
        * */
        ttsobject = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = ttsobject.setLanguage(Locale.ENGLISH);
                } else {
                    Toast.makeText(getApplicationContext(), "Função não disponível para seu dispositvo", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
        public void doSomething(View v){
        switch (v.getId()){
            case R.id.fala:
                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(getApplicationContext(), "Função não disponível para seu dispositvo", Toast.LENGTH_SHORT).show();
                }else{
                    text = et.getText().toString();
                    //ttsobject.speak(text,TextToSpeech.QUEUE_FLUSH,null);//método antigo
                    //speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null,null);//método novo exemplo: https://developer.android.com/reference/android/speech/tts/TextToSpeech.html#speak(java.lang.CharSequence,%20int,%20android.os.Bundle,%20java.lang.String)
                    ttsobject.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);

                }   /*int speak (CharSequence text,
                int queueMode,
                Bundle params,
                String utteranceId)*/
                break;
            case R.id.para:
                if (ttsobject != null){

                    ttsobject.stop();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ttsobject != null) {

            ttsobject.stop();
            ttsobject.shutdown();
        }
    }
}





package com.developer.allef.machinelearningandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ai.api.AIListener;
import ai.api.model.AIError;
import ai.api.model.AIResponse;

public class MainActivity extends AppCompatActivity implements AIListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResult(AIResponse result) { // é aqui onde você processa o resultado da requisição (a resposta)

    }

    @Override
    public void onError(AIError error) { // chamado caso ocorra um erro

    }

    @Override
    public void onAudioLevel(float level) {// indica o nível do som quando o usuário está a falar

    }

    @Override
    public void onListeningStarted() {// quando o microfone é activado

    }

    @Override
    public void onListeningCanceled() {// quando o microfone é cancelado (porque o usuário cancelou ou ocorreu um erro)

    }

    @Override
    public void onListeningFinished() {// quando o microfone é desactivado (porque o usuário terminou de falar)

    }
}

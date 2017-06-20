package com.developer.allef.machinelearningandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonElement;

import java.util.Map;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements AIListener{
    private AIConfiguration config;
    private AIService service;
    private Button falar;
    private TextView Status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falar = (Button)findViewById(R.id.button);
        Status = (TextView)findViewById(R.id.textView);



        config = new AIConfiguration("9845c17bfa80410f8ae387dff4b7730b",
                AIConfiguration.SupportedLanguages.PortugueseBrazil,
                AIConfiguration.RecognitionEngine.System);
        service = AIService.getService(this, config);
        service.setListener(this);

        falar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.startListening();
                service.stopListening();
            }
        });

    }

    @Override
    public void onResult(AIResponse result) { // é aqui onde você processa o resultado da requisição (a resposta)
        Result results = result.getResult();

        // Obter os parametros caso existam
        String parameterString = "";
        if (results.getParameters() != null && !results.getParameters().isEmpty()) {
            for (final Map.Entry<String, JsonElement> entry : results.getParameters().entrySet()) { // percorrendo o arquivo Json
                parameterString += "(" + entry.getKey() + ", " + entry.getValue() + ") ";
            }
        }

        // Show results in TextView.
        Status.setText("Oque Foi Dito:" + results.getResolvedQuery() + //A frase que o utilizador usou
                "\nResposta AI: " + results.getFulfillment().getSpeech() + //A resposta
                "\nParametros: " + parameterString); //Os parametros

    }

    @Override
    public void onError(AIError error) { // chamado caso ocorra um erro

    }

    @Override
    public void onAudioLevel(float level) {// indica o nível do som quando o usuário está a falar

    }

    @Override
    public void onListeningStarted() {// quando o microfone é activado
        Status.setText("Escutando...");
    }

    @Override
    public void onListeningCanceled() {// quando o microfone é cancelado (porque o usuário cancelou ou ocorreu um erro)

    }

    @Override
    public void onListeningFinished() {// quando o microfone é desactivado (porque o usuário terminou de falar)
        Status.setText("Escutando...");
    }
}

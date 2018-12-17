package com.example.jaimepm.andrevinanum;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String name;
    private int attempts = 0;
    private int num;
    public static List<Player> jugadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }

    private void start() {
        getName();
        final Button button = findViewById(R.id.button);
        final Button botonRecord = findViewById(R.id.button2);
        num = (int) (Math.random() * 100 + 1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    isTheNumber();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        botonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hallOfFame();
            }
        });
    }

    public void isTheNumber() throws IOException {
        final EditText editText = findViewById(R.id.editText);
        String st = String.valueOf(editText.getText());
        int numero = Integer.parseInt(st);

        if (numero > num) {
            attempts++;
            Context context = getApplicationContext();
            CharSequence text = "El número es más grande que el introducido";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if (numero < num) {
            Context context = getApplicationContext();
            CharSequence text = "El número es más pequeño que el introducido";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            attempts++;
        }
        else if (numero == num) {
            Player player = new Player(name,attempts);
            jugadores.add(player);
            createFile(player);
            Context context = getApplicationContext();
            CharSequence text = "Adivinado!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            num = (int) (Math.random() * 100 + 1);
            getName();
        }
    }

    private void createFile(Player player) throws IOException {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("savePlayers.txt",Context.MODE_APPEND));
            osw.write(player.getName() + ";" + player.getAttempts());
            osw.append("\r\n");
            osw.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getName(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Usuario");
        dialog.show();
        Button register = dialog.findViewById(R.id.botonDialog);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText textName = dialog.findViewById(R.id.etNombre);
                name = textName.getText().toString();
                dialog.dismiss();
            }
        });
        return name;
    }

    public void hallOfFame() {
        startActivity(new Intent(this, HallOfFame.class));
    }

}


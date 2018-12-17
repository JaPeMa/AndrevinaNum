package com.example.jaimepm.andrevinanum;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HallOfFame extends Activity{
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        try {
            showData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showData() throws IOException {
        players = new ArrayList<>();
        readFile();
        final TextView tablaRecord = findViewById(R.id.record);
        tablaRecord.setText("");
        if(players.size()>0){
            Collections.sort(players);
            for (Player player:players)
                tablaRecord.setText(tablaRecord.getText() + player.toString());
        }else
            tablaRecord.setText("No hay datos");
    }

    private void readFile() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("savePlayers.txt")));
            String datos;
            while((datos = br.readLine())!=null){
                String[] arrPlayers = datos.split(",");
                players.add(new Player(arrPlayers[0],Integer.parseInt(arrPlayers[1])));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
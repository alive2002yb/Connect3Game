package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //0 red , 1 blue
    int won=0;
    int[][] gamestate ={{2,2,2},{2,2,2},{2,2,2}};
//  int[][] gamestate ={{0,1,2},{3,4,5},{6,7,8}};
//  winning methods = {012,345,678,036,147,258,048,246}
    int activeplayer=0;
    public boolean wincheck(){
        int[][] methods = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int a,b,c;
        for (int i = 0; i <methods.length ; i++) {
            a=methods[i][0];
            b=methods[i][1];
            c=methods[i][2];
            if (gamestate[a/3][a%3]==gamestate[b/3][b%3]&&gamestate[b/3][b%3]==gamestate[c/3][c%3]&&gamestate[b/3][b%3]!=2){
                return true;
            }
        }
        return false;
    }

    public void dropColour(View view) {
        if (won!=0){
            return;
        }
        ImageView counter = (ImageView) view;
        int index = Integer.parseInt((String) counter.getTag());
        int row = index / 3;
        int column = index % 3;
        if (gamestate[row][column] != 2) {
            Toast.makeText(this, "Already Played", Toast.LENGTH_SHORT).show();
            return;
        }
        gamestate[row][column] = activeplayer;
        counter.setTranslationY(-1500);
        if (activeplayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            counter.animate().translationYBy(1500).setDuration(300);
        } else if (activeplayer == 1) {
            counter.setImageResource(R.drawable.red);
            counter.animate().translationYBy(1500).setDuration(300);

        }
        Button but = findViewById(R.id.button);
        TextView txt = (TextView) findViewById(R.id.connect);
        if (wincheck() == true && activeplayer == 0) {
            won=1;
            txt.setText("Yellow Won!!!!");
            txt.setTextColor(Color.YELLOW);
            but.setText("Play Again");

        } else if (wincheck() == true && activeplayer == 1) {
            won=1;
            txt.setText("Red Won!!!!");
            txt.setTextColor(Color.RED);
            but.setText("Play Again");
        }
        else {
            activeplayer++;
            activeplayer = activeplayer % 2;

            for (int i = 0; i < gamestate.length; i++) {
                for (int j = 0; j <gamestate[1].length ; j++) {
                    if (gamestate[i][j]==2){
                        return;
                    }
                }
            }
            but.setText("Play Again");
        }
    }
    public void resetFunction(View view){
        won=0;
        ImageView img;
        activeplayer=0;
        for (int i = 0; i < gamestate[1].length; i++) {
            for (int j = 0; j < gamestate[1].length; j++) {
                gamestate[i][j]=2;
            }
        }
        img=findViewById(R.id.imageView1);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView2);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView3);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView4);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView5);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView6);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView7);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView8);
        img.setImageResource(android.R.color.transparent);
        img=findViewById(R.id.imageView9);
        img.setImageResource(android.R.color.transparent);
        TextView txt = (TextView) findViewById(R.id.connect);
        txt.setText("Connect 3 Game");
        Button but = findViewById(R.id.button);
        but.setText("Reset");
        txt.setTextColor(Color.WHITE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
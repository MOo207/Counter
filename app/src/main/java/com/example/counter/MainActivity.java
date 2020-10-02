package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private ImageButton increase, reset;
    private TextView counterLabel;
    private TextSwitcher prayLabel;
    private int counter = 0;
    private String[] prays = {"سبحان الله","الحمد لله" , "الله أكبر", "لا اله الا الله وحده لا شريك له له الملك وله الحمد وهو على كل شيء قدير"};
    private Animation in;
    private Animation out;
    private Vibrator viberator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewSetup();

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viberator.vibrate(50);
             if(counter < 33){
                 increaseCounter(0);
             }  else if(counter < 66){
                 increaseCounter(1);
             } else if(counter < 99){
                 increaseCounter(2);
             } else if(counter == 99){
                 increaseCounter(3);
             } else{
                 resetCounter();
             }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viberator.vibrate(500);
                resetCounter();
            }
        });

    }

    public void viewSetup(){
        in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        out =  AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        increase = findViewById(R.id.increase);
        reset = findViewById(R.id.reset);
        viberator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        counterLabel = findViewById(R.id.counter);
        prayLabel = findViewById(R.id.pray);

        prayLabel.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                TextView t = new TextView(MainActivity.this);
                t.setGravity(Gravity.CENTER);
                t.setTextColor(Color.BLACK);
                t.setText("الذكر");
                t.setTextSize(40);
                return t;
            }
        });
        inAnimate();
    }

    void increaseCounter(int index){
        ++counter;
        counterLabel.setText(String.valueOf(counter));
        inAnimate();
        if(index == 3){
            ((TextView)prayLabel.getNextView()).setTextSize(20);
            prayLabel.setText(prays[index]);
        } else {
            ((TextView)prayLabel.getNextView()).setTextSize(40);
            prayLabel.setText(prays[index]);
        }
    }

    void resetCounter(){
        counter = 0;
        counterLabel.setText(String.valueOf(counter));
        outAnimate();
        ((TextView)prayLabel.getNextView()).setTextSize(40);
        prayLabel.setText("الذكر");
    }

    void inAnimate(){
        prayLabel.startAnimation(in);
    }


    void outAnimate(){
        prayLabel.startAnimation(out);
    }
}
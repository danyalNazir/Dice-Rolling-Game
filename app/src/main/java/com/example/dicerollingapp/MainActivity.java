package com.example.dicerollingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button increaseButton, decreaseButton, resetButton, rollButton;
    TextView textView;
    ImageView imageView;
    int count;
    int flag;
    static int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        increaseButton=findViewById(R.id.increaseButton);
        decreaseButton=findViewById(R.id.DecreaseButton);
        resetButton=findViewById(R.id.resetButton);
        rollButton=findViewById(R.id.rollButton);
        textView=findViewById(R.id.textView);
        imageView=findViewById(R.id.imageView);
        counter=3;
        count=0;
        flag=0;

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0)
                {
                    count=Integer.parseInt(textView.getText().toString());
                    if(count>=0 && count<6)
                    {
                        count+=1;
                        textView.setText(String.valueOf(count));
                    }
                    else
                        Toast.makeText(MainActivity.this,"Dice value couldn't be greater than 6!",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Can't change lucky number after rolling!",Toast.LENGTH_SHORT).show();
            }
        });
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0)
                {
                    count=Integer.parseInt(textView.getText().toString());
                    if(count>1 && count<=6)
                    {
                        count-=1;
                        textView.setText(String.valueOf(count));
                    }
                    else
                        Toast.makeText(MainActivity.this,"Dice value couldn't be smaller than 1!",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Can't change lucky number after rolling!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ResetButtonClick(View v)
    {
        count=0;
        flag=0;
        counter=3;
        textView.setText("0");
    }

    public void RollButtonClick(View v) {

        count = Integer.parseInt(textView.getText().toString());
        if (count != 0) {
            Dice dice = new Dice();
            int face = dice.roll();
            int imageResource;
            switch (face) {
                case 1:
                    imageResource = R.drawable.dice_1;
                    break;
                case 2:
                    imageResource = R.drawable.dice_2;
                    break;
                case 3:
                    imageResource = R.drawable.dice_3;
                    break;
                case 4:
                    imageResource = R.drawable.dice_4;
                    break;
                case 5:
                    imageResource = R.drawable.dice_5;
                    break;
                default:
                    imageResource = R.drawable.dice_6;
                    break;
            }
            imageView.setImageResource(imageResource);
            imageView.setContentDescription(String.valueOf(face));
            flag = 1;
            counter -= 1;

            if (face == count) {
                Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
                ResetButtonClick(v);
                return;
            } else if (counter > 0) {
                Toast.makeText(MainActivity.this, counter + " tries remaining!", Toast.LENGTH_SHORT).show();
            }
            else if(counter == 0)
            {
                Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
                ResetButtonClick(v);
                return;
            }
        } else
            Toast.makeText(MainActivity.this, "Set Lucky Number!", Toast.LENGTH_SHORT).show();
    }

    class Dice {
        private int numSides=6;
        public int roll(){
            return  (int) Math.floor(Math.random()*(6-1+1)+1);
        }
    }
}
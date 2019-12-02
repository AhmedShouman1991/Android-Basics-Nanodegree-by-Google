package com.example.simpaquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private double score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getScore(View view) {
        q1(); q2(); q3(); q4();
        EditText e = findViewById(R.id.name);
        String name = e.getEditableText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Pleas type your name", Toast.LENGTH_SHORT).show();
        }else {
            String statue;
            double percent = (score/4.0) * 100;
            if (percent > 50) {
                statue = "Your percent = "+ percent;
            } else if (percent < 50) {
                statue = "Your percent = "+ percent;
            } else {
                statue = "Your percent = "+ percent;
            }
            Toast.makeText(this, statue, Toast.LENGTH_LONG).show();
        }
        score = 0;
    }

    private void q1() {
        RadioGroup r = findViewById(R.id.G1);
        int checked = r.getCheckedRadioButtonId();
        if (checked == R.id.mofasa_radio_button) {
            score++;
        }
    }
    private void q2() {
        CheckBox c1 = findViewById(R.id.c1);
        CheckBox c3 = findViewById(R.id.c3);
        boolean c1Checked = c1.isChecked();
        boolean c3Checked = c3.isChecked();

        if (c1Checked) {
            score += 0.5;
        }
        if (c3Checked) {
            score += 0.5;
        }

    }

    private void q3() {
        EditText e = findViewById(R.id.answer1);
        try {
            int answer = Integer.parseInt(e.getEditableText().toString());
            if (answer == 4) {
                score++;
            }
        }
        catch (Exception e1) {
            Toast.makeText(this, "only numbers are allowed in q3",Toast.LENGTH_SHORT).show();
        }
    }

    private void q4() {
        EditText e = findViewById(R.id.answer2);
        String answer = e.getEditableText().toString().toUpperCase();
        if (answer.equals("SCARE")) {
            score++;
        }
    }
}

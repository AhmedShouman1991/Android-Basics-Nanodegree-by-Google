/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.new_main);
        PagerAdapter pagerAdapter =new PagerAdapterClass(getSupportFragmentManager());

        ViewPager pager = findViewById(R.id.view_pager);
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tap_layout);
        tabLayout.setupWithViewPager(pager);

//        TextView n = findViewById(R.id.numbers);
//        Intent numbers = new Intent(MainActivity.this, NumbersActivity.class);
//        addOnClickListener(n, numbers);
//
//        TextView f = findViewById(R.id.family);
//        Intent family = new Intent(MainActivity.this, FamilyActivity.class);
//        addOnClickListener(f, family);
//
//        TextView c = findViewById(R.id.colors);
//        Intent colors = new Intent(MainActivity.this, ColorsActivity.class);
//        addOnClickListener(c, colors);
//
//        TextView p = findViewById(R.id.phrases);
//        Intent phrase = new Intent(MainActivity.this, PhraseActivity.class);
//        addOnClickListener(p, phrase);



        //addLongClickListener();
    }

//    public void addOnClickListener(TextView t, final Intent intent) {
//
//        t.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(intent);
//            }
//        });
//    }


//    public void addLongClickListener() {
//        TextView textView = findViewById(R.id.numbers);
//        textView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(v.getContext(), "longClicked", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//    }
////    public void openNumbersList(View view) {
////        Intent intent = new Intent(this, NumbersActivity.class);
////        startActivity(intent);
////    }
//    public void openFamilyList(View view) {
//        Intent intent = new Intent(this, FamilyActivity.class);
//        startActivity(intent);
//    }
//    public void openColorsList(View view) {
//        Intent intent = new Intent(this, ColorsActivity.class);
//        startActivity(intent);
//    }
//
//    public void openPhraseActivity(View view) {
//        Intent intent = new Intent(this, PhraseActivity.class);
//        startActivity(intent);
//    }
}

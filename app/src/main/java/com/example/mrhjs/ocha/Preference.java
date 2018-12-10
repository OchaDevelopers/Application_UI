package com.example.mrhjs.ocha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class Preference extends AppCompatActivity {
    ArrayList<String> color = new ArrayList<>();
    ArrayList<String> texture = new ArrayList<>();
    ArrayList<String> pattern = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        Toolbar ptoolbar = (Toolbar) findViewById(R.id.preference_toolbar);
        setSupportActionBar(ptoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        super.setTitle("Preference");


        final ToggleButton color1 = (ToggleButton) this.findViewById(R.id.color1);
        final ToggleButton color2 = (ToggleButton) this.findViewById(R.id.color2);
        final  ToggleButton color3 = (ToggleButton) this.findViewById(R.id.color3);
        final ToggleButton color4 = (ToggleButton) this.findViewById(R.id.color4);
        final ToggleButton color5 = (ToggleButton) this.findViewById(R.id.color5);
        final ToggleButton color6 = (ToggleButton) this.findViewById(R.id.color6);
        final ToggleButton color7 = (ToggleButton) this.findViewById(R.id.color7);
        final ToggleButton color8 = (ToggleButton) this.findViewById(R.id.color8);
        final ToggleButton color9 = (ToggleButton) this.findViewById(R.id.color9);
        final ToggleButton color10 = (ToggleButton) this.findViewById(R.id.color10);
        final ToggleButton texture1 = (ToggleButton) this.findViewById(R.id.texture1);
        final ToggleButton texture2 = (ToggleButton) this.findViewById(R.id.texture2);
        final ToggleButton texture3 = (ToggleButton) this.findViewById(R.id.texture3);
        final ToggleButton texture4 = (ToggleButton) this.findViewById(R.id.texture4);
        final ToggleButton texture5 = (ToggleButton) this.findViewById(R.id.texture5);
        final ToggleButton texture6 = (ToggleButton) this.findViewById(R.id.texture6);
        final ToggleButton texture7 = (ToggleButton) this.findViewById(R.id.texture7);
        final ToggleButton texture8 = (ToggleButton) this.findViewById(R.id.texture8);
        final ToggleButton pattern1 = (ToggleButton) this.findViewById(R.id.pattern1);
        final ToggleButton pattern2 = (ToggleButton) this.findViewById(R.id.pattern2);
        final ToggleButton pattern3 = (ToggleButton) this.findViewById(R.id.pattern3);
        final ToggleButton pattern4 = (ToggleButton) this.findViewById(R.id.pattern4);
        final ToggleButton pattern5 = (ToggleButton) this.findViewById(R.id.pattern5);
        final ToggleButton pattern6 = (ToggleButton) this.findViewById(R.id.pattern6);
        final ToggleButton pattern7 = (ToggleButton) this.findViewById(R.id.pattern7);
        final ToggleButton pattern8 = (ToggleButton) this.findViewById(R.id.pattern8);

        Button apply = (Button) this.findViewById(R.id.apply);
        Button cancel = (Button) this.findViewById(R.id.cancel);
        color.add(" ");
        pattern.add(" ");
        texture.add(" ");
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Preference.this, Menu.class);
                intent.putExtra("color", color);
                intent.putExtra("texture", texture);
                intent.putExtra("pattern", pattern);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Preference.this, Menu.class);
                startActivity(intent);
            }
        });


        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color1.isChecked()) {
                    color1.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor1));
                    color.add("babyblue");
                } else {
                    color1.setBackgroundDrawable(getResources().getDrawable(R.drawable.color1));
                    color.remove("babyblue");
                }
            }
        });
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color2.isChecked()) {
                    color2.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor2));
                    color.add("red");
                } else {
                    color2.setBackgroundDrawable(getResources().getDrawable(R.drawable.color2));
                    color.remove("red");
                }
            }
        });
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color3.isChecked()) {
                    color3.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor3));
                    color.add("yellow");
                } else {
                    color3.setBackgroundDrawable(getResources().getDrawable(R.drawable.color3));
                    color.remove("yellow");
                }
            }
        });
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color4.isChecked()) {
                    color4.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor4));
                    color.add("purple");
                } else {
                    color4.setBackgroundDrawable(getResources().getDrawable(R.drawable.color4));
                    color.remove("purple");
                }
            }
        });
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color5.isChecked()) {
                    color5.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor5));
                    color.add("orange");
                } else {
                    color5.setBackgroundDrawable(getResources().getDrawable(R.drawable.color5));
                    color.remove("orange");
                }
            }
        });
        color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color6.isChecked()) {
                    color6.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor6));
                    color.add("black");
                } else {
                    color6.setBackgroundDrawable(getResources().getDrawable(R.drawable.color6));
                    color.remove("black");
                }
            }
        });
        color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color7.isChecked()) {
                    color7.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor7));
                    color.add("green");
                } else {
                    color7.setBackgroundDrawable(getResources().getDrawable(R.drawable.color7));
                    color.remove("green");
                }
            }
        });
        color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color8.isChecked()) {
                    color8.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor8));
                    color.add("yellow");
                } else {
                    color8.setBackgroundDrawable(getResources().getDrawable(R.drawable.color8));
                    color.remove("yellow");
                }
            }
        });
        color9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color9.isChecked()) {
                    color9.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor9));
                    color.add("yellow");
                } else {
                    color9.setBackgroundDrawable(getResources().getDrawable(R.drawable.color9));
                    color.remove("yellow");
                }
            }
        });
        color10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (color10.isChecked()) {
                    color10.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickcolor10));
                    color.add("blue");
                } else {
                    color10.setBackgroundDrawable(getResources().getDrawable(R.drawable.color10));
                    color.remove("blue");
                }
            }
        });

        texture1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture1.isChecked()) {
                    texture1.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture1));
                    texture.add("cotton tshirt");
                } else {
                    texture1.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture1));
                    texture.remove("cotton tshirt");
                }
            }
        });
        texture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture2.isChecked()) {
                    texture2.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture2));
                    texture.add("knit");
                } else {
                    texture2.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture2));
                    texture.remove("knit");
                }
            }
        });
        texture3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture3.isChecked()) {
                    texture3.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture3));
                    texture.add("denim");
                } else {
                    texture3.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture3));
                    texture.remove("denim");
                }
            }
        });
        texture4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture4.isChecked()) {
                    texture4.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture4));
                    texture.add("linen");
                } else {
                    texture4.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture4));
                    texture.remove("linen");
                }
            }
        });
        texture5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture5.isChecked()) {
                    texture5.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture5));
                    texture.add("wool");
                } else {
                    texture5.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture5));
                    texture.remove("wool");
                }
            }
        });
        texture6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture6.isChecked()) {
                    texture6.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture6));
                    texture.add("silk");
                } else {
                    texture6.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture6));
                    texture.remove("silk");
                }
            }
        });
        texture7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture7.isChecked()) {
                    texture7.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture7));
                    texture.add("polyester");
                } else {
                    texture7.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture7));
                    texture.remove("polyester");
                }
            }
        });
        texture8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texture8.isChecked()) {
                    texture8.setBackgroundDrawable(getResources().getDrawable(R.drawable.clicktexture8));
                    texture.add("goretex");
                } else {
                    texture8.setBackgroundDrawable(getResources().getDrawable(R.drawable.texture8));
                    texture.remove("goretex");
                }
            }
        });

        pattern1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern1.isChecked()) {
                    pattern1.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern1));
                    pattern.add("stripe");
                } else {
                    pattern1.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern1));
                    pattern.remove("stripe");
                }
            }
        });
        pattern2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern2.isChecked()) {
                    pattern2.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern2));
                    pattern.add("check");
                } else {
                    pattern2.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern2));
                    pattern.remove("check");
                }
            }
        });
        pattern3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern3.isChecked()) {
                    pattern3.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern3));
                    pattern.add(" ");
                } else {
                    pattern3.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern3));
                    pattern.remove(" ");
                }
            }
        });
        pattern4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern4.isChecked()) {
                    pattern4.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern4));
                    pattern.add("logo");
                } else {
                    pattern4.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern4));
                    pattern.remove("logo");
                }
            }
        });
        pattern5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern5.isChecked()) {
                    pattern5.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern5));
                    pattern.add("flower");
                } else {
                    pattern5.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern5));
                    pattern.remove("flower");
                }
            }
        });
        pattern6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern6.isChecked()) {
                    pattern6.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern6));
                    pattern.add("sukajan");
                } else {
                    pattern6.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern6));
                    pattern.remove("sukajan");
                }
            }
        });
        pattern7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern7.isChecked()) {
                    pattern7.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern7));
                    pattern.add("dot");
                } else {
                    pattern7.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern7));
                    pattern.remove("dot");
                }
            }
        });
        pattern8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pattern8.isChecked()) {
                    pattern8.setBackgroundDrawable(getResources().getDrawable(R.drawable.clickpattern8));
                    pattern.add("Argyle");
                } else {
                    pattern8.setBackgroundDrawable(getResources().getDrawable(R.drawable.pattern8));
                    pattern.remove("Argyle");
                }
            }
        });

    }
}

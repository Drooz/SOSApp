package com.example.drooz.sos;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main4Activity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText ex ;
    TextView stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
        ex = (EditText) findViewById(R.id.number);
        stat = (TextView) findViewById(R.id.stat);
    }

    public boolean saveData(View view) {
        //pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String num = ex.getText().toString();
        if (pref.contains(num)){
            stat.setText("Number Already Exist");
            return false ;
        }else {
        editor.putString(num,num);
        stat.setText("Number Added");
        editor.commit();
        return true;}
    }
}

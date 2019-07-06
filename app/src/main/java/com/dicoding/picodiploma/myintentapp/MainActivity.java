package com.dicoding.picodiploma.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnMoveActivity;
    Button btnWithDataActivity;
    Button btnMoveWithObject;
    Button btnDialNumber;
    Button btnMoveForResult;
    TextView tvResult;

    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveActivity = findViewById(R.id.btn_movi_activity);
        btnMoveActivity.setOnClickListener(this);

        btnWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnWithDataActivity.setOnClickListener(this);

        btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        btnDialNumber = findViewById(R.id.btn_move_dial_number);
        btnDialNumber.setOnClickListener(this);

        btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_movi_activity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_move_activity_data:
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Dicoding Academy Boy");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 19);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btn_move_activity_object:
                Person person = new Person();
                person.setAge(18);
                person.setName("Fiki Firmansyah");
                person.setEmail("fikifrsyh@gmail.com");
                person.setCity("Bandung");
                Intent moveWithObject = new Intent(MainActivity.this, MoveWithObjectActivity.class);
                moveWithObject.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
                startActivity(moveWithObject);
                break;
            case R.id.btn_move_dial_number:
                String phoneNumber = "085846087071";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;
            case R.id.btn_move_for_result:
                Intent moveForResultIntent = new Intent(MainActivity.this, MoveResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == MoveResultActivity.RESUL_CODE){
                int selectedValue = data.getIntExtra(MoveResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }
    }
}

package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity { //pengaturan halaman permainan//
    String[] question_list = {"Apa yang dimaksud dengan singkatan \"CPU\" dalam komputer?",
            "Manakah dari berikut yang merupakan jenis-jenis perangkat penyimpanan data dalam komputer?","Protokol yang digunakan untuk mengirim dan menerima surat elektronik adalah:"
            ,"Bahasa pemrograman yang biasa digunakan untuk pengembangan aplikasi web adalah?",
           "Manakah dari berikut ini bukan jenis bahasa pemrograman?" //pengaturan pertanyaan pada aplikasi//
    };
    String[] choose_list = {"Central Processing Unit"," Computer Processing Unit","Central Power Unit","Computer Power Unit",
            "RAM"," ROM"," HDD","semua benar",
            " HTTP","FTP","SMTP","TCP/IP",
            "java","python","PHP","swift",
            "HTML","CSS","SQL","PNG" // pengaturan jawaban di setiap pertanyaan//
    };
    String[] correct_list = {"Central Processing Unit","semua benar","SMTP","PHP","PNG"};
//peraturan jawaban //

    TextView cpt_question , text_question; //menampilkan teks pertanyaan//
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;


    int currentQuestion =  0  ; //jumlah pertanyaan benar
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()
        );
        remplirData();
        btn_next.setOnClickListener(
                view -> {
                        if (isclickBtn){
                            isclickBtn = false;

                            if(!valueChoose.equals(correct_list[currentQuestion])){
                                Toast.makeText(playActivity.this , "salah",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_erreur); //setting peringatan apabila jawaban yang dipilih salah

                            }else {
                                Toast.makeText(playActivity.this , "benar",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_correct); //setting peringatan apabila jawaban benar

                                scorePlayer++;
                            }
                            new Handler().postDelayed(() -> {
                                if(currentQuestion!=question_list.length-1){
                                    currentQuestion = currentQuestion + 1;
                                    remplirData();
                                    valueChoose = "";
                                    btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);

                                }else {
                                    Intent intent  = new Intent(playActivity.this , ResulteActivity.class);
                                    intent.putExtra("mulai ulang" , scorePlayer);
                                    startActivity(intent);
                                    finish(); //pengarutan tombol muali ulang utuk permainana
                                }

                            },2000);

                        }else {
                            Toast.makeText(playActivity.this ,  "....",Toast.LENGTH_LONG).show();
                        }
                }
        );


    }

    void remplirData(){
        cpt_question.setText((currentQuestion+1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion+1]);
        btn_choose3.setText(choose_list[4 * currentQuestion+2]);
        btn_choose4.setText(choose_list[4 * currentQuestion+3]);

    }

    public void ClickChoose(View view) {
        btn_click = (Button)view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }
    void chooseBtn(){

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}
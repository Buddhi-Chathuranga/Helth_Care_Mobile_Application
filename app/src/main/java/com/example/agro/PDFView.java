package com.example.agro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;

public class PDFView extends AppCompatActivity {

    private ImageView srp_back;
    private com.github.barteksc.pdfviewer.PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        pdfView=findViewById(R.id.pdfView);

        srp_back=findViewById(R.id.srp_back);
        srp_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        try {
            InputStream is = getAssets().open("stress.pdf");
            pdfView.fromStream(is).enableSwipe(true).load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
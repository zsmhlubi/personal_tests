package com.example.wits_academy;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class upload_file extends AppCompatActivity {


    private final int REQ = 1;
    private Uri pdfdata;
    String pdfName;
    TextView pdfTextView;
    EditText pdfTitle;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_file);
        pdfTextView = findViewById(R.id.textView);
        pdfTitle = findViewById(R.id.pdfTitle);
    }

    public void upload(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("pdf/mp4");
        intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select pdf"),REQ);


    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK ){
            pdfdata = data.getData();
            if (pdfdata.toString().startsWith("content://")){
                Cursor cursor = null;
                try {
                    cursor = upload_file.this.getContentResolver().query(pdfdata,null, null,null,null,null);
                    if (cursor != null && cursor.moveToFirst()){
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if (pdfdata.toString().startsWith("file://")){
                pdfName = new File(pdfdata.toString()).getName();
            }
            pdfTextView.setText(pdfName);
        }
    }

    public void upload_file(View view) {
        String title = pdfTitle.getText().toString();
        if (title.isEmpty()){
            pdfTitle.setError("Empty");
            pdfTitle.requestFocus();
        }
        else if (pdfdata == null){

        }
        else{

        }
    }

    private void uploadPdf() {

    }
}
package com.createhtml.pro.createhtml;



import android.graphics.pdf.PdfDocument;
import android.print.PrintAttributes;

import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText txt;
    private WebView showweb;
    private TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnHTML);
        txt = findViewById(R.id.txtHTML);
        showweb = findViewById(R.id.webView);
        textview = findViewById(R.id.viewHTML);
        final File file = new File(getBaseContext().getExternalCacheDir().getPath() + "/" + "HTML1" + ".pdf");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview.setText(Html.fromHtml(txt.getText().toString()));// bikin HTML
                showweb.loadData(textview.toString(), "text/html", "UTF-8");
                PrintedPdfDocument document = new PrintedPdfDocument(MainActivity.this, getPrintAttributes());


                PdfDocument.Page page = document.startPage(1);


                View content = textview; //printPDF
                content.draw(page.getCanvas());


                document.finishPage(page);


                try {
                    file.createNewFile();
                    FileOutputStream outputstream = new FileOutputStream(file);
                    document.writeTo(outputstream);
                    Toast.makeText(MainActivity.this, "HTML Created Successfully!",
                            Toast.LENGTH_LONG).show();
                    outputstream.flush();
                    outputstream.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }


                document.close();
            }
        });
    }


    private PrintAttributes getPrintAttributes() {
        PrintAttributes.Builder builder = new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(new PrintAttributes.Resolution("res1", "Resolution", 50, 50)).setMinMargins(new PrintAttributes.Margins(5, 5, 5, 5));
        PrintAttributes printAttributes = builder.build();
        return printAttributes;
    }
}






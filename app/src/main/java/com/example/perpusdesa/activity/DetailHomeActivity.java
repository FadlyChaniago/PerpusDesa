package com.example.perpusdesa.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.perpusdesa.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;

public class DetailHomeActivity extends AppCompatActivity {

    private ImageView imageView;

    private static final String PDF_VIEWER_URL = "https://docs.google.com/gview?embedded=true&url=" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);

        Intent intent = getIntent();
        String pdfUrl = intent.getStringExtra("pdf");


        if (pdfUrl != null) {
            // Option 1: Using PDFView
            PDFView pdfView = findViewById(R.id.pdfView);
            pdfView.fromUri(Uri.parse(pdfUrl))
                    .onError(new OnErrorListener() {
                        @Override
                        public void onError(Throwable t) {
                            // Handle error loading PDF
                        }
                    })
                    .load();

            // Option 2: Using WebView
            WebView webView = findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(PDF_VIEWER_URL + pdfUrl);
        }
    }
}

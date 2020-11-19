package com.example.browserpractice

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usrWebView : WebView = findViewById(R.id.webView)
        //enabling javascript on webView.
        usrWebView.settings.javaScriptEnabled = true
        usrWebView.webViewClient = WebViewClient()

        usrWebView.loadUrl("https://google.com")

        //URL text field handling.
        val editText = findViewById<EditText>(R.id.urlEditText)
        editText.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                usrWebView.loadUrl(editText.text.toString())
                true
            } else {
                false
            }
        }
    }

    override fun onBackPressed() {
        val usrWebView : WebView = findViewById(R.id.webView)
        if (usrWebView.canGoBack()) {
            usrWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.action_google, R.id.action_home -> {
                val usrWebView : WebView = findViewById(R.id.webView)
                usrWebView.loadUrl("https://google.com")
                return true
            }
            R.id.action_naver -> {
                val usrWebView : WebView = findViewById(R.id.webView)
                usrWebView.loadUrl("https://naver.com")
                return true
            }
            R.id.action_daum -> {
                val usrWebView : WebView = findViewById(R.id.webView)
                usrWebView.loadUrl("https://daum.net")
            }
            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-000-0000")
                if(intent.resolveActivity(packageManager) != null) {
                    Toast.makeText(this@MainActivity, "has",Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
                Toast.makeText(this@MainActivity, "hasn't", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_message -> {
                Toast.makeText(this@MainActivity, "Message", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_email -> {
                Toast.makeText(this@MainActivity, "Email", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
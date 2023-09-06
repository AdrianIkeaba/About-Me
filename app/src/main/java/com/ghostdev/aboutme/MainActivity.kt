package com.ghostdev.aboutme

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.ghostdev.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val url = "https://github.com/AdrianIkeaba"
    private val webViewClient = MyWebViewClient()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        // Set up WebView settings and WebViewClient
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        binding.webView.webViewClient = webViewClient

        // Set up click listener for the image
        binding.gitHubImage.setOnClickListener {
            binding.webView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.VISIBLE

            // Load the URL
            binding.webView.loadUrl(url)
        }
    }

    override fun onBackPressed() {
        if (binding.webView.isVisible) {
            binding.webView.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            binding.progressBar.visibility = View.GONE
        }
    }
}

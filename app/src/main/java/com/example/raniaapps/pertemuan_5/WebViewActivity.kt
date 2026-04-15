package com.example.raniaapps.pertemuan_5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.raniaapps.R
import com.example.raniaapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ TOOLBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // ✅ WEBVIEW
        // 🔥 IMPROVISASI WEBVIEW (LETakkan DI SINI)

        binding.webView.alpha = 0f

        binding.webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                super.onPageFinished(view, url)

                binding.webView.animate()
                    .alpha(1f)
                    .setDuration(500)
                    .start()
            }
        }

// tetap aktifkan JS
        binding.webView.settings.javaScriptEnabled = true

// 🔥 ENABLE ZOOM
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.displayZoomControls = false

// 🔥 OVER SCROLL
        binding.webView.overScrollMode = android.view.View.OVER_SCROLL_ALWAYS

// load terakhir
        binding.webView.loadUrl("https://merdeka.com")

        binding.fabScrollTop.setOnClickListener {
            binding.webView.scrollTo(0, 0)
        }

        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->

            // 🔥 TAMBAHAN (letakkan paling atas)
            val fab = binding.fabScrollTop

            if (scrollY > oldScrollY) {
                // ⬇️ Scroll ke bawah (hide toolbar)
                binding.appBar.animate()
                    .translationY(-binding.appBar.height.toFloat())
                    .setDuration(300)
                    .start()

                binding.appBar.setExpanded(false, true)

                // 🔥 TAMBAHAN
                fab.hide()

            } else if (scrollY < oldScrollY) {
                // ⬆️ Scroll ke atas (show toolbar)
                binding.appBar.animate()
                    .translationY(0f)
                    .setDuration(300)
                    .start()

                binding.appBar.setExpanded(true, true)

                // 🔥 TAMBAHAN
                if (scrollY > 300) {
                    fab.show()
                }
            }

            // 🔥 FADE EFFECT TOOLBAR
            val alpha = if (scrollY > 200) 0.9f else 1f
            binding.toolbar.alpha = alpha

            // 🔥 ELEVATION (SHADOW EFFECT)
            if (scrollY > 50) {
                binding.appBar.elevation = 12f
            } else {
                binding.appBar.elevation = 0f
            }
        }

        // ✅ BACK BUTTON (VERSI MODERN)
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        finish()
                    }
                }
            }
        )
    }

    // ✅ BACK TOOLBAR (←)
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
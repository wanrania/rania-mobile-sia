package com.example.raniaapps.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.view.Gravity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.raniaapps.R

class FifthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fifth)

        // =========================
        // TOOLBAR SETUP
        // =========================
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 🔥 TOOLBAR STYLE IMPROVEMENT
        toolbar.apply {
            setBackgroundResource(R.drawable.toolbar_gradient)
            elevation = 12f

            // animasi fade in
            alpha = 0f
            animate()
                .alpha(1f)
                .setDuration(800)
                .start()
        }

        // 🔥 CENTER TITLE TOOLBAR
        for (i in 0 until toolbar.childCount) {
            val view = toolbar.getChildAt(i)
            if (view is TextView) {
                view.gravity = Gravity.CENTER
                view.textAlignment = android.view.View.TEXT_ALIGNMENT_CENTER
            }
        }

        // 🔥 ACTION BAR SETTING
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // =========================
        // BUTTON WEBVIEW
        // =========================
        val btnWebView = findViewById<Button>(R.id.btnWebView)
        btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // =========================
        // WINDOW INSETS
        // =========================
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // =========================
    // OPTION MENU
    // =========================
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // =========================
    // MENU + IMPROVISASI INTERAKTIF
    // =========================
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // 🔥 small animation feedback
        val rootView = findViewById<android.view.View>(R.id.main)
        rootView.animate()
            .alpha(0.95f)
            .setDuration(100)
            .withEndAction {
                rootView.animate().alpha(1f).setDuration(100).start()
            }

        return when (item.itemId) {

            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            R.id.action_search -> {
                android.widget.Toast.makeText(
                    this,
                    "🔍 Searching data...",
                    android.widget.Toast.LENGTH_SHORT
                ).show()

                // update subtitle UI
                supportActionBar?.subtitle = "Mode: Searching 🔍"
                true
            }

            R.id.action_settings -> {
                android.widget.Toast.makeText(
                    this,
                    "⚙️ Open Settings",
                    android.widget.Toast.LENGTH_SHORT
                ).show()

                // update subtitle UI
                supportActionBar?.subtitle = "Mode: Settings ⚙️"
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
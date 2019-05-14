package com.example.hrlite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    var signBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signBtn = findViewById(R.id.signBtn)
        signBtn!!.setOnClickListener{
            var intent = Intent(this,PersonlistActivity::class.java)
            startActivity(intent)
        }
    }
}

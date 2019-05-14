package com.example.hrlite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.Window
import android.view.WindowManager
import android.widget.Button

class PayActivity : AppCompatActivity() {

    var actionbar: ActionBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)
        setContentView(R.layout.activity_pay)
        //actionbar
        actionbar = supportActionBar
        actionbar!!.title = "จัดการข้อมูลการจ่าย"
        var confirm = findViewById<Button>(R.id.confirm)
        confirm.setOnClickListener {
            var intent = Intent(this,PersonlistActivity::class.java)
            startActivity(intent)
        }
        var cancel3 = findViewById<Button>(R.id.cancel3)
        cancel3.setOnClickListener {
            var intent = Intent(this,PersonlistActivity::class.java)
            startActivity(intent)
        }
    }
}

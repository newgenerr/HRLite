package com.example.hrlite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Button
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_revenue.*
import org.json.JSONArray

class ExpenditureActivity : AppCompatActivity() {

    val TAG = "HR_SERVICE"
    var requestQueue: RequestQueue? = null
    var expenditureList = ArrayList<Expenditure>()
    var next2:Button? = null
    var actionbar: ActionBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenditure)

        var intent = getIntent()
        var slId = intent.getStringExtra("sl_id")
        //actionbar
        actionbar = supportActionBar
        actionbar!!.title = "จัดการรายจ่าย"
        next2 = findViewById(R.id.next2)
        next2!!.setOnClickListener {
            var intent = Intent(this,PayActivity::class.java)
            intent.putExtra("sl_id",slId)
            startActivity(intent)
        }
        var cancel2 = findViewById<Button>(R.id.cancel2)
        cancel2.setOnClickListener {
            var intent = Intent(this,PersonlistActivity::class.java)
            startActivity(intent)
        }

        val cache = DiskBasedCache(cacheDir, 1024 * 1024)
        val network = BasicNetwork(HurlStack())
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }
        val url = "http://10.80.39.17/TSP59/School/index.php/hr/salary/Mobile_service/get_expenditure_detail/"+slId
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val json = JSONArray(response)
                Log.d("JSON",json.toString())
                (0 until json.length()).mapTo(expenditureList) {
                    Expenditure(json.getJSONObject(it).getString("ex_id"),
                        json.getJSONObject(it).getString("ex_type"),
                        json.getJSONObject(it).getString("sld_value"))
                }
                income_list.layoutManager = LinearLayoutManager(this)
                income_list.adapter = ExpenditureAdapter(this, expenditureList)
            },
            Response.ErrorListener { response -> Log.d("test",response.toString()) }
        )
        stringRequest.tag = TAG
        requestQueue?.add(stringRequest)
    }

    override fun onStop(){
        super.onStop()
        requestQueue?.cancelAll(TAG)
    }
}

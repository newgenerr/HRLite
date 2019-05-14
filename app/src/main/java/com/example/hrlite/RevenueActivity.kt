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
import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_revenue.*
import org.json.JSONArray

class RevenueActivity : AppCompatActivity() {

    val TAG = "HR_SERVICE"
    var requestQueue: RequestQueue? = null
    var revenueList = ArrayList<Revenue>()
    var next1: Button? = null
    var actionbar: ActionBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revenue)
        var intent = getIntent()
        var slId = intent.getStringExtra("sl_id")
        next1 = findViewById<Button>(R.id.next1)
        //actionbar
        actionbar = supportActionBar
        actionbar!!.title = "จัดการรายรับ"

        next1!!.setOnClickListener {
            var intent = Intent(this,ExpenditureActivity::class.java)
            intent.putExtra("sl_id",slId)
            startActivity(intent)
        }
        var cancel1 = findViewById<Button>(R.id.cancel1)
        cancel1.setOnClickListener {
            finish()
        }

        val cache = DiskBasedCache(cacheDir, 1024 * 1024)
        val network = BasicNetwork(HurlStack())
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }
        val url = "http://10.80.39.17/TSP59/School/index.php/hr/salary/Mobile_service/get_revenue_detail/"+slId
//
//        val params = HashMap<String,String>()
//        params["sdId"] = slId
//        val jsonObject = JSONObject(params)

//        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
//            Response.Listener { response ->
//                // Process the json
//                try {
//                    Log.d("test","Response: $response")
//                }catch (e:Exception){
//                    Log.d("test","Exception: $e")
//                }
//
//            }, Response.ErrorListener{
//                // Error in request
//                Log.d("test","Volley error: $it")
//            })
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val json = JSONArray(response)
                Log.d("JSON",json.toString())
                (0 until json.length()).mapTo(revenueList) {
                    Revenue(json.getJSONObject(it).getString("re_id"),
                        json.getJSONObject(it).getString("re_type"),
                        json.getJSONObject(it).getString("sld_value"))
                }
                income_list.layoutManager = LinearLayoutManager(this)
                income_list.adapter = RevenueAdapter(this, revenueList)
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

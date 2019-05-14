package com.example.hrlite

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_personlist.*
import kotlinx.android.synthetic.main.item_person.view.*
import org.json.JSONArray

class PersonlistActivity : AppCompatActivity() {

    val TAG = "HR_SERVICE"
    var requestQueue: RequestQueue? = null
    var PersonList = ArrayList<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personlist)
        val cache = DiskBasedCache(cacheDir, 1024 * 1024)
        val network = BasicNetwork(HurlStack())
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }
        val url = "http://10.80.39.17/TSP59/School/index.php/hr/salary/Mobile_service/get_all_person_salary"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val json = JSONArray(response)
                Log.d("JSON",json.toString())
                (0 until json.length()).mapTo(PersonList) {
                    Person(json.getJSONObject(it).getString("ps_id"),
                        json.getJSONObject(it).getString("sl_id"),
                        json.getJSONObject(it).getString("pf_name"),
                        json.getJSONObject(it).getString("ps_fname"),
                        json.getJSONObject(it).getString("ps_lname"))
                }
                personList.layoutManager = LinearLayoutManager(this)
                personList.adapter = RecycleAdapter(this, PersonList)
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

class RecycleAdapter (private val context: Context, private val items : ArrayList<Person>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var mView = LayoutInflater
            .from(context)
            .inflate(R.layout.item_person, parent,false)
        var mViewHolder = ViewHolder(mView)

        mView.setOnClickListener {
            val itemList = items
            var selectItem = itemList[mViewHolder.layoutPosition]
            Toast.makeText(context,selectItem.sl_id,Toast.LENGTH_SHORT).show()
            var intent_me =  Intent(context,RevenueActivity::class.java)
            intent_me.putExtra("title",selectItem.sl_id)
//            intent_me.putExtra("author",selectItem.title_description)
//            intent_me.putExtra("imageLink",selectItem.imageUrl)
//            intent_me.putExtra("year",selectItem.price)
            context.startActivity(intent_me)
        }

        return mViewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(items[position])
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(person: Person){
        itemView.personName.text = person.pf_name+""+person.ps_fname+" "+person.ps_lname
        itemView.slId.text = person.sl_id
    }
}

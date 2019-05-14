package com.example.hrlite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_income.view.*

class RevenueAdapter (private val context: Context, private val items : ArrayList<Revenue>) : RecyclerView.Adapter<RevenueViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevenueViewHolder {
        var mView = LayoutInflater
            .from(context)
            .inflate(R.layout.item_income, parent,false)
        var mViewHolder = RevenueViewHolder(mView)

//        mView.setOnClickListener {
//            val itemList = items
//            var selectItem = itemList[mViewHolder.layoutPosition]
//            Toast.makeText(context,selectItem.sl_id, Toast.LENGTH_SHORT).show()
//            var intent_me =  Intent(context,RevenueActivity::class.java)
//            intent_me.putExtra("title",selectItem.sl_id)
//            intent_me.putExtra("author",selectItem.title_description)
//            intent_me.putExtra("imageLink",selectItem.imageUrl)
//            intent_me.putExtra("year",selectItem.price)
//            context.startActivity(intent_me)
//        }

        return mViewHolder

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: RevenueViewHolder, position: Int) {
        viewHolder.bindView(items[position])
    }

}

class RevenueViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(revenue: Revenue){
           itemView.type.text = revenue.re_type
           itemView.value.text = revenue.re_value
    }
}
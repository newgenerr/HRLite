package com.example.hrlite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_income.view.*

class ExpenditureAdapter (private val context: Context, private val items : ArrayList<Expenditure>) : RecyclerView.Adapter<ExpenditureViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenditureViewHolder {
        var mView = LayoutInflater
            .from(context)
            .inflate(R.layout.item_income, parent,false)
        var mViewHolder = ExpenditureViewHolder(mView)

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

    override fun onBindViewHolder(viewHolder: ExpenditureViewHolder, position: Int) {
        viewHolder.bindView(items[position])
    }

}

class ExpenditureViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(expenditure: Expenditure){
        itemView.type.text = expenditure.ex_type
        itemView.value.text = expenditure.ex_value
    }
}
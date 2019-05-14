package com.example.hrlite

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_person.view.*

class PersonAdapter (private val context: Context, private val items : ArrayList<Person>) : RecyclerView.Adapter<PersonViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        var mView = LayoutInflater
            .from(context)
            .inflate(R.layout.item_person, parent,false)
        var mViewHolder = PersonViewHolder(mView)

        mView.setOnClickListener {
            val itemList = items
            var selectItem = itemList[mViewHolder.layoutPosition]
            Toast.makeText(context,selectItem.sl_id, Toast.LENGTH_SHORT).show()
            var intent_me =  Intent(context,RevenueActivity::class.java)
            intent_me.putExtra("sl_id",selectItem.sl_id)
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

    override fun onBindViewHolder(viewHolder: PersonViewHolder, position: Int) {
        viewHolder.bindView(items[position])
    }

}

class PersonViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(person: Person){
        itemView.personName.text = person.pf_name+""+person.ps_fname+" "+person.ps_lname
        itemView.slId.text = person.sl_id
        Picasso.get().load("https://www.img.in.th/images/6e08355d1220d96a4fec1f90288fff5c.png")
            .placeholder(R.mipmap.ic_launcher)
            .into(itemView.status_icon);
    }
}
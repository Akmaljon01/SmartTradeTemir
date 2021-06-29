package com.example.smarttrade.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.example.smarttrade.R
import com.example.smarttrade.asosiyOyna.AosiyActivity
import com.example.smarttrade.dialog.OpenDialog
import com.example.smarttrade.korzinka.KorzinaActivity
import com.example.smarttrade.loginOyna.MainActivity
import com.example.smarttrade.models.Mahsulot
import com.example.smarttrade.models.SotTovar
import com.example.smarttrade.utils.PreUtils
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_korzina.*
import kotlinx.android.synthetic.main.cardkorzina.view.*
import kotlinx.android.synthetic.main.cardmahsulot.view.*


class KorzinkaAdapter (private var items:ArrayList<SotTovar>):RecyclerView.Adapter<KorzinkaAdapter.ItemHolder>() {

    class ItemHolder(view : View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardkorzina,parent,false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.itemView.tovarnomicard.text=items[position].tovarnomi
        holder.itemView.tovarnarxicard.text="narx:\n"+items[position].narx.toString()
        holder.itemView.summatovarcard.text="summa:\n"+items[position].summa.toString()
        holder.itemView.tovarmiqdor.text=items[position].miqdor.toString()
        Glide.with(holder.itemView.rasmcard).load(items[position].rasm).into(holder.itemView.rasmcard)

//        holder.itemView.setOnClickListener {
//            var dialog = OpenDialog(items[position])
//            dialog.show((holder.itemView.context as AosiyActivity).supportFragmentManager,"dssa")
//        }


            holder.itemView.removecard.setOnClickListener {

                PreUtils.removeCardElement(items[position])
                items=Hawk.get(PreUtils.PREF_CARD)
                this.notifyDataSetChanged()
                var summa : Double=0.0
                var summadollar:Double=0.0
                for (d in items){
                    if (d.valyuta.equals("Сўм"))
                        summa += d.summa
                    else if (d.valyuta.equals("$"))
                        summadollar+=d.summa
                }
               KorzinaActivity.changesum(summa,summadollar)

            }
    }





}
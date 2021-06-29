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
import com.example.smarttrade.loginOyna.MainActivity
import com.example.smarttrade.models.Mahsulot
import kotlinx.android.synthetic.main.cardmahsulot.view.*


class MahsulotAdapter (private var items:ArrayList<Mahsulot>):RecyclerView.Adapter<MahsulotAdapter.ItemHolder>() {

    class ItemHolder(view : View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardmahsulot,parent,false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.itemView.mahnomi.text=items[position].nomi
        holder.itemView.mahnarxi.text=items[position].narxi.toString()
        Glide.with(holder.itemView.mahrasm).load(items[position].image).into(holder.itemView.mahrasm)

        holder.itemView.setOnClickListener {
            var dialog = OpenDialog(items[position])
            dialog.show((holder.itemView.context as AosiyActivity).supportFragmentManager,"dssa")
        }



    }



}
package com.example.smarttrade.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.smarttrade.R
import com.example.smarttrade.models.Mahsulot
import com.example.smarttrade.models.SotTovar
import com.example.smarttrade.utils.PreUtils
import kotlinx.android.synthetic.main.cardkorzina.view.*
import kotlinx.android.synthetic.main.opentovar.*
import kotlinx.android.synthetic.main.opentovar.view.*

class OpenDialog(var mahsulot : Mahsulot): DialogFragment() {
      var summa : Double = 0.0
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder=AlertDialog.Builder(requireActivity())
        var layoutInflater : LayoutInflater= activity?.layoutInflater!!
        var view : View=layoutInflater.inflate(R.layout.opentovar,null)


        view.tovarnomi.text=mahsulot.nomi
        view.tovarnarxi.text=mahsulot.narxi.toString()

        if (mahsulot.valyuta.equals("Сўм"))
        view.edtnarxiga.setHint(mahsulot.narxi.toString()+" Сўм")
        else if (mahsulot.valyuta.equals("$"))
            view.edtnarxiga.setHint(mahsulot.narxi.toString()+" Сўм")



        view.summasaqla.setOnClickListener {
                if (view.edtnarxiga.text.toString().isEmpty()){
                    view.edtnarxiga.setText("0.0")
                }
            if (view.edtnarxiga.text.toString().toDouble()<mahsulot.tannarx){
                Toast.makeText(requireActivity(),"tan narxdan arzon sotilyapti \n tannarx : ${mahsulot.tannarx}",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            var summa=view.summaxisobla.text.toString().substring(0,view.summaxisobla.text.toString().indexOf(" "))

            PreUtils.addcard(SotTovar(mahsulot.nomi,mahsulot.id,view.edtjamiga.text.toString().toDouble(),view.edtnarxiga.text.toString().toDouble(),
                    summa.toDouble(),mahsulot.image,mahsulot.valyuta))

           dialog?.cancel()
        }

       view.edtkaropkaga.afterTextChanged {

          var jami= xisobla(it.toString(),view.edtdonaga.text.toString(),view.edtnarxiga.text.toString())
           view.edtjamiga.setText(jami.toString())

           var summa=summaXisobla(it.toString(),view.edtdonaga.text.toString(),view.edtnarxiga.text.toString())
           if (mahsulot.valyuta.equals("Сўм"))
           view.summaxisobla.setText(summa.toString()+" Сўм")
           else if (mahsulot.valyuta.equals("Сўм"))
               view.summaxisobla.setText(summa.toString()+" $")




       }

        view.edtdonaga.afterTextChanged {
            var jami= xisobla(view.edtkaropkaga.text.toString(),it,view.edtnarxiga.text.toString())
            view.edtjamiga.setText(jami.toString())

            var summa=summaXisobla(view.edtkaropkaga.text.toString(),it,view.edtnarxiga.text.toString())

            if (mahsulot.valyuta.equals("Сўм"))
                view.summaxisobla.setText(summa.toString()+" Сўм")
            else if (mahsulot.valyuta.equals("Сўм"))
                view.summaxisobla.setText(summa.toString()+" $")


        }

        view.edtnarxiga.afterTextChanged {
            var summa=summaXisobla(view.edtkaropkaga.text.toString(),view.edtdonaga.text.toString(),view.edtnarxiga.text.toString())
            if (mahsulot.valyuta.equals("Сўм"))
                view.summaxisobla.setText(summa.toString()+" Сўм")
            else if (mahsulot.valyuta.equals("Сўм"))
                view.summaxisobla.setText(summa.toString()+" $")
        }




        builder.setView(view).setTitle(null)
        return builder.create()
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }


    fun xisobla(karobka : String,dona : String,narxi : String) : Double{
        var kar: Double=0.0;
        var don: Double=0.0;
        var narx: Double=0.0;

        if (karobka.isNotEmpty()){
            kar=karobka.toDouble()
        }
        if (dona.isNotEmpty()){
            don=dona.toDouble()
        }
        if (narxi.isNotEmpty()){
            narx=narxi.toDouble()
        }

        var jami : Double = kar*mahsulot.shtuk+don
        return jami
    }

    fun summaXisobla(karobka : String,dona : String,narxi : String): Double{
        var kar: Double=0.0;
        var don: Double=0.0;
        var narx: Double=0.0;

        if (karobka.isNotEmpty()){
            kar=karobka.toDouble()
        }
        if (dona.isNotEmpty()){
            don=dona.toDouble()
        }
        if (narxi.isNotEmpty()){
            narx=narxi.toDouble()
        }

        var jami : Double =( kar*mahsulot.shtuk+don)*narx
        return jami
    }
}
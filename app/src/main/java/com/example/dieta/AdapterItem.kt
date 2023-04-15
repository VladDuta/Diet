package com.example.dieta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_items.view.*

class AdapterItem(val userList: List<MyDataItem>, private val informationFragment: InformationFragment) : RecyclerView.Adapter<AdapterItem.ViewHolder>()  {

    private val checkedItems = mutableSetOf<Int>()

    var total: Int = 0

    private fun setCheckedState(position: Int, checked: Boolean) {
        if(checked)
        {
            checkedItems.add(position)
        }
        else checkedItems.remove(position)

        total = checkedItems.sumOf { index -> userList[index].foodItems.get(0).calories }
        informationFragment.onCalorieTotalChanged(total)
    }

  inner  class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var numeLista: TextView
        var caloriiLista: TextView = itemView.findViewById(R.id.caloriiLista)
        val checkBox = itemView.findViewById<CheckBox>(R.id.check_box)


        init{

            checkBox.setOnClickListener {

                setCheckedState(adapterPosition, checkBox.isChecked)
            }

            numeLista=itemView.numeLista
            caloriiLista=itemView.caloriiLista
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_items, parent, false)

        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.numeLista.text=userList[position].foodItems.get(0).foodName
        holder.caloriiLista.text=userList[position].foodItems.get(0).calories.toString()


    }

    override fun getItemCount(): Int {
        return userList.size
    }

}
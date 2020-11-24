package com.example.roomdemo.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.R
import com.example.roomdemo.model.User
import kotlinx.android.synthetic.main.single_item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var userList = emptyList<User>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.single_item, p0, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val currentItem = userList[p1]
        p0.itemView.txtFirstName.text =  currentItem.firstName
        p0.itemView.txtLastName.text = currentItem.lastName
        p0.itemView.txtAge.text = currentItem.age
        p0.itemView.txtId.text = currentItem.id.toString()

        p0.itemView.single_item_layout.setOnClickListener() {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            p0.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user : List<User>){
        this.userList = user
        notifyDataSetChanged()

    }
}
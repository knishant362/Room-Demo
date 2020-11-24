package com.example.roomdemo.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.R
import com.example.roomdemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //
        setHasOptionsMenu(true)

        //RecyclerVew

        val myAdapter = ListAdapter()
        val myRecyclerView = view.MyRecyclerView

        myRecyclerView.adapter = myAdapter

        myRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //mUserViewModel

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner , Observer {user ->
            myAdapter.setData(user)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_to_addFragment)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

       inflater.inflate(R.menu.user_menu , menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.action_delete){

            deleteAllData()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllData() {

        val binder = AlertDialog.Builder(requireContext())
        binder.setPositiveButton("Yes"){_ , _ -> clearDatabase() }
        binder.setNegativeButton("No"){ _ , _ -> }
        binder.setTitle("Clear Database")
        binder.setMessage("Do you want to clear the Database")
        binder.create().show()

    }

    private fun clearDatabase() {
        mUserViewModel.clearDatabase()
    }


}
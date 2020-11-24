package com.example.roomdemo.fragments.add

import android.app.Activity
import android.os.Bundle
import android.os.IBinder
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdemo.R
import com.example.roomdemo.model.User
import com.example.roomdemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener {

            AddData()

        }

        return view
    }

    private fun AddData() {

        val first_name = txtFirstName.text.toString()
        val last_name = txtLastName.text.toString()
        val age = txtAge.text.toString()

        if(!inputCheck(first_name, last_name, age)){

            // Create User Object
            val user = User(0 , first_name , last_name , age)

            //Add data to database
            mUserViewModel.addUser(user)
            closeKeyboard(view?.windowToken)
            Toast.makeText(requireContext() , "Successful", Toast.LENGTH_SHORT ).show()

            //Navigate back
            findNavController().navigate(R.id.action__to_listFragment)

        }else{
            Toast.makeText(requireContext() , "Please fill all fields", Toast.LENGTH_SHORT ).show()

        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {

        return (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))


    }

    private fun closeKeyboard(token : IBinder?){
        val imm = requireActivity()
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow( token, 0)

    }

}
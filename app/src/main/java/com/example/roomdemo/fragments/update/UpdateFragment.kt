package com.example.roomdemo.fragments.update

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.os.IBinder
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdemo.R
import com.example.roomdemo.model.User
import com.example.roomdemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        //Assigning values to Update Fragment fields
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.txtUpFirstName.setText(args.currentUser.firstName)
        view.txtUpLastName.setText(args.currentUser.lastName)
        view.txtUpAge.setText(args.currentUser.age)

        //Update button
        view.btnUpdate.setOnClickListener {
            updateData()
            findNavController().navigate(R.id.action_update_to_list)
        }

        return view

    }

    private fun updateData() {
        val firstname = txtUpFirstName.text.toString()
        val lastname = txtUpLastName.text.toString()
        val age = txtUpAge.text.toString()

        if(!inputCheck(firstname , lastname , age)){
            val upUser = User(args.currentUser.id , firstname , lastname , age)

            mUserViewModel.updateUser(upUser)

            closeKeyboard(view?.windowToken)
            findNavController().navigate(R.id.action_update_to_list)
            Toast.makeText(requireContext() , "Successful" , Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext() , "Please Fill all fields" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {

        return (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.user_menu , menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.action_delete) {
            deleteUser(args.currentUser)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(user: User) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->

            mUserViewModel.deleteUser(user)
            closeKeyboard(view?.windowToken)
            findNavController().navigate(R.id.action_update_to_list)
            Toast.makeText(requireContext() , "Delete Successful" , Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Do you want to delete ${args.currentUser.firstName}")
        builder.create().show()



    }

    private fun closeKeyboard(token : IBinder?){
        val imm = requireActivity()
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow( token, 0)

    }

}
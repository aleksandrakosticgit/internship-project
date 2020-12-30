package com.example.internshipproject.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.internshipproject.R
import com.example.internshipproject.model.Post
import com.example.internshipproject.model.User
import com.example.internshipproject.viewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.

 */


class PostDetailsFragment : Fragment(){


    private lateinit var userViewModel: UserViewModel
    private var listOfUsers = ArrayList<User>()
    private lateinit var actionBar: ActionBar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("TAG", "onCReateView called")

        actionBar = (activity as AppCompatActivity).supportActionBar!!
        actionBar.title = "Post Details"

        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity.let {
            userViewModel = ViewModelProvider(it!!).get(UserViewModel::class.java)
        }

        getUser()
    }


    private fun getClickedPost(): Post {
        var posts: Post? = null
        val bundle = arguments
        if (bundle != null) {
            posts = bundle.getParcelable(POST_TRANSFER)
        }

        return posts!!
    }

     private fun getUser() {
         var user: User?

        lifecycleScope.launch{
            withContext(Dispatchers.Main){
                listOfUsers = userViewModel.getUsersFromServer()

        val clickedPost = getClickedPost()
        for (u in listOfUsers.indices) {

            if (clickedPost.userId == listOfUsers[u].id) {


                   user = listOfUsers[u]

                detailsTitle_tv.text = "Title: " + clickedPost.title
                    detailsBody_tv.text = clickedPost.body
                    email_tv.text = "E-mail: " + user?.email
                    authorName_tv.text = "Author: " + user?.name

            }
        }
            }
        }

    }

}







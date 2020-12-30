package com.example.internshipproject.viewModels


import androidx.lifecycle.ViewModel
import com.example.internshipproject.model.User
import com.example.internshipproject.network.PostService
import com.example.internshipproject.network.ServiceBuilder


class UserViewModel : ViewModel() {


    private val listOfUsers = ArrayList<User>()
    private var user: User? = null



    suspend fun getUsersFromServer(): ArrayList<User>{

        val request = ServiceBuilder.buildService(PostService::class.java)


            val response = request.getUsers()

            if (response.isSuccessful) {

                val userResponse = response.body()

                for (u in userResponse!!.indices){
                    user = User(
                        userResponse[u].email,
                        userResponse[u].id,
                        userResponse[u].name
                    )
                     listOfUsers.add(user!!)
                }
            }

        return listOfUsers
    }
}
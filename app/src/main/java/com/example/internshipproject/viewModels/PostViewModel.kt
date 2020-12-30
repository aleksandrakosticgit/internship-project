package com.example.internshipproject.viewModels


import android.app.Application
import androidx.lifecycle.*
import com.example.internshipproject.model.Post
import com.example.internshipproject.network.PostService
import com.example.internshipproject.network.ServiceBuilder
import java.util.*
import kotlin.collections.ArrayList

val EMPTY_POST_LIST = Collections.emptyList<Post>()

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val listOfPosts = ArrayList<Post>()

     private val _listOfPost =  MutableLiveData<List<Post>>()



    private var post: Post? = null



       suspend fun getPostsFromServer(): LiveData<List<Post>> {
         val request = ServiceBuilder.buildService(PostService::class.java)
             val response = request.getPosts()

             if (response.isSuccessful) {

                 val postResponse = response.body()

                 for (p in postResponse!!.indices) {

                     post = Post(
                         postResponse[p].body,
                         postResponse[p].id,
                         postResponse[p].title,
                         postResponse[p].userId

                     )
                     listOfPosts.add(post!!)
                     _listOfPost.postValue(listOfPosts)

                 }
             }
         return _listOfPost
     }


}














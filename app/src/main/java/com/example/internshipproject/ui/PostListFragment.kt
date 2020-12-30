package com.example.internshipproject.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.internshipproject.R
import com.example.internshipproject.model.Post
import com.example.internshipproject.viewModels.EMPTY_POST_LIST
import com.example.internshipproject.viewModels.PostViewModel
import kotlinx.android.synthetic.main.fragment_post_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 */

internal const val POST_TRANSFER = "POST_TRANSFER"

class PostListFragment : Fragment(), OnCellClickListener {

    private var navController: NavController? = null
    private lateinit var viewModel: PostViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity.let {
            viewModel = ViewModelProvider(it!!).get(PostViewModel::class.java)
        }

        getList()


        recyclerViewId.hasFixedSize()
        recyclerViewId.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter = RecyclerViewAdapter(EMPTY_POST_LIST, this)
        recyclerViewId.adapter = recyclerViewAdapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(requireContext(),recyclerViewAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerViewId)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)

    }


    override fun onCellClickListener(item: Post, position: Int) {
        val postDetailsFragment = PostDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable(POST_TRANSFER, item)
        postDetailsFragment.arguments = bundle
       val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, postDetailsFragment)


        transaction.commit()

    }


    private fun getList(){
    lifecycleScope.launch{
    withContext(Dispatchers.Main){
        viewModel.getPostsFromServer().observe(viewLifecycleOwner, Observer {posts -> recyclerViewAdapter.setDataToAdapter(posts ?: EMPTY_POST_LIST)})

    }
    }

    }




}







package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.Resource
import com.mobile.azrinurvani.dagger2kotlinpractice.util.VerticalSpaceItemDecoration
import com.mobile.azrinurvani.dagger2kotlinpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject

//TODO 71 - Create PostsFragment as LifecycleOwner
class PostsFragment : DaggerFragment() {

    companion object{
        private const val TAG ="PostsFragment"
    }

    private lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    //TODO 80 - Add object for inject adapter
    @Inject
    lateinit var adapter : PostsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO 72 - Initialize postViewModel
        viewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel::class.java)

        initRecyclerView()
        subscribeObserver()
    }

    //TODO 76 - Create subscribeObserver method
    private fun subscribeObserver(){
        viewModel.observePosts()?.removeObservers(viewLifecycleOwner)
        viewModel.observePosts()?.observe(viewLifecycleOwner,Observer{
            if (it != null){
                //TODO 82 - Create switch statement for ERROR, LOADING and SUCCESS
                when (it.status){
                    Resource.Status.LOADING -> {
                        Log.d(TAG,"onChanged : LOADING....")
                    }
                    Resource.Status.ERROR -> {
                        Log.e(TAG,"onChanged : ERROR...."+it.message)
                    }
                    Resource.Status.SUCCESS -> {
                        Log.d(TAG,"onChanged : SUCCESS....")
                        it.data?.let { postsData -> adapter.setPosts(postsData) }
                    }
                }
            }
        })
    }

    //TODO 81 - Create new method for init recycler view
    private fun initRecyclerView(){
        recycler_view.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpaceItemDecoration(15)
        recycler_view.addItemDecoration(itemDecoration)
        recycler_view.adapter = adapter
    }
}
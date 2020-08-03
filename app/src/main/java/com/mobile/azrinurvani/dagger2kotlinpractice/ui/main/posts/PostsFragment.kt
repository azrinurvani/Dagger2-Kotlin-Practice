package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mobile.azrinurvani.dagger2kotlinpractice.R
import com.mobile.azrinurvani.dagger2kotlinpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

//TODO 71 - Create PostsFragment as LifecycleOwner
class PostsFragment : DaggerFragment() {

    companion object{
        private const val TAG ="PostsFragment"
    }

    private lateinit var viewModel: PostsViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO 72 - Initialize postViewModel
        viewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel::class.java)
        Log.d(TAG,"onViewCreated : PostsFragment was created...")
    }
}
package com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.mobile.azrinurvani.dagger2kotlinpractice.SessionManager
import com.mobile.azrinurvani.dagger2kotlinpractice.models.Post
import com.mobile.azrinurvani.dagger2kotlinpractice.network.main.MainApi
import com.mobile.azrinurvani.dagger2kotlinpractice.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

//TODO 67 - Create PostsViewModel with 2 parameter (sessionManager,mainApi)
class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    companion object{
        private const val TAG = "PostsViewModel"
    }

    init {
        Log.d(TAG,"PostsViewModel : viewModel is working...")
    }

    //TODO 74 - Create Mediator Live Data object
    var posts : MediatorLiveData<Resource<List<Post>>>? = null


    //TODO 75 - Create observePosts method
    fun observePosts() : LiveData<Resource<List<Post>>>? {
        if (posts==null){
            posts = MediatorLiveData()
            posts?.value = Resource.loading(null)

            val source : LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id!!)
                    .onErrorReturn{
                        Log.e(TAG,"apply: "+it.localizedMessage)
                        val post = Post()
                        post.id = -1
                        val arrayPost = ArrayList<Post>()
                        arrayPost.add(post)
                        arrayPost
                    }
                    .map(object: Function<List<Post>,Resource<List<Post>>>{
                        override fun apply(postMap: List<Post>): Resource<List<Post>> {
                            if (postMap.isNotEmpty()){
                                if (postMap[0].id ==-1){
                                    return Resource.error("Something went wrong")
                                }
                            }
                            return Resource.success(postMap)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )
           posts?.addSource(source) {
               posts?.value =it
               posts?.removeSource(source)
           }
        }
        return posts
    }



}
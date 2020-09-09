package com.mistpaag.jsonplaceholder.post.views.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.api.load
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.data.repository.Repository
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.utils.Const
import kotlinx.android.synthetic.main.post_item.view.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailPostViewModel(private val repository: Repository): ViewModel(){

    val postDetail : LiveData<PostResponse>
        get()= _postDetail
    private val _postDetail = MutableLiveData<PostResponse>()

    fun fetchUser(){
        viewModelScope.launch {
            repository.fetchUser().collect {
                it?.let {
                }
            }
        }
    }

    fun fetchPost(id:Int){
        viewModelScope.launch {
            repository.fetchLocalPost(id).collect {
                _postDetail.value = it
            }
        }
    }

    fun updateFavoritePostStatus(){
        viewModelScope.launch {
            repository.updateFavoritePostStatus(_postDetail.value!!.id, !_postDetail.value!!.favorite)
            fetchPost(_postDetail.value!!.id)
        }
    }

}
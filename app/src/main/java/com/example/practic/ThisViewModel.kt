package com.example.practic

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
// TODO!!! fix this shit asap dumbass
class ThisViewModel: ViewModel() {
    private val LiveLikesData: LiveData<Int>
        get() = LikesData
    private val LiveRepostData: LiveData<Int>
        get() = RepostData
    private val LiveViewData: LiveData<Int>
        get() = ViewData
    private val LiveTextData: LiveData<String>
        get() = TextData
    private val LiveAuthorData: LiveData<Author>
        get() = AuthorData

    private val LikesData = MutableLiveData<Int>()
    private val RepostData = MutableLiveData<Int>()
    private val ViewData = MutableLiveData<Int>()
    private val TextData = MutableLiveData<String>()
    private val AuthorData = MutableLiveData<Author>()
    @Composable
    fun Screen(
        viewModel: ThisViewModel = viewModel()
    ) {
        val time = TimeParser()
        //val btpit = Author(painterResource(id = R.mipmap.ic_launcher_foreground), "BTPIT", time.getTimeNow())
        val post = Post(LiveTextData.value!!, LiveLikesData.value!!, LiveRepostData.value!!, LiveViewData.value!!, LiveAuthorData.value!!)
        post.Draw()
    }
}
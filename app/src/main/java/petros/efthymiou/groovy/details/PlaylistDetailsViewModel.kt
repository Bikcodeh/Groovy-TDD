package petros.efthymiou.groovy.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import petros.efthymiou.groovy.playlist.PlaylistRepositoryImpl


class PlaylistDetailsViewModel(
    private val service: PlaylistDetailService
) : ViewModel() {


    private val _loader: MutableLiveData<Boolean> = MutableLiveData()
    val loader: LiveData<Boolean> get() = _loader

    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(id: String) {
        viewModelScope.launch {
            _loader.postValue(true)
            service.fetchPlaylistDetails(id).onEach {
                _loader.postValue(false)
            }.collect {
                playlistDetails.postValue(it)
            }
        }
    }
}

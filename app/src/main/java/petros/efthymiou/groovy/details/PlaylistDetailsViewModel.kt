package petros.efthymiou.groovy.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import petros.efthymiou.groovy.playlist.PlaylistRepositoryImpl


class PlaylistDetailsViewModel(
    private val service: PlaylistDetailService
) : ViewModel() {


    val playlistDetails: MutableLiveData<Result<PlaylistDetails>> = MutableLiveData()

    fun getPlaylistDetails(id: String) {
        viewModelScope.launch {
            service.fetchPlaylistDetails(id).collect {
                playlistDetails.postValue(it)
            }
        }
    }
}

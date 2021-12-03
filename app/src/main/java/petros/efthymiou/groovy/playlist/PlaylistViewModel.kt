package petros.efthymiou.groovy.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class PlaylistViewModel(
    private val repository: PlaylistRepositoryImpl
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlist = liveData<Result<List<Playlist>>> {
        loader.postValue(true)
        emitSource(repository.getPlaylist().asLiveData())
    }
}

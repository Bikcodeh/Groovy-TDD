package petros.efthymiou.groovy.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class PlaylistViewModel(
    private val repository: PlaylistRepositoryImpl
) : ViewModel() {

    val playlist = liveData<Result<List<Playlist>>> {
        emitSource(repository.getPlaylist().asLiveData())
    }
}

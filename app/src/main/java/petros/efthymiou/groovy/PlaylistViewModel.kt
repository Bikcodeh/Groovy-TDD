package petros.efthymiou.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel(
    private val repository: PlaylistRepositoryImpl
): ViewModel() {

    val playlist = MutableLiveData<Result<List<Playlist>>>()

    init {
        repository.getPlaylist()
    }
}

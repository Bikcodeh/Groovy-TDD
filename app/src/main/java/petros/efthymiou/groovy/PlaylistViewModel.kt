package petros.efthymiou.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val repository: PlaylistRepositoryImpl,
    dispatcher: CoroutineDispatcher
): ViewModel() {

    val playlist = MutableLiveData<Result<List<Playlist>>>()

    init {
        viewModelScope.launch(dispatcher) {
            repository.getPlaylist().collect { playlistResult ->
                playlist.value = playlistResult
            }
        }
    }
}

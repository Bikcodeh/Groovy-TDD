package petros.efthymiou.groovy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineDispatcher

class PlaylistViewModelFactory(
    private val repository: PlaylistRepositoryImpl,
    private val dispatcher: CoroutineDispatcher
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistViewModel(repository, dispatcher) as T
    }
}

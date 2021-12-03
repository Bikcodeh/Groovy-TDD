package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(private val service: PlaylistService) {

    suspend fun getPlaylist(): Flow<Result<List<Playlist>>> {
        return service.fetchPlaylist()
    }
}

package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistRepositoryImpl(private val service: PlaylistService) {

    suspend fun getPlaylist(): Flow<Result<List<Playlist>>> {
        service.fetchPlaylist()
        return flow {  }
    }
}

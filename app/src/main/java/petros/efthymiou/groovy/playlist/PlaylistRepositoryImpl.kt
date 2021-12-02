package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepositoryImpl(private val service: PlaylistService) {

    suspend fun getPlaylist(): Flow<Result<List<Playlist>>> {
        return service.fetchPlaylist()
    }
}

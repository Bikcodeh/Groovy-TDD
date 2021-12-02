package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistService {
    suspend fun fetchPlaylist(): Flow<Result<List<Playlist>>> {
        return flow {

        }
    }

}

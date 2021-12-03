package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(private val service: PlaylistService, private val mapper: PlaylistMapper) {

    suspend fun getPlaylist(): Flow<Result<List<Playlist>>> {
        return service.fetchPlaylist().map {
            if(it.isSuccess)
                Result.success(mapper.invoke(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
        }
    }
}

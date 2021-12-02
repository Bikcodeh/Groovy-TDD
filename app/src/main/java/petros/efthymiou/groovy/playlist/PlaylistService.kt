package petros.efthymiou.groovy.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlaylistService(private val playlistApi: PlaylistApi) {

    suspend fun fetchPlaylist(): Flow<Result<List<Playlist>>> {
        return  flow {
            emit(Result.success(playlistApi.fetchAllPlaylist()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}

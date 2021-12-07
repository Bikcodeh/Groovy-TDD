package petros.efthymiou.groovy.details

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class PlaylistDetailService @Inject constructor(
    private val api: PlaylistDetailsApi
) {
    fun fetchPlaylistDetails(id: String): Flow<Result<PlaylistDetails>> =
        flow {
            val result = api.fetchPlaylistDetails(id)
            result?.let {
                emit(Result.success(it))
            } ?: run {
                emit(Result.failure<PlaylistDetails>(RuntimeException("Error")))
            }
        }.catch { error ->
            Log.d("ERROR SERVICE", error.message ?: "")
            emit(Result.failure(error))
        }
}

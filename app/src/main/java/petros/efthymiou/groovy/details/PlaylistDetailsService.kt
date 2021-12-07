package petros.efthymiou.groovy.details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistDetailService @Inject constructor(){
    fun fetchPlaylistDetails(id: String): Flow<Result<PlaylistDetails>> {
        return flow {  }
    }
}

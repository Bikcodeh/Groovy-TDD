package petros.efthymiou.groovy.details

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaylistDetailsApi {

    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistDetails(@Path("id") id: String): PlaylistDetails?
}

package petros.efthymiou.groovy.playlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import petros.efthymiou.groovy.R

data class Playlist(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("category")
    @Expose
    val category: String,
    val image: Int = R.mipmap.playlist
)

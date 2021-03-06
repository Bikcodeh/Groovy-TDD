package petros.efthymiou.groovy.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.databinding.PlaylistItemBinding

class MyPlaylistRecyclerViewAdapter(
    private val values: List<Playlist>,
    private val listener: (String) -> Unit
) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(PlaylistItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playlistName.text = item.name
        holder.playlistCategory.text = item.category
        holder.playlistImage.setImageResource(item.image)
        holder.itemView.setOnClickListener {
            listener(item.id)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val playlistName: TextView = binding.tvPlaylistName
        val playlistCategory: TextView = binding.tvPlaylistCategory
        val playlistImage: AppCompatImageView = binding.ivPlaylist
    }
}
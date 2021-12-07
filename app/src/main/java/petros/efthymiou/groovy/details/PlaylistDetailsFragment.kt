package petros.efthymiou.groovy.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import petros.efthymiou.groovy.PlaylistDetailFragmentArgs
import petros.efthymiou.groovy.databinding.FragmentPlaylistDetailBinding
import javax.inject.Inject


class PlaylistDetailFragment : Fragment() {

    lateinit var viewModel: PlaylistDetailsViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding get() = _binding!!

    val args: PlaylistDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        val id: String = args.playlistId

        setupViewModel()

        viewModel.getPlaylistDetails(id)

        observeLiveData()
        return binding.root
    }

    private fun observeLiveData() {
        viewModel.playlistDetails.observe(viewLifecycleOwner) { result ->
            result.getOrNull()?.let { playlist ->
                setupUI(playlist)
            } ?: run {
                //TODO
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistDetailsViewModel::class.java]
    }

    private fun setupUI(playlist: PlaylistDetails) {
        with(binding) {
            playlistName.text = playlist.name
            playlistDetails.text = playlist.details
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistDetailFragment()
    }
}
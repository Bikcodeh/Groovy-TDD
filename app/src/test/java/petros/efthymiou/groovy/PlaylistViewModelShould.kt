package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PlaylistViewModel
    private val repository: PlaylistRepositoryImpl = mock()

    @Before
    fun setUp() {
        viewModel = PlaylistViewModel(repository)
    }

    @Test
    fun getPlaylistFromRepository() {
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlaylist()
    }
}
package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
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

    private val repository: PlaylistRepositoryImpl = mock()
    private val playlist = mock<List<Playlist>>()
    private val expected = Result.success(playlist)

    @Test
    fun getPlaylistFromRepository(): Unit = runBlocking {
        runBlocking {
            whenever(repository.getPlaylist()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository, Dispatchers.Unconfined)
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlaylist()
    }

    @Test
    fun emitsPlaylistFromRepository() = runBlocking{
        runBlocking {
            whenever(repository.getPlaylist()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository, Dispatchers.Unconfined)
        assertEquals(expected, viewModel.playlist.getValueForTest())
    }
}
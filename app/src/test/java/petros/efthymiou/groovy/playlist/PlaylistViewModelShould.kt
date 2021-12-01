package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepositoryImpl = mock()
    private val playlist = mock<List<Playlist>>()
    private val expected = Result.success(playlist)

    @Test
    fun getPlaylistFromRepository(): Unit = runBlocking {
        val viewModel = mockSuccessfulCase()
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlaylist()
    }

    @Test
    fun emitsPlaylistFromRepository() = runBlocking {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.playlist.getValueForTest())
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylist()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlaylistViewModel(repository)
    }
}
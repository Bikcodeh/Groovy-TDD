package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException

class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepositoryImpl = mock()
    private val playlist = mock<List<Playlist>>()
    private val expected = Result.success(playlist)
    private val exception = RuntimeException("Something went wrong")

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

    @Test
    fun emitErrorWhenReceiveError()  = runBlockingTest {
        val viewModel = mockErrorCase()

        assertEquals(exception, viewModel.playlist.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlist.getValueForTest()

            assertEquals(true, values[0])
        }
    }

    private suspend fun mockErrorCase(): PlaylistViewModel {
        whenever(repository.getPlaylist()).thenReturn(
            flow {
                emit(Result.failure<List<Playlist>>(exception))
            }
        )

        return PlaylistViewModel(repository)
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
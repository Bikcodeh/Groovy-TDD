package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PlaylistRepositoryImplShould: BaseUnitTest() {

    private val exception: Throwable = RuntimeException("Something went wrong")
    private val service: PlaylistService = mock()
    private val playlist = mock<List<Playlist>>()
    private val playlistRaw = mock<List<PlaylistRaw>>()
    private val mapper: PlaylistMapper = mock()

    @Test
    fun getPlaylistFromService() = runBlockingTest {

        val repository = mockSuccessfulCase()

        repository.getPlaylist()

        verify(service, times(1)).fetchPlaylist()
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureCase()

        assertEquals(exception, repository.getPlaylist().first().exceptionOrNull())
    }

    @Test
    fun emitPlaylistFromService() = runBlockingTest {

        val repository = mockSuccessfulCase()

        assertEquals(playlist, repository.getPlaylist().first().getOrNull())
    }

    @Test
    fun delegateBusinessLogicToMapper() = runBlockingTest {
        val repository = mockSuccessfulCase()

        repository.getPlaylist().first()

        verify(mapper, times(1)).invoke(playlistRaw)
    }

    private suspend fun mockFailureCase(): PlaylistRepositoryImpl {
        whenever(service.fetchPlaylist()).thenReturn(
            flow { emit(Result.failure<List<PlaylistRaw>>(exception)) }
        )

        return PlaylistRepositoryImpl(service, mapper)
    }

    private suspend fun mockSuccessfulCase(): PlaylistRepositoryImpl {
        whenever(service.fetchPlaylist()).thenReturn(
            flow { emit(Result.success(playlistRaw)) }
        )
        whenever(mapper.invoke(playlistRaw)).thenReturn(playlist)

        return PlaylistRepositoryImpl(service, mapper)
    }
}
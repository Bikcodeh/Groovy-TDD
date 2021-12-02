package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistApi = mock()
    private val playlist = mock<List<Playlist>>()

    @Test
    fun getPlaylistFromApi() = runBlockingTest {
        service = PlaylistService(api)

        service.fetchPlaylist().first()

        verify(api, times(1)).fetchAllPlaylist()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(Result.success(playlist), service.fetchPlaylist().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest {
        mockErrorCase()
        assertEquals(
            "Something went wrong",
            service.fetchPlaylist().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchAllPlaylist()).thenThrow(RuntimeException("Damn backend developer"))

        service = PlaylistService(api)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlaylist()).thenReturn(playlist)
        service = PlaylistService(api)
    }
}
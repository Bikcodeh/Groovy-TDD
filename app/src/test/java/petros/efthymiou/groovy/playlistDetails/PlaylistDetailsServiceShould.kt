package petros.efthymiou.groovy.playlistDetails

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import petros.efthymiou.groovy.details.PlaylistDetailService
import petros.efthymiou.groovy.details.PlaylistDetails
import petros.efthymiou.groovy.details.PlaylistDetailsApi
import petros.efthymiou.groovy.utils.BaseUnitTest

@ExperimentalCoroutinesApi
class PlaylistDetailsServiceShould: BaseUnitTest() {

    private val api: PlaylistDetailsApi = mock()
    private val id: String = "1"
    private lateinit var service: PlaylistDetailService
    private val playlist: PlaylistDetails = mock()
    private val expected = Result.success(playlist)

    @Before
    fun setUp() {
        service = PlaylistDetailService(api)
    }

    @Test
    fun getPlaylistDetailFromApi()  = runBlockingTest {

        service.fetchPlaylistDetails(id).first()

        verify(api, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runBlockingTest {
        whenever(api.fetchPlaylistDetails(id)).thenReturn(playlist)
        assertEquals(expected, service.fetchPlaylistDetails(id).first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        whenever(api.fetchPlaylistDetails(id)).thenThrow(
            RuntimeException("Backend error")
        )

        assertEquals("Backend error", service.fetchPlaylistDetails(id).first().exceptionOrNull()?.message)
    }


}
package petros.efthymiou.groovy.playlistDetails

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import petros.efthymiou.groovy.details.PlaylistDetailService
import petros.efthymiou.groovy.details.PlaylistDetails
import petros.efthymiou.groovy.details.PlaylistDetailsViewModel
import petros.efthymiou.groovy.playlist.PlaylistRepositoryImpl
import petros.efthymiou.groovy.utils.BaseUnitTest
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PlaylistDetailsViewModelShould : BaseUnitTest() {

    private val service: PlaylistDetailService = mock()
    private lateinit var viewModel: PlaylistDetailsViewModel
    private val id = "1"
    private val playlistDetails: PlaylistDetails = mock()
    private val expected = Result.success(playlistDetails)
    private val exception = RuntimeException("Something went wrong")
    private val error = Result.failure<PlaylistDetails>(exception)

    @Before
    fun setUp() {
        viewModel = PlaylistDetailsViewModel(service)
    }

    @Test
    fun getPlaylistDetailFromRepository() = runBlockingTest {

        mockSuccessfulCase()

        viewModel.playlistDetails.getValueForTest()

        verify(service, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun emitsPlaylistDetailsFromService() = runBlockingTest {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.playlistDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenServiceFails() {
        mockErrorCase()

        assertEquals(error, viewModel.playlistDetails.getValueForTest())
    }

    private fun mockErrorCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow { emit(error) }
        )

        viewModel.getPlaylistDetails(id)
    }

    private fun mockSuccessfulCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(flow {
            emit(expected)
        })

        viewModel.getPlaylistDetails(id)
    }
}
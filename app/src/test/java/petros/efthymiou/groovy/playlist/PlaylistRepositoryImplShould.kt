package petros.efthymiou.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.BaseUnitTest

class PlaylistRepositoryImplShould: BaseUnitTest() {

    private val service: PlaylistService = mock()

    @ExperimentalCoroutinesApi
    @Test
    fun getsPlaylistFromService() = runBlockingTest {

        val repository = PlaylistRepositoryImpl(service)

        repository.getPlaylist()

        verify(service, times(1)).fetchPlaylist()
    }
}
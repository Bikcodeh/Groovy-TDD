package petros.efthymiou.groovy.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}
package petros.efthymiou.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import petros.efthymiou.groovy.playlist.idlingResource

class PlaylistDetailFeature : BaseUITest() {

    val scenarioRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displaysPlaylistNameAndDetails() {

        navigateToDetailsScreen()

        assertDisplayed("Hard Rock Cafe")

        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")

        onView(allOf(withId(R.id.playlist_name))).check(
            matches((isDisplayed()))
        )
    }

    @Test
    fun displayLoaderWhileIsFetchingData() {
        IdlingRegistry.getInstance().unregister(idlingResource)

        Thread.sleep(2000)
        navigateToDetailsScreen()

        assertDisplayed(R.id.progressDetails)
    }

    @Test
    fun hidesLoader() {
        navigateToDetailsScreen()

        assertNotDisplayed(R.id.progressDetails)
    }

    private fun navigateToDetailsScreen() {
        onView(
            allOf(
                withId(R.id.ivPlaylist),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))
            )
        )
            .perform(ViewActions.click())
    }
}
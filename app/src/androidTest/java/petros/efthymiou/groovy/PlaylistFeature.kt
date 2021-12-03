package petros.efthymiou.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import petros.efthymiou.groovy.playlist.idlingResource

class PlaylistFeature : BaseUITest() {

    val scenarioRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlist")
    }

    @Test
    fun displayListOfPlaylist() {

        assertRecyclerViewItemCount(R.id.playlist_list, 10)

        onView(
            allOf(
                withId(R.id.tvPlaylistName),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))
            )
        )
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.tvPlaylistCategory),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))
            )
        )
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.ivPlaylist),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayLoaderWhileFetchingThePlaylist() {

        //Don't block our execution test while app is fetching our data
        IdlingRegistry.getInstance().unregister(idlingResource)

        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader() {
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displayRockImageForRockListItems() {

        onView(
            allOf(
                withId(R.id.ivPlaylist),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.ivPlaylist),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 3))
            )
        )
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailScreen() {
        onView(
            allOf(
                withId(R.id.ivPlaylist),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))
            )
        )
            .perform(click())

        assertDisplayed(R.id.playlist_details_root)
    }
}
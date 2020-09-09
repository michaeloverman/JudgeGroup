package digital.overman.michael.schools.ui.detail

import digital.overman.michael.schools.School
import org.hamcrest.core.Is.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.CountDownLatch

class DetailViewModelTest : DetailViewModel.Callbacks {

    private lateinit var subject: DetailViewModel
    private var latch = CountDownLatch(1)
    private var school = School(id = "01M292") // only need the id to fetch the SAT scores - don't need to initialize anything else, really...

    @Before
    fun setUp() {
        subject = DetailViewModel(school)
        subject.callback = this
        latch.await()
    }

    @Test
    fun scoresRetrieved() {
        assertThat(subject.scores?.satMath, `is`("404"))
    }

    override fun scoreReturn() {
        latch.countDown()
    }
}

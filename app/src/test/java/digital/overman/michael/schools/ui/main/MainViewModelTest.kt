package digital.overman.michael.schools.ui.main

import org.hamcrest.core.Is.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.CountDownLatch

class MainViewModelTest : MainViewModel.Callbacks {

    private lateinit var subject: MainViewModel
    private var latch = CountDownLatch(1)

    @Before
    fun setUp() {
        subject = MainViewModel()
        subject.callback = this
        latch.await()
    }

    @Test
    fun loads440Schools() {
        assertThat(subject.schools.size, `is`(440))
    }

    @Test
    fun alphabeticalSort() {
        subject.sortAlpha()
        assertThat(subject.schools[0].name, `is`("A. Philip Randolph Campus High School"))
    }

    @Test
    fun idSort() {
        subject.sortId()
        assertThat(subject.schools[0].name, `is`("Orchard Collegiate Academy (Henry Street School)"))
    }

    override fun schoolListReturn() {
        latch.countDown()
    }
}

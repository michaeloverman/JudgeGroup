package digital.overman.michael.schools.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import digital.overman.michael.schools.School

class DetailViewModelFactory(private val school: School) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(school) as T
        }
        throw IllegalArgumentException("Unknown view model type received")
    }
}

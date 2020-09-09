package digital.overman.michael.schools.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import digital.overman.michael.schools.R
import digital.overman.michael.schools.School
import digital.overman.michael.schools.databinding.DetailFragmentBinding

private const val TAG = "DetailFragment"
class DetailFragment : Fragment(), DetailViewModel.Callbacks {

    companion object {
        fun newInstance(school: School): DetailFragment {
            var frag = DetailFragment()
            frag.school = school
            return frag
        }
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    private lateinit var binding: DetailFragmentBinding
    lateinit var school: School

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )

        viewModelFactory = DetailViewModelFactory(school)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        viewModel.callback = this

        binding.schoolName.text = viewModel.school.name
        binding.address.text = viewModel.school.address()
        binding.schoolEmail.text = viewModel.school.email
        binding.phoneNumber.text = viewModel.school.phone
        binding.faxNumber.text = viewModel.school.fax
        binding.numStudents.text = viewModel.school.numStudents
        binding.gradRate.text = viewModel.school.graduationRate()
        binding.description.text = viewModel.school.description

        return binding.root
    }

    private fun updateScoreUI() {
        binding.mathSat.text = viewModel.scores?.satMath
        binding.readSat.text = viewModel.scores?.satRead
        binding.writeSat.text = viewModel.scores?.satWrite
    }

    override fun scoreReturn() {
        Log.d(TAG, "callback to update SAT scores")
        updateScoreUI()
    }
}

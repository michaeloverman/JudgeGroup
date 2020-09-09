package digital.overman.michael.schools.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import digital.overman.michael.schools.R
import digital.overman.michael.schools.School
import digital.overman.michael.schools.databinding.ListItemSchoolBinding
import digital.overman.michael.schools.databinding.MainFragmentBinding
import digital.overman.michael.schools.ui.detail.DetailFragment

private const val TAG = "MainFragment"
class MainFragment : Fragment(), MainViewModel.Callbacks {

    interface ActivityCallbacks {
        fun goToDetail(fragment: DetailFragment)
    }

    private var callbacks: ActivityCallbacks? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var schoolAdapter: SchoolAdapter
    private lateinit var binding: MainFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as ActivityCallbacks?
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.callback = this

        schoolAdapter = SchoolAdapter(viewModel.schools)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = schoolAdapter
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_alpha -> viewModel.sortAlpha()
            R.id.sort_by_id -> viewModel.sortId()
            else -> return super.onOptionsItemSelected(item)
        }
        schoolAdapter.notifyDataSetChanged()
        return true
    }

    private fun onSchoolClicked(pos: Int) {
        val school = viewModel.schools[pos]
        val fragment = DetailFragment.newInstance(school)
        callbacks?.goToDetail(fragment)
    }

    private inner class SchoolHolder(private val binding: ListItemSchoolBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            Log.d(TAG, "Initiating SchoolHolder SchoolViewModel...")
//            binding.viewModel
            itemView.setOnClickListener(this)
        }
        //
        fun bind(school: School) {
            binding.apply {
                schoolName.text = viewModel.schools[adapterPosition].name
                schoolNeighborhood.text = viewModel.schools[adapterPosition].neighborhood
                executePendingBindings()
            }
        }

        override fun onClick(p0: View?) {
            onSchoolClicked(adapterPosition)
        }
    }

    private inner class SchoolAdapter(var schools: List<School>) : RecyclerView.Adapter<SchoolHolder>() {

        init {
            Log.d(TAG, "Init SchoolAdapter, #schools: ${schools.size}")
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolHolder {
            val binding = DataBindingUtil.inflate<ListItemSchoolBinding>(
                layoutInflater,
                R.layout.list_item_school,
                parent,
                false
            )
            return SchoolHolder(binding)
        }

        override fun onBindViewHolder(holder: SchoolHolder, position: Int) {
            val school = schools[position]
            holder.bind(school)
        }

        override fun getItemCount(): Int {
//            Log.d(TAG, "getting numbr of schools: ${schools.size}")
            return schools.size
        }

    }

    override fun schoolListReturn() {
        Log.d(TAG, "Schoollist return callback: ${viewModel.schools.size}")
        schoolAdapter.schools = viewModel.schools
        schoolAdapter.notifyDataSetChanged()
    }
}

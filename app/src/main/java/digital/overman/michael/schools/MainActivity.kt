package digital.overman.michael.schools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import digital.overman.michael.schools.ui.detail.DetailFragment
import digital.overman.michael.schools.ui.main.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.ActivityCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun goToDetail(fragment: DetailFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

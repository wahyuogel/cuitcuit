package com.wgs.cuatcuit.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wgs.cuatcuit.R
import com.wgs.cuatcuit.fragment.CuitListFragment
import kotlinx.android.synthetic.main.activity_cuit_list.*

/**
 * Created by Alvin Rusli on 02/28/2020.
 */
class CuitListActivity : AppCompatActivity() {

    private val fragment by lazy { CuitListFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuit_list)
        if (savedInstanceState == null) initFragment()
    }

    /** Initialize the fragment */
    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(layout_fragment.id, fragment)
            .commit()
    }
}

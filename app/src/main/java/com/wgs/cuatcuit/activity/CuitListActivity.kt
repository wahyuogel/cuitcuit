package com.wgs.cuatcuit.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
        initToolbar()
        initView()
    }

    /** Initialize the fragment */
    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(layout_fragment.id, fragment)
            .commit()
    }

    private fun initToolbar() {
        toolbar.apply {
            setTitle(R.string.app_name)
            inflateMenu(R.menu.menu_toolbar_search)
            inflateMenu(R.menu.menu_toolbar_logout)

            setOnMenuItemClickListener {
                return@setOnMenuItemClickListener when (it.itemId) {
                    R.id.menuitem_search -> {
                        txt_search_layout.visibility = if (!txt_search_layout.isVisible) View.VISIBLE else View.INVISIBLE
                        true
                    }
                    R.id.menuitem_logout -> {
                        Toast.makeText(this@CuitListActivity, "TODO: LOGOUT", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun initView() {
        txt_search_layout.setEndIconOnClickListener {
            val query = txt_search.text.toString()
            if (query.isBlank()) {
                fragment.setQuery(null)
                toolbar.setTitle(R.string.app_name)
            } else {
                fragment.setQuery(query)
                toolbar.setTitle(query)
            }
            txt_search_layout.visibility = View.INVISIBLE
        }
    }
}

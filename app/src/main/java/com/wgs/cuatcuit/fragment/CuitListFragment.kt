package com.wgs.cuatcuit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wgs.cuatcuit.R

/**
 * Created by Alvin Rusli on 02/28/2020.
 */
class CuitListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cuit_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        const val KEY_QUERY = "query"

        fun newInstance(query: String? = null): CuitListFragment {
            return CuitListFragment().apply {
                val args = Bundle()
                args.putString(KEY_QUERY, query)
                arguments = args
            }
        }
    }
}

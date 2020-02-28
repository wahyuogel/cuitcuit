package com.wgs.cuatcuit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wgs.cuatcuit.R
import com.wgs.cuatcuit.adapter.CuitAdapter
import com.wgs.cuatcuit.model.core.Resource
import com.wgs.cuatcuit.view.util.EndlessScrollListener
import com.wgs.cuatcuit.viewmodel.CuitListViewModel
import kotlinx.android.synthetic.main.fragment_cuit_list.*

/**
 * Created by Alvin Rusli on 02/28/2020.
 */
class CuitListFragment : Fragment() {

    private var query: String? = null

    private val adapter by lazy { CuitAdapter() }

    private val viewModel by lazy { ViewModelProviders.of(this).get(CuitListViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cuit_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        query = arguments?.getString(KEY_QUERY)
        initViewModel()
        initView()
        fetchData()
    }

    private fun initViewModel() {
        viewModel.resource.observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Resource.LOADING -> onLoading()
                Resource.SUCCESS -> onSuccess()
                Resource.ERROR -> onFailure(it.error)
            }
        })
        viewModel.dataList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initView() {
        recycler_cuit.layoutManager = LinearLayoutManager(context)
        recycler_cuit.adapter = adapter
        recycler_cuit.addOnScrollListener(object : EndlessScrollListener(RecyclerView.VERTICAL, 1) {
            override fun onLoadMore() {
                if (viewModel.resource.value?.status == Resource.SUCCESS) fetchData()
            }
        })

        swipe_refresh_cuit.setOnRefreshListener {
            viewModel.refreshCuitList(query)
        }
    }

    private fun fetchData() {
        viewModel.getCuitList(query)
    }

    private fun onLoading() {
        txt_error.visibility = View.GONE
        progress_load.visibility = View.VISIBLE
    }

    private fun onSuccess() {
        swipe_refresh_cuit.isRefreshing = false
        progress_load.visibility = View.GONE
        txt_error.visibility = View.GONE
    }

    private fun onFailure(t: Throwable) {
        txt_error.text = t.message

        swipe_refresh_cuit.isRefreshing = false
        progress_load.visibility = View.GONE
        txt_error.visibility = View.VISIBLE
    }

    fun setQuery(query: String?) {
        this.query = query
        viewModel.refreshCuitList(query)
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

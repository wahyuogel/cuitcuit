package com.wgs.cuatcuit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wgs.cuatcuit.api.ApiUtils
import com.wgs.cuatcuit.model.Cuit
import com.wgs.cuatcuit.model.CuitListResponse
import com.wgs.cuatcuit.model.core.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset

/**
 * Created by Alvin Rusli on 01/24/2020.
 */
class CuitListViewModel : ViewModel() {

    val resource = MutableLiveData<Resource<Any>>()
    val dataList = MutableLiveData<ArrayList<Cuit>>()

    var page = 1
    var isLoadFinished = false

    init {
        dataList.value = ArrayList()
    }

    fun refreshCuitList(query: String? = null) {
        dataList.value?.clear()
        page = 1
        isLoadFinished = false
        getCuitList(query)
    }

    fun getCuitList(query: String? = null) {
        resource.postValue(Resource.loading())
        val call = ApiUtils.getInterface("http://private-778487-alvinrusliappschef.apiary-mock.com").getCuitList(
            searchQuery = query,
            page = page,
            perPage = 10)
        call.enqueue(object : Callback<CuitListResponse> {
            override fun onResponse(call: Call<CuitListResponse>, response: Response<CuitListResponse>) {
                if (response.isSuccessful) {
                    val list = ArrayList<Cuit>()
                    dataList.value?.let { list.addAll(it) }
                    val newList = response.body()?.data
                    if (!newList.isNullOrEmpty()) list.addAll(newList)
                    if (newList?.size ?: -1 < page) isLoadFinished = true
                    else page++
                    dataList.postValue(list)
                    resource.postValue(Resource.success(response.body()))
                } else {
                    val errorBytes = response.errorBody()!!.bytes()
                    val errorBody = String(errorBytes, Charset.forName("UTF-8"))
                    resource.postValue(Resource.error(Exception(errorBody)))
                }
            }

            override fun onFailure(call: Call<CuitListResponse>, t: Throwable) {
                resource.postValue(Resource.error(t))
            }
        })
    }
}

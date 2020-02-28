package com.wgs.cuatcuit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.volokh.danylo.hashtaghelper.HashTagHelper
import com.wgs.cuatcuit.R
import com.wgs.cuatcuit.model.Cuit
import kotlinx.android.synthetic.main.adapter_cuit.view.*


/**
 * Created by Alvin Rusli on 02/28/2020.
 */
class CuitAdapter : RecyclerView.Adapter<CuitAdapter.ViewHolder>() {

    val list = ArrayList<Cuit>()

    val hashTagListener: ((hashTag: String) -> Unit)? = null
    val likeListener: ((data: Cuit) -> Unit)? = null

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_cuit, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hashTagHelper: HashTagHelper

        init {
            itemView.apply {
                hashTagHelper = HashTagHelper.Creator.create(ContextCompat.getColor(context, R.color.colorPrimary)) {
                    hashTagListener?.invoke(it)
                }
                btn_like_toggle.setOnClickListener {
                    val data = list[adapterPosition]
                    likeListener?.invoke(data)
                }
            }
        }

        fun bindView() {
            val data = list[adapterPosition]
            itemView.apply {
                txt_name.text = data.name
                txt_date.text = data.date

                if (!data.image.isNullOrBlank()) {
                    layout_image.visibility = View.VISIBLE
                    Picasso.get().load(data.image).into(img_cuit)
                } else {
                    layout_image.visibility = View.GONE
                }

                txt_message.text = data.message
                hashTagHelper.handle(txt_message)

                if (!data.isLiked) {
                    btn_like_toggle.text = context.getString(R.string.action_like)
                } else {
                    btn_like_toggle.text = context.getString(R.string.action_unlike)
                }
            }
        }
    }
}

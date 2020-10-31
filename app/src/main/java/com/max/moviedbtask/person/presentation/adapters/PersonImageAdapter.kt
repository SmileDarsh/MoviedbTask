package com.max.moviedbtask.person.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.max.moviedbtask.R
import com.max.moviedbtask.core.utils.SharedPreferencesManager.saveImageUrl
import com.max.moviedbtask.core.utils.loadUrl
import kotlinx.android.synthetic.main.item_image.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class PersonImageAdapter : RecyclerView.Adapter<PersonImageAdapter.PersonImageViewHolder>() {
    private val mList = mutableListOf<String>()

    fun addPersonImage(item: String) {
        mList.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PersonImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        )

    override fun onBindViewHolder(holder: PersonImageViewHolder, position: Int) =
        holder.bindView(mList[position])

    override fun getItemCount() = mList.size

    inner class PersonImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: String) {
            itemView.ivImage.loadUrl(item)
            itemView.setOnClickListener {
                saveImageUrl(item)
                EventBus.getDefault().post(itemView.ivImage)
            }
        }
    }
}
package com.max.moviedbtask.person.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.max.moviedbtask.R
import com.max.moviedbtask.core.utils.PaginationAdapter
import com.max.moviedbtask.core.utils.listToString
import com.max.moviedbtask.core.utils.loadUrl
import com.max.moviedbtask.person.domain.model.Person
import kotlinx.android.synthetic.main.item_person.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by µðšţãƒâ ™ on 29/10/2020.
 *  ->
 */
class PersonAdapter(private val mList: MutableList<Person> = mutableListOf()) :
    PaginationAdapter(mList) {

    fun addPerson(item: Person) {
        mList.add(item)
        notifyItemInserted(itemCount)
    }

    override fun addCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
    )

    override fun itemCount() = mList.size

    override fun addBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as PersonViewHolder).bindView(mList[position])

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Person) {
            itemView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.fade_scale_animation)
            itemView.tvName.text = item.name
            itemView.tvKnownForDepartment.text = item.knownForDepartment
            itemView.tvKnownFor.listToString(item.knownFor.map { it.title })
            itemView.ivImage.loadUrl(item.image)
            itemView.setOnClickListener { EventBus.getDefault().post(item) }
        }
    }
}
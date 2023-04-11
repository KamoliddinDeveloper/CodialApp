package com.example.codialapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codialapp.databinding.CourseAdapterItemBinding
import com.example.codialapp.models.Course

class CourseAdapter(val list: ArrayList<Course>, val context: Context, val rvAcrion: RvAction) :
    RecyclerView.Adapter<CourseAdapter.Vh>() {

    inner class Vh(val rvItem: CourseAdapterItemBinding) : RecyclerView.ViewHolder(rvItem.root) {
        fun onBind(course: Course, position: Int) {
            rvItem.name.text = course.name
            rvItem.card.setOnClickListener {
                rvAcrion.itemClick(course, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            CourseAdapterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    interface RvAction {
        fun itemClick(course: Course, position: Int)
    }
}

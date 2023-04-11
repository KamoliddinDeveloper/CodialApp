package com.example.codialapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codialapp.R
import com.example.codialapp.adapters.CourseAdapter
import com.example.codialapp.databinding.FragmentMentorsBinding
import com.example.codialapp.db.MyDbHelper
import com.example.codialapp.models.Course
import com.example.codialapp.models.MyMentorObject


class MentorsFragment : Fragment(), CourseAdapter.RvAction {
    private lateinit var binding: FragmentMentorsBinding
    private lateinit var list:ArrayList<Course>
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var courseAdapter: CourseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentMentorsBinding.inflate(layoutInflater)
        binding.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        myDbHelper= MyDbHelper(binding.root.context)
        list= ArrayList()
        list.addAll(myDbHelper.getAllCourses())
        courseAdapter= CourseAdapter(list, binding.root.context, this)
        binding.myRv.adapter=courseAdapter
        return binding.root
    }

    override fun itemClick(course: Course, position: Int) {
        MyMentorObject.courseId=position
        findNavController().navigate(R.id.mentorsListFragment, bundleOf("key" to course))
    }
}
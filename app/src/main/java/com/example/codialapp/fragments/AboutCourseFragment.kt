package com.example.codialapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.codialapp.R
import com.example.codialapp.databinding.FragmentAboutCourseBinding
import com.example.codialapp.db.MyDbHelper
import com.example.codialapp.models.Course

class AboutCourseFragment : Fragment() {
    private lateinit var binding:FragmentAboutCourseBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list:ArrayList<Course>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentAboutCourseBinding.inflate(layoutInflater)
        val index=arguments?.getSerializable("key") as Course
        myDbHelper = MyDbHelper(binding.root.context)
        list= ArrayList()
        list.addAll(myDbHelper.getAllCourses())
        binding.info.text=index.name
        binding.about.text=index.info

        binding.trash.setOnClickListener {
            myDbHelper.getAllGroups().forEach{
                if (it.course==index){
                    myDbHelper.deleteGroup(it)
                }
            }
            myDbHelper.deleteCourse(index)
            Toast.makeText(binding.root.context, "Kurs o'chirildi", Toast.LENGTH_SHORT).show()
            fragmentManager?.popBackStack()
        }
        return binding.root
    }
}
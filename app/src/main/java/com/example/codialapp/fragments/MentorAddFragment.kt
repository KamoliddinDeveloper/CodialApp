package com.example.codialapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.codialapp.databinding.FragmentMentorAddBinding
import com.example.codialapp.db.MyDbHelper
import com.example.codialapp.models.Course
import com.example.codialapp.models.Mentor
import com.example.codialapp.models.MyMentorObject

class MentorAddFragment : Fragment() {

    private lateinit var binding:FragmentMentorAddBinding
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentMentorAddBinding.inflate(layoutInflater)
        val index=arguments?.getSerializable("course") as Course
        myDbHelper= MyDbHelper(binding.root.context)

        binding.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        binding.btnSave.setOnClickListener {
            if (binding.name.text!!.isNotEmpty() && binding.surname.text!!.isNotEmpty() && binding.phone.text!!.isNotEmpty()){
                val mentor= Mentor(
                    name = binding.name.text.toString(),
                    surname = binding.surname.text.toString(),
                    phone = binding.phone.text.toString(),
                    course = MyMentorObject.courseId
                )
                myDbHelper.addMentor(mentor)
                Toast.makeText(binding.root.context, "Saqlandi", Toast.LENGTH_SHORT).show()
                fragmentManager?.popBackStack()
            }else{
                Toast.makeText(binding.root.context, "Iltimos hamma maydonlarni toldiring!", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}
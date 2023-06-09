package com.example.codialapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codialapp.R
import com.example.codialapp.adapters.MentorAdapter
import com.example.codialapp.databinding.FragmentMentorsListBinding
import com.example.codialapp.databinding.MentorEditDialogBinding
import com.example.codialapp.db.MyDbHelper
import com.example.codialapp.models.Course
import com.example.codialapp.models.Mentor
import com.example.codialapp.models.MyMentorObject


class MentorsListFragment : Fragment(), MentorAdapter.MentorItemEvent {

    private lateinit var binding: FragmentMentorsListBinding
    private lateinit var list:ArrayList<Mentor>
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentMentorsListBinding.inflate(layoutInflater)
        binding.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        val index=arguments?.getSerializable("key") as Course
        binding.info.text=index.name

        myDbHelper= MyDbHelper(binding.root.context)
        list= ArrayList()
        for (i in myDbHelper.getAllMentors()){
            if (i.course == MyMentorObject.courseId) list.add(i)
        }
        mentorAdapter= MentorAdapter(list, this)
        binding.myRv.adapter=mentorAdapter

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.mentorAddFragment, bundleOf("course" to index))
        }

        return binding.root
    }

    override fun editClick(mentor: Mentor, position: Int) {
        val dialog=AlertDialog.Builder(binding.root.context).create()
        val mentorEditDialogBinding= MentorEditDialogBinding.inflate(layoutInflater)
        mentorEditDialogBinding.name.setText(mentor.name)
        mentorEditDialogBinding.about.setText(mentor.surname)
        mentorEditDialogBinding.about2.setText(mentor.phone)
        mentorEditDialogBinding.save.text="O'zgartirish"
        dialog.setView(mentorEditDialogBinding.root)
        dialog.show()

        mentorEditDialogBinding.save.setOnClickListener {
            if (mentorEditDialogBinding.name.text.toString().isNotEmpty() && mentorEditDialogBinding.about.text.toString().isNotEmpty() && mentorEditDialogBinding.about2.text.toString().isNotEmpty()){

                mentor.name=mentorEditDialogBinding.name.text.toString()
                mentor.surname=mentorEditDialogBinding.about.text.toString()
                mentor.phone=mentorEditDialogBinding.about2.text.toString()

                myDbHelper.editMentors(mentor)
                mentorAdapter.notifyDataSetChanged()
                Toast.makeText(binding.root.context, "Tahrirlandi", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }else{
                Toast.makeText(binding.root.context, "Iltimos hamma maydonlarni toldiring!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun trashClick(mentor: Mentor, position: Int) {
        myDbHelper.deleteMentor(mentor)
        list.remove(mentor)
        Toast.makeText(binding.root.context, "Mentor ochirildi", Toast.LENGTH_SHORT).show()
        mentorAdapter.notifyDataSetChanged()
    }


}
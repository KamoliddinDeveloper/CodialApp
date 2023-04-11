package com.example.codialapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.codialapp.adapters.SpinnerAdapter
import com.example.codialapp.databinding.FragmentGroupAddBinding
import com.example.codialapp.db.MyDbHelper
import com.example.codialapp.models.Course
import com.example.codialapp.models.Group

class GroupAddFragment : Fragment() {

    private lateinit var binding: FragmentGroupAddBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var spinnerAdapter: SpinnerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentGroupAddBinding.inflate(layoutInflater)

        myDbHelper= MyDbHelper(binding.root.context)

        val course=arguments?.getSerializable("key") as Course

        val listMentor=myDbHelper.getAllMentors()
        val listMentorName=ArrayList<String>()
        listMentor.forEach {
            listMentorName.add(it.name.toString())
        }
        spinnerAdapter=SpinnerAdapter(listMentorName)
        binding.mentor.adapter=spinnerAdapter

        val listTime=ArrayList<String>()
        listTime.add("16:00-18:00")
        listTime.add("18:00-20:00")
        binding.time.adapter=SpinnerAdapter(listTime)

        val listDays=ArrayList<String>()
        listDays.add("Dushanba/Chorshanba/Juma")
        listDays.add("Seshanba/Payshanba/Shanba")
        binding.days.adapter=SpinnerAdapter(listDays)

        binding.btnSave.setOnClickListener {
            if (binding.name.text.toString().isNotEmpty() && binding.mentor.selectedItem.toString().isNotEmpty()){
                val group= Group(
                    name = binding.name.text.toString(),
                    mentor = listMentor[binding.mentor.selectedItemPosition],
                    time = listTime[binding.time.selectedItemPosition],
                    daysOfWeek = listDays[binding.days.selectedItemPosition],
                    course = course,
                    open = false
                )
                myDbHelper.addGroup(group)
                Toast.makeText(binding.root.context, "Saqlandi", Toast.LENGTH_SHORT).show()
                fragmentManager?.popBackStack()
            }else{
                Toast.makeText(binding.root.context, "Iltimos hamma maydonlarni toldiring!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        return binding.root
    }


}
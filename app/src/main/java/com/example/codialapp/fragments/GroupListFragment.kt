package com.example.codialapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.codialapp.R
import com.example.codialapp.adapters.GroupAdapter
import com.example.codialapp.adapters.StateAdapter
import com.example.codialapp.databinding.FragmentGroupListBinding
import com.example.codialapp.databinding.TabItemViewBinding
import com.example.codialapp.db.MyDbHelper
import com.example.codialapp.models.Course
import com.example.codialapp.models.Group
import com.example.codialapp.models.MyMentorObject
import com.example.codialapp.models.PagerItem
import com.google.android.material.tabs.TabLayoutMediator


class GroupListFragment : Fragment(), GroupAdapter.GroupRvEvent {

    private var list=ArrayList<PagerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.add(PagerItem("Ochilgan guruhlar"))
        list.add(PagerItem("Ochilayotgan guruhlar"))
    }

    private lateinit var binding: FragmentGroupListBinding
    private lateinit var stateAdapter: StateAdapter
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentGroupListBinding.inflate(layoutInflater)

        myDbHelper=MyDbHelper(binding.root.context)

        val course=arguments?.getSerializable("key") as Course
        binding.info.text=course.name

        binding.back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        stateAdapter= StateAdapter(list, this)
        binding.myViewpager.adapter=stateAdapter



        TabLayoutMediator(binding.myTablayout,binding.myViewpager){tab,position->
            val tabItemView= TabItemViewBinding.inflate(layoutInflater)

            tabItemView.text.text=list[position].open


            tab.customView = tabItemView.root
        }.attach()

        binding.myViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        binding.add.visibility=View.GONE
                        MyMentorObject.onRegisterCallBack=0
                    }
                    1->{
                        binding.add.visibility=View.VISIBLE
                        MyMentorObject.onRegisterCallBack=1
                    }
                }
            }
        })

        stateAdapter.notifyItemChanged(binding.myViewpager.currentItem)

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.groupAddFragment, bundleOf("key" to course))
        }

        return binding.root
    }

    override fun viewClick(group: Group, position: Int) {
    }

    override fun editClick(group: Group, position: Int) {

    }

    override fun trashClick(group: Group, position: Int) {
    }

}
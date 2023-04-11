package com.example.codialapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.codialapp.R
import com.example.codialapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)

        binding.apply {

            course.setOnClickListener {
                findNavController().navigate(R.id.coursesFragment)
            }

            mentor.setOnClickListener {
                findNavController().navigate(R.id.mentorsFragment)
            }

            group.setOnClickListener {
                findNavController().navigate(R.id.groupsFragment)
            }
        }

        return binding.root
    }
}
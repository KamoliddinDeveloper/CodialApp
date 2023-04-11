package com.example.codialapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.codialapp.fragments.ViewPagerItemFragment
import com.example.codialapp.models.PagerItem

class StateAdapter(val list:ArrayList<PagerItem>, fragment: Fragment)
    : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return ViewPagerItemFragment.newInstance(list[position].open, "")
    }
}
package com.lfh.wanmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.FragmentMainBinding
import com.lfh.wanmvvm.logic.base.BaseFragment


class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val listFragments = arrayListOf<Fragment>()

    init {
        listFragments.apply {
            add(homeFragment)
            add(pjoFragment)
            add(pubnumFragment)
            add(squareFragment)
            add(mineFragment)
        }
    }

    private val homeFragment by lazy { HomeFragment() }
    private val pjoFragment by lazy { PjoFragment() }
    private val pubnumFragment by lazy { PubnumFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val mineFragment by lazy { MineFragment() }


    override fun initViewModel() {

    }

    override fun getResourceId() = R.layout.fragment_main

    override fun initFragmentViewModel() {

    }

    override fun init(savedInstanceState: Bundle?) {

        binding.bnvMain.run {
            setOnNavigationItemSelectedListener() { item ->
                when (item.itemId) {
                    R.id.item_home -> {
                        binding.vpMainPager.setCurrentItem(0, false)
                    }
                    R.id.item_ground -> {
                        binding.vpMainPager.setCurrentItem(1, false)
                    }
                    R.id.item_pub_num -> {
                        binding.vpMainPager.setCurrentItem(2, false)
                    }
                    R.id.item_project -> {
                        binding.vpMainPager.setCurrentItem(3, false)
                    }
                    R.id.item_mine -> {
                        binding.vpMainPager.setCurrentItem(4, false)
                    }
                }
                return true
            }
        }
    }


}
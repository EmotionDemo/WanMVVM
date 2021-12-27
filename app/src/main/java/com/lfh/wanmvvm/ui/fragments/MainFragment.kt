package com.lfh.wanmvvm.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.FragmentMainBinding
import com.lfh.wanmvvm.logic.base.BaseFragment
import com.lfh.wanmvvm.logic.home.HomeFragment
import com.lfh.wanmvvm.util.doSelect
import com.lfh.wanmvvm.util.iniFragment


class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val listFragments = arrayListOf<Fragment>()

    private val homeFragment by lazy { HomeFragment() }
    private val pjoFragment by lazy { PjoFragment() }
    private val pubnumFragment by lazy { PubnumFragment() }
    private val squareFragment by lazy { SquareFragment() }
    private val mineFragment by lazy { MineFragment() }

    init {
        listFragments.apply {
            add(homeFragment)
            add(pjoFragment)
            add(pubnumFragment)
            add(squareFragment)
            add(mineFragment)
        }
    }


    override fun initViewModel() {

    }

    override fun getResourceId() = R.layout.fragment_main

    override fun initFragmentViewModel() {

    }

    /**
     * 初始化
     */
    override fun init(savedInstanceState: Bundle?) {
        binding.vpMainPager.iniFragment(childFragmentManager, listFragments).run {
            offscreenPageLimit = listFragments.size
        }
        binding.vpMainPager.doSelect {
            binding.bnvMain.menu.getItem(it).isChecked = true
        }

        binding.bnvMain.run {
            removeLongToutchToast()
            //lambda表达式
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_home ->
                        binding.vpMainPager.setCurrentItem(0, false)

                    R.id.item_ground ->
                        binding.vpMainPager.setCurrentItem(1, false)

                    R.id.item_pub_num ->
                        binding.vpMainPager.setCurrentItem(2, false)

                    R.id.item_project ->
                        binding.vpMainPager.setCurrentItem(3, false)

                    R.id.item_mine ->
                        binding.vpMainPager.setCurrentItem(4, false)
                }
                true
            }
        }

    }

    private fun BottomNavigationView.removeLongToutchToast() {
        this.getChildAt(0).findViewById<View>(R.id.item_home).setOnLongClickListener {
            true
        }
        this.getChildAt(0).findViewById<View>(R.id.item_ground).setOnLongClickListener {
            true
        }
        this.getChildAt(0).findViewById<View>(R.id.item_pub_num).setOnLongClickListener {
            true
        }
        this.getChildAt(0).findViewById<View>(R.id.item_project).setOnLongClickListener {
            true
        }
        this.getChildAt(0).findViewById<View>(R.id.item_mine)
            .setOnLongClickListener(fun(it: View): Boolean {
                return true
            })
    }


}
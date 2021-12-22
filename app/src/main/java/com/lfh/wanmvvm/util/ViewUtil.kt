package com.lfh.wanmvvm.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager


/**
 * 顶层函数
 * 扩展函数，扩展viewpager,当View pager被选中时
 */
fun ViewPager.doSelect(selected: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            selected.invoke(position)
        }

        override fun onPageScrollStateChanged(state: Int) {

        }

    })
}

fun ViewPager.iniFragment(manager: FragmentManager, fragments: MutableList<Fragment>): ViewPager {
    //设置适配器
    adapter = object : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount() = fragments.size

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }
    }
    return this

}
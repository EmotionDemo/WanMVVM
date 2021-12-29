package com.lfh.wanmvvm.util

import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView


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

/**
 * 移除长按点击弹出Toast
 */
fun BottomNavigationView.removeLongTouchToast() {
    val bottomNavigationMenuView = this.getChildAt(0) as ViewGroup
    val size = bottomNavigationMenuView.childCount
    for (index in 0 until size) {
        bottomNavigationMenuView[index].setOnLongClickListener {
            true
        }
    }
}


package com.lfh.wanmvvm.logic.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.FragmentHomeBinding
import com.lfh.wanmvvm.logic.base.BaseFragment
import com.lfh.wanmvvm.logic.login.LoginActivity
import com.lfh.wanmvvm.util.toast
import com.stx.xhb.androidx.entity.BaseBannerInfo

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    var homeViewModel: HomeViewModel? = null
    private var bannerList: MutableList<BannerModel>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getResourceId()?.let {
            binding = DataBindingUtil.inflate(inflater, it, container, false)
            return binding.root
        }
    }


    override fun initViewModel() {
        homeViewModel = getActivityViewModel(HomeViewModel::class.java)
    }

    override fun getResourceId(): Int = R.layout.fragment_home

    override fun initFragmentViewModel() {

    }

    override fun init(savedInstanceState: Bundle?) {
        initTitle(binding.rlHeader.tvFgTitle, "主页")
        binding.smartFreshLayout.setOnRefreshListener {
            homeViewModel?.getBanner()
        }
        binding.rlHeader.rlSearch.setOnClickListener{
            startActivity(Intent(context,LoginActivity::class.java))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel?.getBanner()
    }

    override fun observe() {
        super.observe()
        homeViewModel?.banner?.observe(this, Observer {
            bannerList = it
            initBanners()
        })

        homeViewModel?.errorLiveData?.observe(this, Observer {
            toast(it.errorMsg)
        })
    }

    /**
     * 初始化banner
     */
    private fun initBanners() {

        val bannerModels = arrayListOf<BaseBannerInfo>()

        for (index in 0 until bannerList!!.size) {
            bannerModels.add(object : BaseBannerInfo {
                override fun getXBannerUrl(): Any {
                    return bannerList!![index].imagePath!!
                }

                override fun getXBannerTitle(): String {
                    return bannerList!![index].title!!
                }
            })
        }

        binding.bannerHome.apply {
            setAutoPalyTime(2000)
            setAutoPlayAble(true)
            setBannerData(bannerModels)
            loadImage { _, _, view, position ->
                Glide.with(context.applicationContext)
                    .load(bannerList!!.get(position).imagePath).centerCrop().into(view as ImageView)
            }
            setOnItemClickListener { banner, model, view, position ->
                toast(bannerList!!.get(position).title!!, Toast.LENGTH_SHORT)
            }
        }
    }


}
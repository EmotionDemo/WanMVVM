package com.lfh.wanmvvm.logic.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.FragmentHomeBinding
import com.lfh.wanmvvm.logic.base.BaseFragment
import com.lfh.wanmvvm.logic.base.article.ArticleListModel
import com.lfh.wanmvvm.util.cancelShow
import com.lfh.wanmvvm.util.toast
import com.stx.xhb.androidx.entity.BaseBannerInfo

/**
 * author:lifenghua
 * createTime:2021/12/30 8:43
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    var homeViewModel: HomeViewModel? = null
    val TAG = "HomeFragment"
    private var bannerList: MutableList<BannerModel>? = null
    private var articles: MutableList<ArticleListModel>? = null
    private var adapter: HomeArticleAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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

    /**
     * desc: 做一些初始化的操作
     * @author :lifenghua
     * createData :2021/12/30 10:16
     */
    override fun init(savedInstanceState: Bundle?) {
        initTitle(binding.rlHeader.tvFgTitle, "主页")
        //View初始化完成后进行banner初始化
        homeViewModel?.getBanner()
        homeViewModel?.getArticle()
        //进行刷新框初始化
        binding.smartFreshLayout.setOnRefreshListener {
            homeViewModel?.getBanner()
            homeViewModel?.getArticle()
        }
    }

    override fun observe() {
        super.observe()
        homeViewModel?.banner?.observe(this, Observer {
            bannerList = it
            initBanners()
            initArticles()
            toast("请求成功！")
            binding.smartFreshLayout.cancelShow()
        })

        /**
         * desc: 失败的livedata
         * @author :lifenghua
         * createData :2021/12/30 20:32
         */
        homeViewModel?.errorLiveData?.observe(this, Observer {
            toast(it.errorMsg)
            Log.d(TAG, it.errorMsg)
            binding.smartFreshLayout.cancelShow()
        })
    }

    private fun initArticles() {
        adapter = HomeArticleAdapter(articles!!, requireContext())

    }

    /**
     * 初始化banner
     */
    private fun initBanners() {
        //把从服务器获取的list值装填到bannermodel中
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

        //设置banner属性
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
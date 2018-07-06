package com.example.wangc.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wangc.bean.BannerBean
import com.example.wangc.wanandroid.R

/**
 * Created by wangc on 2018/7/6
 * E-MAIL:274281610@QQ.COM
 */
class MainAdapter (val mContext:Context, val mDatas: List<BannerBean.Data>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder{
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.main_iten,parent,false))
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder:MainAdapter.ViewHolder, position: Int) {
        holder.mTextView.text = mDatas[position].title
        Glide.with(mContext)
                .load(mDatas[position].imagePath)
                .into(holder.imageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var mTextView:TextView
        var imageView:ImageView
        init {
            mTextView = itemView.findViewById(R.id.text)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }
}
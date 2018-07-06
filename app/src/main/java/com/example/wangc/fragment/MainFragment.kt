package com.example.wangc.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wangc.adapter.MainAdapter
import com.example.wangc.bean.BannerBean
import com.example.wangc.http.ApiService
import com.example.wangc.wanandroid.R
import kotlinx.android.synthetic.main.fragment_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 *
 */
class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var datas :List<BannerBean.Data>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var runnable:Runnable = Runnable { kotlin.run {getList()} }

        Thread(runnable).start()


    }

    private fun getList() {
        val client: OkHttpClient = OkHttpClient.Builder().build()

        val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getMainList().enqueue(object :Callback<BannerBean>{
            override fun onFailure(call: Call<BannerBean>?, t: Throwable?) {
                Log.e("+++","失败"+t?.message)
            }

            override fun onResponse(call: Call<BannerBean>, response: Response<BannerBean>) {
                datas = response.body()?.data
                var adapter = activity?.let { MainAdapter(it,datas) }
                rl.adapter = adapter
                rl.layoutManager = LinearLayoutManager(activity)
            }
        })

    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     *
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * 在apply中，用this代表当前引用对象，并且调用其方法时，this可省略。apply必有返回值，且返回值是当前引用对象
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}

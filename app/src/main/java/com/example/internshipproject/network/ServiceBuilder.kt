package com.example.internshipproject.network

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import com.example.internshipproject.util.Constants
import com.example.internshipproject.util.MainApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

object ServiceBuilder {


        //val cacheSize = (5 * 1024 * 1024).toLong()
        //val myCache = Cache(MainApplication.applicationContext().cacheDir, cacheSize)

        private val client = OkHttpClient.Builder()
//            .cache(myCache)
//            .addInterceptor { chain ->
//                var request = chain.request()
//                request = if (checkForInternet(MainApplication.applicationContext()) == true)
//                    request.newBuilder().header("Cache-Control", "public, max-age=" + 60 * 5).build()
//                else
//                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
//                chain.proceed(request)
//            }
            .build()

         private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    fun <T> buildService(service: Class <T>): T{
        return retrofit.create(service)
    }

   /* @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun checkForInternet(context: Context) : Boolean {

        var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
            Log.i("Network connectivity", "$newValue")
        }

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    isNetworkConnected = false
                }
            })

        return isNetworkConnected
    }


    */
    }






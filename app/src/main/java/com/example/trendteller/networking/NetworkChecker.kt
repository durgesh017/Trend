package com.example.trendteller.networking

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkChecker(val connectivityManager: ConnectivityManager) {

    @RequiresApi(Build.VERSION_CODES.M)
    fun performAction(action: () -> Unit) {
        if (hasValidInternetConnection()) {
            action()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasValidInternetConnection(): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return  capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)


    }
}
package men.xjtu.discourse

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import okhttp3.Cache
import okhttp3.Dns
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.dnsoverhttps.DnsOverHttps
import java.io.File
import java.net.InetAddress
import java.net.UnknownHostException
import java.nio.charset.Charset

// From
// https://stackoverflow.com/questions/68449855/how-do-i-override-the-dns-resolution-of-an-android-webview
// https://stackoverflow.com/questions/65420610/how-to-implement-a-custom-dns-for-okhttp3
object WebViewNetworkHandler {
    private var client: OkHttpClient? = null

    operator fun invoke(webResourceRequest: WebResourceRequest?): WebResourceResponse? {
        val url: String = webResourceRequest?.url.toString()
        val headers: Headers? = webResourceRequest?.requestHeaders?.toHeaders()

        val newRequest = headers?.let {
            Request.Builder()
                .url(url)
                .headers(it)
                .build()
        }

        val response = newRequest?.let { getClient().newCall(newRequest).execute() }

        return response?.let { it ->
            WebResourceResponse(
                "", "", it.body.byteStream()
            )
        }
    }

    private fun getClient() = client ?: makeClient().also { client = it }

    private fun makeClient(): OkHttpClient {

        val appCache = Cache(File("cacheDir", "okhttpcache"), 10 * 1024 * 1024)

        val bootstrapClient = OkHttpClient.Builder()
            .cache(appCache)
            .build()


        val dns = DnsOverHttps.Builder().client(bootstrapClient)
            .url("https://1.1.1.1/dns-query".toHttpUrl())
            .build()

        return bootstrapClient.newBuilder()
            .dns(dns)
            .build()
    }

}

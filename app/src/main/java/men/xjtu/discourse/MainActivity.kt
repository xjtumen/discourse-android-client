package men.xjtu.discourse

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = MyWebViewClient(this)

        myWebView.loadUrl("https://xjtu.men")

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val myWebView: WebView = findViewById(R.id.webview)
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        if (keyCode == KeyEvent.KEYCODE_FORWARD && myWebView.canGoForward()) {
            myWebView.goForward()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }
}

fun IsXjtuMenService(string: String): Boolean {
    return try {
        Uri.parse(string).host.toString().endsWith("xjtu.men")
    } catch (e: Exception) {
        e.printStackTrace();
        false
    }
}

class MyWebViewClient(private val mainActivity: MainActivity) : WebViewClient() {
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        return try {
            when {
                // intercept XJTU.MEN traffic to use DNS over HTTPS
                IsXjtuMenService(request?.url.toString()) -> WebViewNetworkHandler(request)
                else -> null
            }
        } catch (e: Exception) {
            e.printStackTrace();
            null
        }
    }

    override fun shouldOverrideUrlLoading(webView: WebView?, url: String): Boolean {
        if (IsXjtuMenService(url)) return false
        // open non XJTU.MEN links in system browser
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        mainActivity.startActivity(intent)
        return true
    }

}



package cc.aoeiuv020.qidianlite

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wvBook.webViewClient = WebViewClient()
        wvBook.settings.javaScriptEnabled = true
        val url = "http://m.qidian.com"
        var sign = Utils.makeSign(url)
        sign = Uri.encode(Utils.des3(sign).replace(" ", ""))
        Utils.setSign(sign)
        wvBook.loadUrl(url)
    }

    override fun onBackPressed() {
        if (wvBook.canGoBack()) {
            wvBook.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

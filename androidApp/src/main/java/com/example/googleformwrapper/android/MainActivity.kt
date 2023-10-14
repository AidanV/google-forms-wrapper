package com.example.googleformwrapper.android

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.googleformwrapper.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormView(Greeting().greet())
                }
            }
        }
    }
}



@Composable
fun FormView(text: String) {
    // Declare a string that contains a url
    val mUrl = "https://docs.google.com/forms/d/e/1FAIpQLSd7oQx2d3xdFwKDWTfZ7pZqgXuXLBnTngQHRCHNe9byfpS1Vg/viewform?usp=sf_link"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        val webView = WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
        webView.settings.javaScriptEnabled = true
        webView.evaluateJavascript(
            "(function() { return ('<html><body>'+document.getElementsByTagName('html')[0].innerHTML+'</body></html>'); })();"
        ) { value -> println(value) }
        webView
    }, update = {
        it.loadUrl(mUrl)
    })

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        FormView("Hello, Android!")
    }
}

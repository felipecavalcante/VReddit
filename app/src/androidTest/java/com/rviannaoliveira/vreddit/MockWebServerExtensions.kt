package com.rviannaoliveira.vreddit

import android.content.Context
import okhttp3.mockwebserver.MockWebServer
import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Criado por rodrigo on 22/10/17.
 */
fun MockWebServer.initMockServer() {
    this.start()
    this.url("/r/redditMock/")
    AppApplication.URL = this.url("/r/redditMock/")
}

fun MockWebServer.readFileFromAssets(cx: Context, fileName: String): String {
    val builder = StringBuilder()
    try {
        val stream = cx.assets.open(fileName)
        val bReader = BufferedReader(InputStreamReader(stream, "UTF-8"))
        var line = bReader.readLine()

        while (line != null) {
            builder.append(line)
            line = bReader.readLine()
        }
    } catch (e: IOException) {
        Timber.e(e)
    }

    return builder.toString().substring(0)
}
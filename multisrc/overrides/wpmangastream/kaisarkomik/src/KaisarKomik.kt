package eu.kanade.tachiyomi.extension.id.kaisarkomik

import eu.kanade.tachiyomi.lib.ratelimit.RateLimitInterceptor
import eu.kanade.tachiyomi.multisrc.wpmangastream.WPMangaStream
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class KaisarKomik : WPMangaStream("Kaisar Komik", "https://kaisarkomik.com", "id") {
    private val rateLimitInterceptor = RateLimitInterceptor(4)

    override val client: OkHttpClient = network.cloudflareClient.newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addNetworkInterceptor(rateLimitInterceptor)
        .build()

    override val hasProjectPage = true
}

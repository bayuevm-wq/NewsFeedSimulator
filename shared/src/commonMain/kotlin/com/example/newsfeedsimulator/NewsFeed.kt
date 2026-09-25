package com.example.newsfeedsimulator

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

// Data class untuk menyimpan data berita
data class News(
    val id: Int,
    val title: String,
    val category: String
)

// Flow untuk menghasilkan berita baru setiap 2 detik
fun newsFlow(): Flow<News> = flow {

    val newsList = listOf(
        News(
            1,
            "Kotlin Multiplatform Semakin Populer",
            "Technology"
        ),
        News(
            2,
            "Timnas Indonesia Menang",
            "Sports"
        ),
        News(
            3,
            "Perkembangan Artificial Intelligence",
            "Technology"
        ),
        News(
            4,
            "Tips Menjaga Kesehatan",
            "Health"
        ),
        News(
            5,
            "Android Studio Terbaru",
            "Technology"
        ),
        News(
            6,
            "Hasil Pertandingan Hari Ini",
            "Sports"
        ),
        News(
            7,
            "Teknologi Smartphone Masa Kini",
            "Technology"
        ),
        News(
            8,
            "Tips Hidup Sehat",
            "Health"
        )
    )

    // Menghasilkan berita setiap 2 detik
    for (news in newsList) {
        delay(2000)
        emit(news)
    }
}

// StateFlow untuk menyimpan jumlah berita yang sudah dibaca
class NewsManager {

    private val _readNewsCount =
        MutableStateFlow(0)

    // StateFlow read-only
    val readNewsCount: StateFlow<Int> =
        _readNewsCount.asStateFlow()

    // Menambah jumlah berita yang sudah dibaca
    fun incrementReadCount() {
        _readNewsCount.value++
    }

    // Reset jumlah berita
    fun resetReadCount() {
        _readNewsCount.value = 0
    }
}

// Mengambil detail berita secara asynchronous
suspend fun fetchNewsDetail(news: News): String {

    // Simulasi network request
    delay(1000)

    return "Berita ${news.id}: ${news.title}"
}
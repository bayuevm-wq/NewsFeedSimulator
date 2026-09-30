package com.example.newsfeedsimulator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * App() - Entry point utama aplikasi.
 *
 * Ganti pemanggilan di bawah ini untuk beralih antar halaman:
 * - ProfileApp()   -> Menampilkan My Profile App (Tugas Praktikum Minggu 3)
 * - NewsFeedApp()  -> Menampilkan News Feed Simulator
 */
@Composable
fun App() {
    // === GANTI DI SINI UNTUK BERALIH HALAMAN ===
    ProfileApp()      // <- My Profile App (aktif sekarang)
    // NewsFeedApp()  // <- News Feed Simulator (uncomment untuk mengaktifkan)
}

/**
 * NewsFeedApp() - Halaman News Feed Simulator (kode asli)
 */
@Composable
fun NewsFeedApp() {

    val newsManager = remember {
        NewsManager()
    }

    val readCount by newsManager.readNewsCount
        .collectAsState()

    var selectedCategory by remember {
        mutableStateOf("Technology")
    }

    var currentNews by remember {
        mutableStateOf<News?>(null)
    }

    var newsDetail by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    // Flow: filter -> map -> onEach -> collect
    LaunchedEffect(selectedCategory) {

        newsFlow()
            .filter { news ->
                news.category == selectedCategory
            }
            .map { news ->
                news.copy(
                    title = news.title.uppercase()
                )
            }
            .onEach { news ->
                currentNews = news
                isLoading = true
            }
            .collect { news ->

                val detailDeferred = async {
                    fetchNewsDetail(news)
                }

                newsDetail = detailDeferred.await()

                newsManager.incrementReadCount()

                isLoading = false
            }
    }

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // =========================
                // HEADER
                // =========================

                Text(
                    text = "📰 News Feed",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Berita terbaru untuk kamu",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                // =========================
                // FILTER CARD
                // =========================

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Filter Berita",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {

                            CategoryButton(
                                text = "Technology",
                                selected =
                                    selectedCategory == "Technology",
                                onClick = {
                                    selectedCategory = "Technology"
                                }
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            CategoryButton(
                                text = "Sports",
                                selected =
                                    selectedCategory == "Sports",
                                onClick = {
                                    selectedCategory = "Sports"
                                }
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            CategoryButton(
                                text = "Health",
                                selected =
                                    selectedCategory == "Health",
                                onClick = {
                                    selectedCategory = "Health"
                                }
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // =========================
                // READ COUNT
                // =========================

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.primaryContainer
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement =
                            Arrangement.SpaceBetween,
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Column {

                            Text(
                                text = "Berita Dibaca",
                                style =
                                    MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = "$readCount berita",
                                style =
                                    MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = selectedCategory,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // =========================
                // NEWS CARD
                // =========================

                currentNews?.let { news ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = news.category.uppercase(),
                                style =
                                    MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Text(
                                text = news.title,
                                style =
                                    MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Text(
                                text = "ID Berita: ${news.id}",
                                style =
                                    MaterialTheme.typography.bodySmall
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        MaterialTheme
                                            .colorScheme
                                            .surfaceVariant,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .padding(14.dp)
                            ) {

                                if (isLoading) {

                                    Text(
                                        text =
                                            "Mengambil detail berita..."
                                    )

                                } else {

                                    Text(
                                        text = newsDetail,
                                        style =
                                            MaterialTheme
                                                .typography
                                                .bodyMedium
                                    )
                                }
                            }
                        }
                    }

                } ?: run {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp)
                    ) {

                        Text(
                            text = "Menunggu berita...",
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Berita diperbarui setiap 2 detik",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun CategoryButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (selected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.surfaceVariant,

            contentColor =
                if (selected)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {

        Text(
            text = text,
            fontWeight =
                if (selected)
                    FontWeight.Bold
                else
                    FontWeight.Normal
        )
    }
}
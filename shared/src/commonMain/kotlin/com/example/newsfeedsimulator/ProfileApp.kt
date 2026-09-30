package com.example.newsfeedsimulator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import newsfeedsimulator.shared.generated.resources.Res
import newsfeedsimulator.shared.generated.resources.profile_photo
import org.jetbrains.compose.resources.painterResource

// =====================================================
// My Profile App - Tugas Praktikum Minggu 3
// Pengembangan Aplikasi Mobile - Compose Multiplatform
// =====================================================

/**
 * Composable reusable #1: ProfileHeader
 *
 * Menampilkan bagian header profile yang berisi:
 * - Foto/Icon profile berbentuk circular (menggunakan Box + Text)
 * - Nama pengguna (bold, ukuran besar)
 * - Status/pekerjaan
 *
 * Komponen yang digunakan: Column, Box, Text
 * Modifier: fillMaxWidth(), padding(), size(), clip(), background()
 */
@Composable
fun ProfileHeader(
    name: String,
    status: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto profile circular menggunakan Box + Image
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(Res.drawable.profile_photo),
                contentDescription = "Foto Profile",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nama pengguna - lebih besar dan bold
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Status/pekerjaan
        Text(
            text = status,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Composable reusable #2: InfoItem
 *
 * Menampilkan satu baris informasi kontak dengan icon (emoji) dan teks.
 * Menggunakan Row untuk layout horizontal.
 *
 * Komponen yang digunakan: Row, Box, Column, Text
 * Modifier: fillMaxWidth(), padding(), size(), clip(), background()
 */
@Composable
fun InfoItem(
    icon: String,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon informasi menggunakan Box + emoji
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = icon,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Label dan value
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

/**
 * Composable reusable #3: ProfileCard
 *
 * Card reusable untuk menampilkan section dengan judul dan konten.
 * Digunakan untuk "About Me" dan "Contact Information".
 *
 * Komponen yang digunakan: Card, Column, Text
 * Modifier: fillMaxWidth(), padding()
 */
@Composable
fun ProfileCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Judul card
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Konten card (flexible)
            content()
        }
    }
}

/**
 * ProfileApp() - Halaman utama My Profile App
 *
 * Memanggil semua reusable composable:
 * - ProfileHeader() -> header dengan foto, nama, dan status
 * - ProfileCard()   -> digunakan 2 kali (About Me & Contact Information)
 * - InfoItem()      -> digunakan 3 kali (Email, Phone, Location)
 *
 * Komponen yang digunakan: Column, Row, Box, Card, Text, Button, Icon (emoji)
 * Layout utama menggunakan Column dengan verticalScroll
 */
@Composable
fun ProfileApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // =========================
                // TITLE APP
                // =========================
                Text(
                    text = "My Profile App",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                // =========================
                // PROFILE HEADER
                // (menggunakan composable ProfileHeader)
                // =========================
                ProfileHeader(
                    name = "Bayu Setiawan",
                    status = "Informatics Student"
                )

                // =========================
                // ABOUT ME CARD
                // (menggunakan composable ProfileCard)
                // =========================
                ProfileCard(title = "About Me") {
                    Text(
                        text = "Saya adalah mahasiswa Teknik Informatika yang tertarik pada pengembangan aplikasi mobile.",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // =========================
                // CONTACT INFORMATION CARD
                // (menggunakan composable ProfileCard + InfoItem)
                // =========================
                ProfileCard(title = "Contact Information") {
                    // Email - menggunakan composable InfoItem
                    InfoItem(
                        icon = "📧",
                        label = "Email",
                        value = "bayu.evm@gmail.com"
                    )

                    // Phone - menggunakan composable InfoItem
                    InfoItem(
                        icon = "📱",
                        label = "Phone",
                        value = "0895392463781"
                    )

                    // Location - menggunakan composable InfoItem
                    InfoItem(
                        icon = "📍",
                        label = "Location",
                        value = "Lampung, Indonesia"
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // =========================
                // CONTACT ME BUTTON
                // =========================
                Button(
                    onClick = { /* Aksi tombol Contact Me */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Contact Me",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

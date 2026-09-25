# News Feed Simulator

Aplikasi News Feed Simulator yang dibuat menggunakan Kotlin Multiplatform dan Compose Multiplatform.

## Deskripsi

News Feed Simulator merupakan aplikasi sederhana yang mensimulasikan penerimaan berita secara berkala.

Aplikasi menampilkan berita berdasarkan beberapa kategori dan memungkinkan pengguna memilih kategori berita yang ingin ditampilkan.

Kategori yang tersedia:

- Technology
- Sports
- Health

## Fitur

Aplikasi menerapkan beberapa konsep Kotlin Coroutines dan Flow, yaitu:

- **Flow** untuk menghasilkan berita secara berkala.
- **Filter** untuk memfilter berita berdasarkan kategori.
- **Map** untuk melakukan transformasi pada data berita.
- **onEach** untuk menjalankan proses ketika data berita diterima.
- **Collect** untuk menerima dan memproses data dari Flow.
- **StateFlow** untuk menyimpan jumlah berita yang telah dibaca.
- **Async/Await** untuk mengambil detail berita secara asynchronous.

## Teknologi yang Digunakan

- Kotlin
- Kotlin Multiplatform
- Compose Multiplatform
- Kotlin Coroutines
- Flow
- StateFlow
- Android Studio

## Cara Menjalankan Aplikasi

1. Buka project `NewsFeedSimulator` menggunakan Android Studio.
2. Pastikan emulator Android sudah tersedia.
3. Pilih perangkat Android yang digunakan, misalnya **Pixel 8 API 34**.
4. Jalankan aplikasi menggunakan tombol **Run ▶**.
5. Aplikasi akan tampil pada emulator.

## Penggunaan Aplikasi

Setelah aplikasi dijalankan:

1. Aplikasi akan menampilkan halaman **News Feed Simulator**.
2. Pilih kategori berita yang tersedia:
   - Technology
   - Sports
   - Health
3. Berita akan ditampilkan sesuai kategori yang dipilih.
4. Jumlah berita yang telah dibaca akan ditampilkan pada aplikasi.
5. Detail berita diproses secara asynchronous.

## Struktur Project

Struktur project mengikuti struktur bawaan project Kotlin Multiplatform yang digunakan pada aplikasi ini.

Bagian utama project terdiri dari:

- `androidApp` — bagian aplikasi untuk platform Android.
- `iosApp` — bagian aplikasi untuk platform iOS.
- `shared` — bagian kode yang digunakan bersama oleh platform.
- `commonMain` — tempat kode Kotlin Multiplatform yang digunakan bersama.

## Pengujian

Beberapa fitur yang telah diuji pada aplikasi:

- Menjalankan aplikasi pada emulator Android.
- Menampilkan berita secara berkala.
- Memilih kategori **Technology**.
- Memilih kategori **Sports**.
- Memilih kategori **Health**.
- Menampilkan jumlah berita yang telah dibaca.
- Mengambil detail berita menggunakan proses asynchronous.

## Repository

Project ini disimpan pada GitHub:

https://github.com/bayuevm-wq/NewsFeedSimulator

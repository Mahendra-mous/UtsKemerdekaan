package com.example.tugasuts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugasuts.ui.theme.TugasutsTheme

// Kelas data untuk rincian hadiah
data class Hadiah(
    val kategori: String,
    val namaHadiah: String,
    val imageResId: Int
)

// Data untuk daftar hadiah
val hadiahList = listOf(
    Hadiah("Juara 1", "Televisi LED 32 Inch", R.drawable.tv),
    Hadiah("Juara 1", "Sepeda Gunung", R.drawable.sepeda),
    Hadiah("Juara 2", "Smartphone", R.drawable.smartphone),
    Hadiah("Juara 2", "Voucher Belanja 500K", R.drawable.voucher),
    Hadiah("Juara 3", "Jam Tangan", R.drawable.jam_tangan),
    Hadiah("Juara 3", "Headphone", R.drawable.headphone)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Mengatur tampilan konten aplikasi
        setContent {
            TugasutsTheme{ // Menerapkan tema aplikasi
                MainScreen() // Memanggil komposisi layar utama
            }
        }
    }
}

// Fungsi komposisi utama untuk aplikasi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Hadiah Kemerdekaan") }) //app bar untuk judul
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Layout() //memanggil fungsi Layout
            Image() //Menampilkan Gambar
            Input() //Memanggil fungsi input nama pengguna
            ScrollableList(hadiahList) //Memanggil dan menampilkan daftar hadiah yang dapat discroll
            Grid(hadiahList) //menampilkan tata letak grid
        }
    }
}

// Fungsi komposisi untuk menangani tata letak
@Composable
fun Layout() {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        // Tata letak untuk orientasi landscape
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray)
                    .height(100.dp)
            ) {
                Text("Landscape Mode", Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.DarkGray)
                    .height(100.dp)
            ) {
                Text("Bagian Kanan", Modifier.align(Alignment.Center))
            }
        }
    } else {
        // Tata letak untuk orientasi potrait
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.LightGray)
            ) {
                Text("Portrait Mode", Modifier.align(Alignment.Center))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.DarkGray)
            ) {
                Text("Bagian Bawah", Modifier.align(Alignment.Center))
            }
        }
    }
}

// Fungsi komposisi untuk menampilkan gambar sambutan
@Composable
fun Image() {
    Text("Selamat Datang!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))
    Image(
        painter = painterResource(id = R.drawable.merdeka), // Gambar logo kemerdekaan
        contentDescription = "Logo Kemerdekaan",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

// Fungsi komposisi untuk input nama pengguna
@Composable
fun Input() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it }, // Memperbarui teks saat diubah
            label = { Text("Masukkan Nama Anda") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* Handle action */ }) {
            Text("Kirim")
        }
    }
}

// Fungsi komposisi untuk menampilkan daftar hadiah yang dapat discroll
@Composable
fun ScrollableList(hadiahList: List<Hadiah>) {
    LazyColumn {
        items(hadiahList) { hadiah ->
            HadiahItem(hadiah) // Memanggil komposisi item hadiah
        }
    }
}

// Fungsi komposisi untuk menampilkan setiap item hadiah
@Composable
fun HadiahItem(hadiah: Hadiah) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = hadiah.imageResId), // Menampilkan gambar hadiah
                contentDescription = hadiah.namaHadiah,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = hadiah.kategori,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = hadiah.namaHadiah,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Fungsi komposisi untuk menampilkan tata letak grid hadiah
@Composable
fun Grid(hadiahList: List<Hadiah>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Dua kolom di grid
        modifier = Modifier.padding(8.dp)
    ) {
        items(hadiahList) { hadiah ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = hadiah.imageResId),  // Menampilkan gambar hadiah
                        contentDescription = hadiah.namaHadiah,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(bottom = 8.dp)
                    )
                    Text(text = "${hadiah.kategori}: ${hadiah.namaHadiah}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

// Fungsi untuk preview tampilan layar utama
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    TugasutsTheme {  // Menerapkan tema aplikasi untuk pratinjau
        MainScreen()
    }
}

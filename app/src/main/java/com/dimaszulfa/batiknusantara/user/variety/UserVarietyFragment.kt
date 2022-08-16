package com.dimaszulfa.batiknusantara.user.variety

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimaszulfa.batiknusantara.R
import com.dimaszulfa.batiknusantara.data.VarietyEntity
import com.dimaszulfa.batiknusantara.databinding.FragmentHomeBinding
import com.dimaszulfa.batiknusantara.databinding.FragmentUserVarietyBinding



private var _binding: FragmentUserVarietyBinding? = null
val binding get() = _binding!!
    private var data = arrayListOf(
        VarietyEntity("Batik Tulis/Canting", "Teknik pembuatan batik tulis atau canting adalah metode paling tua dan tradisional. Proses pembuatan batik masih menggunakan alat canting tradisional yang diisi dengan lilin panas sebelum digunakan untuk menggambar pola di atas kain. Setelah pola gambar ditutupi lilin, kemudian kain diwarnai. Bagian lilin kemudian dilepaskan dari kain. Dengan begitu, saat kain dimasukan dalam larutan pewarna, bagian yang tertutup lilin tidak terkena warna dan membentuk motif batik yang cantik. Teknik pembuatan batik dengan metode canting membutuhkan ketelitian tinggi. Tekstur dan motif batik dibuat manual menggunakan tangan. Tidak heran pembuatan batik dengan canting bisa memakan waktu 2 – 3 bulan. Meski begitu, harga batik tulis jauh lebih mahal dibanding batik biasa karena punya nilai seni tinggi.",false),
        VarietyEntity("Batik Cap", "Teknik pembuatan batik cap muncul sekitar abad ke-20. Metode ini tidak menggunakan canting, melainkan cap yang terbuat dari tembaga berukuran 20 x 20 cm. Bagian tengah cap memiliki motif ukiran batik. Stempel akan dicelupkan ke dalam cairan malam lalu ditekan dengan keras di atas kain. Proses pembuatan batik dengan metode cap tergolong modern. Cara pembuatannnya sama seperti saat kita menggunakan stempel. Kelebihan dari metode ini adalah membuat proses pengerjaan batik lebih cepat. Proses pembuatan batik cap hanya memakan waktu 2 – 3 hari tergantung luas kain.",false),
        VarietyEntity("Batik Kombinasi", "Batik kombinasi adalah perpaduan antara batik tulis (canting) dengan batik cap. Teknik pembuatan batik ini diciptakan untuk menyempurnakan hasil batik cap yang hanya bisa membuat motif besar. Detail motif yang ukurannya lebih kecil kemudian ditambahkan menggunakan canting. Meskipun menggunakan canting, namun kualitas batik kombinasi masih setara dengan batik cap. Karena canting hanya digunakan untuk menambah motif tertentu saja. Keseluruhan proses pembuatan lebih banyak menggunakan cap tembaga. Waktu pengerjaan batik kombinasi sedikit lebih lama dari batik cap karena butuh waktu tambahan untuk menambah detail motif dengan canting.",false),
        VarietyEntity("Batik Ikat Celup (Tie-Dye)", "Teknik pembuatan batik dengan metode ikat celup juga tergolong modern. Teknik ini banyak digunakan untuk membuat batik yang lebih berwarna-warni. Di Jawa teknik ini disebut Jumputan, di Palembang lebih dikenal dengan nama Cinde, sedangkan di Banjarmasin namanya Sasirangan. Sebelum dicelup ke cairan pewarna, sebagaian kain diikat dengan tali. Setelah semua bagian kain tercelup kemudian angkat kain. Buka ikatan kain dan pastikan bagian yang terikat tidak terkena pewarna. Hasil atau motif batik ikat celup mirip kaos tie dye yang saat ini sedang tren di kalangan anak muda.",false),
        VarietyEntity("Batik Lukis/Colet", "Teknik pembuatan batik tradisional hanya menghasilkan 1 – 2 warna saja. Namun berbeda dengan teknik pembuatan batik lukis atau colet. Dengan teknik ini, Anda bisa membuat batik beraneka warna. Teknik ini juga membutuhkan keterampilan seni yang tinggi. Semakin bagus hasilnya, maka semakin mahal harganya. Sebelum dilukis, kain polos akan diberi motif agar tetap memiliki ciri khas batiknya. Setelah itu pengrajin akan memberi warna pada motif atau pola gambar tersebut dengan kuas cat. Semakin bagus perpaduan warnanya akan semakin bagus hasilnya.",false),
        VarietyEntity("Batik Printing", "Metode pembuatan batik printing adalah teknik yang paling modern dan paling banyak digunakan saat ini. Selain lebih cepat, proses pembuatan batik printing tidak membutuhkan keterampilan khusus. Oleh karena itu, teknik ini paling banyak digunakan oleh pemula untuk membuat banyak batik dalam waktu singkat. Untuk membuat batik printing, Anda hanya membutuhkan komputer, software dan kemampuan untuk membuat motif batik, dan mesin printing kain. Motif yang sudah dibuat di komputer kemudian dicetak di atas kain. Karena nilai seninya lebih rendah, harga kain batik printing lebih murah. Waktu pengerjaan batik printing paling cepat dibanding teknik pembuatan batik lainnya. Ada 6 teknik pembuatan batik yang paling banyak digunakan saat ini. Setiap teknik punya kelebihannya masing-masing. Sebagai contoh, batik tulis atau canting dapat menghasilkan batik yang harganya paling mahal namun dari proses pengerjaan memakan waktu paling lama. Di sisi lain, batik printing bisa menghasilkan banyak batik dalam waktu singkat. Akan tetapi harganya jauh lebih murah dibanding teknik pembuatan batik lainnya. Jika Anda ingin membeli batik berkualitas segera kunjungi koleksi Batik Pria dan Batik Wanita terbaik di Bhinneka.",false)
    )
class UserVarietyFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserVarietyBinding.inflate(layoutInflater)
        binding.rvVariety.adapter = UserVarietyAdapter(data)
        Log.d("TAG", data.toString())
        binding.rvVariety.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        binding.rvVariety.setHasFixedSize(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
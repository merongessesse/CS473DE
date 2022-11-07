package edu.miu.walmartlogin.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.miu.walmartlogin.R
import edu.miu.walmartlogin.adapter.CategoryAdapter
import edu.miu.walmartlogin.data.Product
import edu.miu.walmartlogin.databinding.ActivityElectronicsItemBinding

class ElectronicsCategoryActivity : AppCompatActivity(), CategoryAdapter.Callback {

    private lateinit var binding: ActivityElectronicsItemBinding
    private val productList = mutableListOf(
        Product("ASUS ROG Strix G15 Advantage Edition (2021) 15.6 \" Laptop", "$1235", "Black", R.drawable.laptop, 1111L, "Amp up your entire Windows 10 gaming experience with the slick and sporty new ROG Strix G15 Advantage Edition. Double up on AMD firepower with up to a powerful Ryzen™ 9 5900HX CPU and Radeon™ RX 6800M GPU. AMD SmartShift and Smart Access Memory tech help the processors work better together to boost performance in a range of tasks."),
        Product("RCA 85\" SMART 4K UHD webOS TV", "$1135", "Black", R.drawable.tv, 1222L, "THIS RCA 75\" TV IS POWERED BY A WEBOS SMART PLATFORM DELIVERS STREAMING SIMPLICITY. FEATURING THE LATEST STREAMING APPS LIKE DISNEY+ AND THE APPLE TV APP PLUS POPULAR APPS LIKE NETFLIX, HULU, SLING, PRIME VIDEO, YOUTUBE AND MORE. IT'S SIMPLE TO FIND TOP SHOWS, UP-AND-COMERS AND GET PERSONALIZED CONTENT RECOMMENDATIONS. FEATURING 4K ULTRA HD RESOLUTION PICTURE RESOLUTION AND EASY INTERNET ACCESS THROUGH WI-FI."),
        Product("Apple iPad 10.2\" 32GB with Wi-Fi ", "$350", "Space Grey", R.drawable.tablet, 1333L, "The product's specs are as follows:\n" +
                "10.2-inch Retina display\n" +
                "•With incredible detail and vivid colours, the gorgeous 10.2\" Retina display is perfect for watching a movie, working on a project, and drawing your next masterpiece"),
        Product("Plustek 25 ppm SmartOffice PS283 Scanner", "$570", "Black", R.drawable.scanner, 1444L, "The Plustek 25 ppm SmartOffice PS283 offers you a flexible and productive way to scan and manage all of your paper based information.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElectronicsItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CategoryAdapter(this, productList)
        adapter.setCallback(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClicked(product: Product?) {
        val intent = Intent(this, ElectronicsDetailActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}
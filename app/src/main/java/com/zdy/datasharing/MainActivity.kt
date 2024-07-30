package com.zdy.datasharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.zdy.datasharing.Menu.SharePhotoFragment
import com.zdy.datasharing.Menu.ShareTextFragment
import com.zdy.datasharing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bNav.bNav.selectedItemId = R.id.shareText
        SelectMenu(R.id.shareText)

        binding.bNav.bNav.setOnItemSelectedListener {
            SelectMenu(it.itemId)
            true
        }


    }

    private fun SelectMenu(itemID: Int){
        when(itemID){

            R.id.shareText ->{
                SetFragment(ShareTextFragment.newInstance())
            }

            R.id.sharePhoto ->{
                SetFragment(SharePhotoFragment.newInstance())
            }

        }
    }

    private fun SetFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,fragment).commit()

    }



}
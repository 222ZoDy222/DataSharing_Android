package com.zdy.datasharing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindButton()

    }


    private fun bindButton(){
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener(::ShareEditTextData)

    }

    private fun ShareEditTextData(view: View){

        val editText = findViewById<EditText>(R.id.editText)
        val textToShare = editText.text

        if(textToShare.isNullOrEmpty()) return

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,textToShare)
            type = "text/plain"
        }

        var shareIntent = Intent.createChooser(sendIntent,null)
        startActivity(shareIntent)

    }

}
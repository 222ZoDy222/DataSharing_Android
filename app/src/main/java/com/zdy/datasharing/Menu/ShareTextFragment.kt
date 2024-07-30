package com.zdy.datasharing.Menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.zdy.datasharing.R


class ShareTextFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_share_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindButton()
    }

    private fun bindButton(){
        val btn = view?.findViewById<Button>(R.id.button)
        val editText = view?.findViewById<EditText>(R.id.editText)
        btn?.setOnClickListener{
            view?.let { ShareEditTextData(it) }
        }

    }

    private fun ShareEditTextData(view: View){

        val editText = view.findViewById<EditText>(R.id.editText)
        val textToShare = editText?.text

        if(textToShare.isNullOrEmpty()) return

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,textToShare)
            type = "text/*"
        }

        var shareIntent = Intent.createChooser(sendIntent,null)
        startActivity(shareIntent)

    }

    companion object {

        @JvmStatic
        fun newInstance(): ShareTextFragment {
            return ShareTextFragment()
        }

    }
}
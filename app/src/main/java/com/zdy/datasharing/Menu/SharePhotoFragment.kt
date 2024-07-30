package com.zdy.datasharing.Menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.zdy.datasharing.R
import com.zdy.datasharing.databinding.FragmentSharePhotoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SharePhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SharePhotoFragment : Fragment() {

    lateinit var binding: FragmentSharePhotoBinding

    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){

        PhotoUri = it

    }


    private var m_photoUri: Uri? = null

    public var PhotoUri: Uri?
        get(){
            return  m_photoUri
        }
        set(value){
            m_photoUri = value
            m_photoUri?.let {
                try {
                    binding.imageButton.setImageURI(m_photoUri)
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSharePhotoBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindButtons()

    }

    private fun bindButtons(){


        binding.imageButton.setOnClickListener{
            PickPhoto()
        }

        binding.button2.setOnClickListener{
            SharePhoto()
        }
    }



    private fun PickPhoto(){
        galleryLauncher.launch("image/*")
    }

    private fun SharePhoto(){

        PhotoUri?.let {

            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM,it)
                type="image/*"
            }
            startActivity(shareIntent)

        }

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SharePhotoFragment()
    }
}
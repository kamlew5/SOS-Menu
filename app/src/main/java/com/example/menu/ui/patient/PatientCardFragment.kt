package com.example.menu.ui.patient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.menu.R
class PatientCardFragment : Fragment() {
    private lateinit var galleryViewModel: GalleryViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_patient_card, container, false)
        println(ViewModelProvider(this).get(GalleryViewModel::class.java).getText())
        val url = "https://sos.kml.net.pl/api/patient?id=1"
        return root
    }
}
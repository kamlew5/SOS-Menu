package com.example.menu.ui.patient

import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.menu.R
import java.util.zip.Inflater
import kotlin.properties.Delegates

var changeText by Delegates.notNull<Int>()

class GalleryViewModel : ViewModel() {
    private val _text = MutableLiveData<Int>().apply {
        value = changeText
    }
    val text: LiveData<Int> = _text
    fun getText()
    : String {
        return "String"
    }
}
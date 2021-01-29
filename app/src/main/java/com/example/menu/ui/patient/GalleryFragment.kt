package com.example.menu.ui.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.PatientAdapter
import com.example.menu.R
import com.example.menu.network.OkHttpRequest
import com.example.menu.serializers.PatientList
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONException
import java.io.IOException


class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var gsonPatientList: PatientList

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        println("działa1")
        val url = "https://sos.kml.net.pl/api/patient?id=all"
        getDetails(url)
        return root
    }

    private fun showPatients() {
        val recyclerPatient: RecyclerView = requireView().findViewById(R.id.recyclerPatient)
        recyclerPatient.layoutManager = LinearLayoutManager(parentFragment?.context)
        recyclerPatient.adapter = PatientAdapter(gsonPatientList)
    }
    private fun getDetails(url:String) {
        val client = OkHttpClient()
        val request= OkHttpRequest(client)

        request.GET(url, object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Activity Failure.")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                activity?.runOnUiThread{
                    try {
                        gsonPatientList = GsonBuilder().create().fromJson(responseData, PatientList::class.java)
                        println(gsonPatientList)
                        this@GalleryFragment.showPatients()

                    } catch (e: JSONException) {
                        e.printStackTrace()

                    }
                }
            }

        })
    }
}
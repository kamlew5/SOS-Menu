package com.example.menu.ui.patient

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.*
import com.example.menu.network.OkHttpRequest
import com.example.menu.serializers.PatientList
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONException
import java.io.IOException
import java.lang.NullPointerException


class PatientFragment : Fragment(){

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
        println(ViewModelProvider(this).get(GalleryViewModel::class.java).getText())
        val url = "https://sos.kml.net.pl/api/patient?id=all"
        ViewModelProvider(this).get(GalleryViewModel::class.java).text.observe(viewLifecycleOwner,
            Observer {
           try {
               change()
           }
           catch (e: NullPointerException){}
        })
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
                        this@PatientFragment.showPatients()

                    } catch (e: JSONException) {
                        e.printStackTrace()

                    }
                }
            }

        })
    }
    fun change(){
        val transaction: FragmentTransaction? =
            fragmentManager?.beginTransaction()
        transaction?.replace(R.id.nav_host_fragment_container, PatientCardFragment())?.commit()
    }
}
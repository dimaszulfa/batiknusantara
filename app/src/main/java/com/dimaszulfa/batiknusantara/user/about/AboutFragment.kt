package com.dimaszulfa.batiknusantara.user.about

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dimaszulfa.batiknusantara.databinding.FragmentAboutBinding
import com.dimaszulfa.batiknusantara.util.TimerService
import kotlin.math.roundToInt


class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding ?= null
    val binding get() = _binding!!
    private var timeStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private var currentTime = 0L
    private var closeTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentTime = System.currentTimeMillis()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAboutBinding.inflate(layoutInflater)
        serviceIntent=  Intent(requireContext(), TimerService::class.java)
        activity?.registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnKmeans.setOnClickListener {
            startStopTimer()
        }

        binding.txAbout.text = currentTime.toString()


    }

    private fun startStopTimer()
    {
        if(timeStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer(){
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        activity?.startService(serviceIntent)
        binding.btnKmeans.text = "Stop"
        timeStarted = true
    }

    private fun stopTimer()
    {
        activity?.stopService(serviceIntent)
        binding.btnKmeans.text = "Start"
        timeStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            Toast.makeText(requireContext(), getTimeStringFromDouble(time), Toast.LENGTH_SHORT).show()
        }

    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String = String.format("%02d:%02d:%02d", hour, min, sec)



    private fun kMeans(
        elements: Array<Int>,
        numIterations: Int,
        numClusters: Int
    ): MutableList<MutableList<Int>> {

        // Setup: create K clusters with random elements from input
        val clusters = mutableListOf<MutableList<Int>>()
        val centroids = Array(numClusters) { 0 }
        for (i in 0 until numClusters) {
            clusters.add(mutableListOf(elements[i]))
        }

        // Algorithm
        for (i in 0 until numIterations) {
            Log.d("LOG", "Iteration $i")
            for (j in 0 until numClusters) {
                // Find centroids
                centroids[j] = findCentroid(clusters[j], centroids[j])
                Log.d("LOG", "Centroid $j: ${centroids[j]}")
            }
            clusters.forEach{ list ->
                list.removeAll {
                    true
                }
            }
            for (element in elements) {
                // Assign elements to the best cluster
                val index = assignToCluster(element, centroids)
                clusters[index].add(element)
                Log.d("LOG", "Element: $element assigned to the $index cluster")
            }

        }
        Log.d("LOGHASIL", clusters.toString())

        return clusters
    }

    // Find the centroid of a cluster
    fun findCentroid(cluster: MutableList<Int>?, actualCentroid: Int): Int {
        var newCentroid = 0
        cluster?.forEach {
            newCentroid = newCentroid.plus(it)
        }
        return newCentroid / cluster!!.size
    }

    // Returns the index of the cluster that this element must be assigned to
    fun assignToCluster(element: Int, centroids: Array<Int>): Int {
        var minValue = 0
        var minIndex = 0
        for (i in centroids.indices) {
            if (kotlin.math.abs(element - centroids[i]) < kotlin.math.abs(element - minValue)) {
                minIndex = i
                minValue = centroids[i]
            }
        }
        // Centroids and clusters are in sync
        return minIndex
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        closeTime = System.currentTimeMillis()
        var hasil = closeTime - currentTime
        Toast.makeText(requireContext(), "BYE", Toast.LENGTH_LONG).show()
        Log.d("Hasil", hasil.toString())
    }
}
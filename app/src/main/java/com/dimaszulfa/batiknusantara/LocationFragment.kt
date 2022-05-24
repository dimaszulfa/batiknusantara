package com.dimaszulfa.batiknusantara

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dimaszulfa.batiknusantara.databinding.FragmentLocationBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.plugin.locationcomponent.location


class LocationFragment : Fragment() {


    private lateinit var binding: FragmentLocationBinding
    lateinit var permissionsManager: PermissionsManager
    private val callback = LocationListeningCallback(MainActivity())

    // Get the user's location as coordinates

    private val onIndicatorBearingChangedListener = OnIndicatorBearingChangedListener {
        binding.mapView.getMapboxMap().setCamera(CameraOptions.Builder().bearing(it).build())
    }

    // Create an instance of the Annotation API and get the CircleAnnotationManager.


    private fun addMarkers() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.clothing_shop)



// Create an instance of the Annotation API and get the PointAnnotationManager.
        val annotationApi = binding.mapView.annotations
        val pointAnnotationManager = annotationApi.createPointAnnotationManager()
// Set options for the resulting symbol layer.



        val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
            // Define a geographic coordinate.
            .withPoint(Point.fromLngLat(23.23, 59.37))
            .withPoint(Point.fromLngLat(30.23, 53.37))
            // Specify the bitmap you assigned to the point annotation
            // The bitmap will be added to map style automatically.
            .withIconImage(bitmap)



// Add the resulting pointAnnotation to the map.
        pointAnnotationManager.create(pointAnnotationOptions)

    }

    private val onMoveListener = object : OnMoveListener {
        override fun onMoveBegin(detector: MoveGestureDetector) {
            onCameraTrackingDismissed()
        }

        override fun onMove(detector: MoveGestureDetector): Boolean {
            return false
        }

        override fun onMoveEnd(detector: MoveGestureDetector) {}
    }


    private val onIndicatorPositionChangedListener = OnIndicatorPositionChangedListener {
        binding.mapView.getMapboxMap().setCamera(CameraOptions.Builder().center(it).build())
        binding.mapView.gestures.focalPoint =
            binding.mapView.getMapboxMap().pixelForCoordinate(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
        binding = FragmentLocationBinding.inflate(layoutInflater, container, false)
//        binding.mapView.onCreate(savedInstanceState)
//        binding.mapView.getMapAsync {
//            it.setStyle(Style.MAPBOX_STREETS)
//        }
        binding.mapView.getMapboxMap().setCamera(
            CameraOptions.Builder()
                .zoom(14.0)
                .build()
        )
        binding.mapView.gestures.addOnMoveListener(onMoveListener)

        binding.mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS,
            object : Style.OnStyleLoaded {
                override fun onStyleLoaded(style: Style) {
                    binding.mapView.location.updateSettings {
                        enabled = true
                        pulsingEnabled = true
                        addMarkers()
                        redirectUsertoLocation()
                    }
                }
            }
        )
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {

// Permission sensitive logic called here, such as activating the Maps SDK's LocationComponent to show the device's location


// Pass the user's location to camera

            redirectUsertoLocation()

        } else {
            Toast.makeText(
                requireContext(),
                "Anda belum mengaktifkan lokasi",
                Toast.LENGTH_LONG
            ).show()
            permissionsManager = PermissionsManager(permissionsListener)
            permissionsManager.requestLocationPermissions(requireActivity())
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    var permissionsListener: PermissionsListener = object : PermissionsListener {
        override fun onExplanationNeeded(permissionsToExplain: List<String>) {

        }

        override fun onPermissionResult(granted: Boolean) {
            if (granted) {
                redirectUsertoLocation()
            } else {

                Toast.makeText(requireContext(), "Anda Menolak Izin Lokasi", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun redirectUsertoLocation() {
        binding.mapView.location.addOnIndicatorPositionChangedListener(
            onIndicatorPositionChangedListener
        )
        binding.mapView.location.addOnIndicatorBearingChangedListener(
            onIndicatorBearingChangedListener
        )
    }

    @SuppressLint("Lifecycle")
    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    @SuppressLint("Lifecycle")
    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    @SuppressLint("Lifecycle")
    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    @SuppressLint("Lifecycle")
    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    private fun onCameraTrackingDismissed() {
        Toast.makeText(requireContext(), "onCameraTrackingDismissed", Toast.LENGTH_SHORT).show()
        binding.mapView.location
            .removeOnIndicatorPositionChangedListener(onIndicatorPositionChangedListener)
        binding.mapView.location
            .removeOnIndicatorBearingChangedListener(onIndicatorBearingChangedListener)
        binding.mapView.gestures.removeOnMoveListener(onMoveListener)
    }

}



package com.pekwerike.mintbankicr

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.app.ActivityCompat
import com.pekwerike.mintbankicr.ui.screens.HomePageScreen
import com.pekwerike.mintbankicr.ui.theme.MintBankICRTheme
import com.pekwerike.mintbankicr.viewmodel.MainActivityViewModel


class MainActivity : ComponentActivity() {
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MintBankICRTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                        HomePageScreen(cameraLifecycleOwner = this, mainActivityViewModel)

                }
            }
        }
        requestCameraPermission()
    }

    // confirm if permission to use the camera has been granted by the user
    private fun isCameraPermissionGranted() = ActivityCompat
            .checkSelfPermission(this, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED

    //request for camera permission
    private fun requestCameraPermission(){
         if(isCameraPermissionGranted()){
             // start camera preview
            mainActivityViewModel.cameraPermissionGranted(true)
         }else {
             ActivityCompat.requestPermissions(this, arrayOf(CAMERA_PERMISSION),
             CAMERA_PERMISSION_REQUEST_CODE)
         }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(isCameraPermissionGranted()){
                // start camera preview
                mainActivityViewModel.cameraPermissionGranted(true)
            }else {
                // TODO manage the scenario when a user denies the permission parmenently
                requestCameraPermission()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        const val CAMERA_PERMISSION_REQUEST_CODE = 100
        const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }
}

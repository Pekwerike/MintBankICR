package com.pekwerike.mintbankicr

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pekwerike.mintbankicr.ui.screens.HomePageScreen
import com.pekwerike.mintbankicr.ui.theme.MintBankICRTheme

class MainActivityViewModel : ViewModel(){
    private var _shouldShowCameraPreview = MutableLiveData<Boolean>(false)
    val shouldShowCameraPreview : LiveData<Boolean> = _shouldShowCameraPreview

    fun cameraPermissionGranted(state : Boolean){
        _shouldShowCameraPreview.value = state
    }
}
class MainActivity : ComponentActivity() {
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MintBankICRTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (canShowCameraPreview.value) {
                        HomePageScreen(cameraLifecycleOwner = this)
                    }
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
             canShowCameraPreview.value = true
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

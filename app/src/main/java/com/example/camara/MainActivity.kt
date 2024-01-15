package com.example.camara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.camara.ui.theme.CamaraTheme

class MainActivity : AppCompatActivity() {
    private val PERMISSION_ID = 34
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOpenCamera.setOnClickListener {
            if(checkCameraPermission()){
                openCamera()
            } else{
                requestPermissions()
            }
        }
    }
    private fun openCamera(){
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions:
    Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED)) {
                openCamera()
            } else {
                Toast.makeText(this,"Aun requieres permiso",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun checkCameraPermission(): Boolean{
        return ActivityCompat
            .checkSelfPermission(this, Manifest.permission.CAMERA)==
                PackageManager.PERMISSION_GRANTED
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CamaraTheme {
        Greeting("Android")
    }
}
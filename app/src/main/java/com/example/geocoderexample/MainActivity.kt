package com.example.geocoderexample

import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geocoderexample.ui.theme.GeocoderExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GeocoderExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Geocoding()
                        Spacer(modifier = Modifier.height(20.dp))
                        ReverseGeocoding()
                    }
                }
            }
        }
    }
}

@Composable
fun Geocoding(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val geocoder = Geocoder(context)
    val locationName = "Maglegårdsvej 2, 4000 Roskilde, Denmark"
    Column(modifier = modifier) {
        Text(text = "Geocoding: Address -> (lat, long)", fontSize = 20.sp)
        Text(text = locationName)
        val addressList = geocoder.getFromLocationName(locationName, 5)
        if (addressList.isNullOrEmpty()) {
            Text(text = "No addresses")
        } else {
            for (address in addressList) {
                Text(text = "Lat: ${address.latitude} Long: ${address.longitude}")
            }
        }
    }
}

@Composable
fun ReverseGeocoding(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val geocoder = Geocoder(context)
    val latitude = 55.6305952
    val longitude = 12.0784041
    Column(modifier = modifier) {
        Text(text = "Reverse Geocoding: (lat, long) -> Address", fontSize = 20.sp)
        Text(text = "Lat: $latitude Long: $longitude")
        val addressList = geocoder.getFromLocation(latitude, longitude, 5)
        if (addressList.isNullOrEmpty()) {
            Text(text = "No addresses")
        } else {
            for (address in addressList) {
                Text(text = address.getAddressLine(0))
            }
        }
    }
}
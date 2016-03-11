package shaula.igor.google_maps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap googleMap;

    private boolean isMapReady;

    private LatLng locationNext = new LatLng(40.75, -74.0); // a very little shift \
    private LatLng locationNewYork = new LatLng(40.7484, -73.9878);

    private MarkerOptions markerOptions, markerOptionsNext;

    private LatLng l1 = new LatLng(40, -74);
    private LatLng l2 = new LatLng(41, -74);
    private LatLng l3 = new LatLng(41, -75);
    private LatLng l4 = new LatLng(40, -75);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bMap = (Button) findViewById(R.id.bMap);
        Button bTerrain = (Button) findViewById(R.id.bTerrain);
        Button bHybrid = (Button) findViewById(R.id.bHybrid);
        Button bSatellite = (Button) findViewById(R.id.bSatellite);
        Button bGoToNextLocation = (Button) findViewById(R.id.bGoToNextLocation);
        Button bGoToStreetView = (Button) findViewById(R.id.bGoToStreetView);

        bMap.setOnClickListener(this);
        bTerrain.setOnClickListener(this);
        bHybrid.setOnClickListener(this);
        bSatellite.setOnClickListener(this);
        bGoToNextLocation.setOnClickListener(this);
        bGoToStreetView.setOnClickListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markerOptions = new MarkerOptions()
                .position(new LatLng(40.7489, -73.9879))
                .title("This location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marker));
        markerOptionsNext = new MarkerOptions()
                .position(new LatLng(40.7480, -73.9870))
                .title("Next Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marker));
    }

    @Override
    public void onClick(View v) {
        if (isMapReady)
            switch (v.getId()) {
                case R.id.bMap:
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    break;
                case R.id.bTerrain:
                    googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    break;
                case R.id.bHybrid:
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    break;
                case R.id.bSatellite:
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    break;
                case R.id.bGoToNextLocation:
                    // preparing new location data \
                    CameraPosition cameraPositionNext = CameraPosition.fromLatLngZoom(locationNext, 17);
                    CameraUpdate cameraUpdateNext = CameraUpdateFactory.newCameraPosition(cameraPositionNext);
                    // moving the camera to the next position \
                    googleMap.animateCamera(cameraUpdateNext, 2 * 1000, null);
                    break;
                case R.id.bGoToStreetView:
                    startActivity(new Intent(MainActivity.this, StreetViewActivity.class));
                    break;
            }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        isMapReady = true;
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(locationNewYork)
                .zoom(17)
                .build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        // setting the camera for the starting point \
        googleMap.moveCamera(cameraUpdate);

        googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptionsNext);

        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                        .add(l1)
                        .add(l2)
                        .add(l3)
                        .add(l4)
                        .add(l1)
        );

        googleMap.addCircle(new CircleOptions()
                .center(l1)
                .fillColor(Color.YELLOW)
                .radius(10 * 1000));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        isMapReady = false;
    }
}
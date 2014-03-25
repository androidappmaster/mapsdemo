package es.appmaster.mapsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity {

    private LatLng MURCIA = new LatLng(37.9923, -1.13046);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) (getSupportFragmentManager().findFragmentById(R.id.map));
        GoogleMap map = mapFragment.getMap();

        // we can modify UI settings in code or in xml layout
        map.getUiSettings().setCompassEnabled(true);

        // center camera to MURCIA
        map.moveCamera(CameraUpdateFactory.newLatLng(MURCIA));

        // add a marker
        map.addMarker(new MarkerOptions()
                        .position(MURCIA)
                        .title(getString(R.string.app_name))
                        .snippet(getString(R.string.infowindow_snippet))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
        );

        // if we use a custom infowindow adapter,
        // the marker options title and snippet don't work directly
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                View v = getLayoutInflater().inflate(R.layout.infowindow_layout, null);
                TextView infowindowTitle = (TextView)v.findViewById(R.id.infowindow_title);
                TextView infowindowSubtitle = (TextView)v.findViewById(R.id.infowindow_subtitle);

                infowindowTitle.setText(marker.getTitle());
                infowindowSubtitle.setText(marker.getSnippet());

                return v;
            }

        });

    }

}

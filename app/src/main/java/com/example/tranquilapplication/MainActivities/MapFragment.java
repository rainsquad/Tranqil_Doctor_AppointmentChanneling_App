package com.example.tranquilapplication.MainActivities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tranquilapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {
       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


           // Inflate the layout for this fragment
           View view = inflater.inflate(R.layout.fragment_map,container,false);




           SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
           supportMapFragment.getMapAsync(new OnMapReadyCallback() {
               @Override
               public void onMapReady(@NonNull GoogleMap googleMap) {
//                   googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                       @Override
//                       public void onMapClick( LatLng latLng) {
//                           MarkerOptions markerOptions = new MarkerOptions();
//                           markerOptions.position(latLng);
//
//                           googleMap.clear();
//                           googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
//                           googleMap.addMarker(markerOptions);
//
//
//                       }
//                   });




                       LatLng sydney = new LatLng(6.919976718611493, 79.87052391958046);
                       googleMap.addMarker(new MarkerOptions()
                               .position(sydney)
                               .title("De Soysa Hospital for Women"));
                   googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,20));

               }
           });
        return view;
    }


}
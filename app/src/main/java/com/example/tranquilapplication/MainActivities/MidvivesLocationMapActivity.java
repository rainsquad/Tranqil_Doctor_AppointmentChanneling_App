package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tranquilapplication.MainActivities.MapFragment;
import com.example.tranquilapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class MidvivesLocationMapActivity extends AppCompatActivity {
    ImageView Direction1,Direction2,Direction3,Direction4,Direction5,Direction6,imgBack;
    MapView mapView;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midvives_location_map);
        Direction1 = findViewById(R.id.GovernmentMidwiferyServiceAssociation);
        Direction2 = findViewById(R.id.PublicHealthMidwifeNawinna);
        Direction3 = findViewById(R.id.CastleStreetHospitalforWomen);
        Direction4 = findViewById(R.id.PublicHealthChecker);
        Direction5 = findViewById(R.id.Publichealthmidwife);
        imgBack = findViewById(R.id.imgBack);


        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        //MAP VIEW setup

        Fragment fragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout,fragment).commit();

    imgBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onBackPressed();
        }
    });


    Direction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/dir//public+health+midwife+nawala+mps/@6.892005,79.8093708,12z/data=!3m1!4b1!4m9!4m8!1m1!4e2!1m5!1m1!1s0x3ae25a2c3cca5c29:0x74d9a110aaadd020!2m2!1d79.8793672!2d6.8920382" );
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);



            }
        });
        Direction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/dir//VW37%2B276+Public+Health+Midwife,Nawinna,+Maharagama+10280/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3ae25bad4debfa8d:0xc1d52eb44f7843b8?sa=X&ved=2ahUKEwi5v-P28Yz-AhVfTWwGHZmlDKUQ48ADegQIFRAF" + "   " );
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);



            }
        });
        Direction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/dir/6.9107712,79.8851072/Castle+Street+Hospital+for+Women,+Castle+St,+00800/@6.9107704,79.8827535,17z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3ae2599ab32af535:0xb3b8753cac684fdf!2m2!1d79.8847681!2d6.9106412" + "   " );
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });
        Direction4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/dir/6.9107712,79.8851072/WWR9%2BXGW+Public+Health+Checker+(Midwife),+TC+Rd,+Kolonnawa/@6.9258026,79.8889208,14z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3ae259e9a38b69b3:0x37df83d01d5f298c!2m2!1d79.9188987!2d6.9425568" + "   " );
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);



            }
        });
        Direction5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/dir/6.9107712,79.8851072/Public+Health+Midwife+(Weaboda+North+),+Webada+Rd/@6.9708963,79.8660697,12z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3ae2f97a931e0847:0x45ff6c4ca689cf3e!2m2!1d79.9977423!2d7.0186035" + "   " );
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);



            }
        });




      }
}
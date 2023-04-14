package com.example.tranquilapplication.Fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.tranquilapplication.Services.Constants.KEY_PROFILE_PIC;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.tranquilapplication.MainActivities.DoctorDetailsActivity;
import com.example.tranquilapplication.MainActivities.ForumActivity;
import com.example.tranquilapplication.MainActivities.MidvivesLocationMapActivity;
import com.example.tranquilapplication.MainActivities.ScreenTestStartActivity;
import com.example.tranquilapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends Fragment {

    SharedPreferences sharedPreferences;
    private TextView txtLoggedInUser;
    private ImageView pro_pic_view;
    private CardView therapy;
    private static final String KEY_NAME = "name";
    private static final String KEY_ID = "id";

    BottomNavigationView bottomNavigationView;



    LinearLayout MidWives, clickForum,layout;

    private LinearLayout selectEPDS, selectMidvives, selectDoctors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        selectEPDS = view.findViewById(R.id.clickEPDS);
        txtLoggedInUser = view.findViewById(R.id.Loggedinuser);
        pro_pic_view = view.findViewById(R.id.pro_pic);
        therapy = view.findViewById(R.id.therapy);
        selectDoctors = view.findViewById(R.id.ClickDoctors);

        MidWives = view.findViewById(R.id.ClickMidwives);
        clickForum = view.findViewById(R.id.clickForum);
        layout = view.findViewById(R.id.linearLayout4);
        //    layout = (LinearLayout)view;

        //Getting Loged-in user using shared preferences

        selectEPDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ScreenTestStartActivity.class);
                startActivity(intent);

            }
        });

        selectDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), DoctorDetailsActivity.class);
                startActivity(intent1);

            }
        });



        MidWives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          CreatepopUpwindowMidWives();
            }
        });
        clickForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatepopUpwindow();
            }
        });

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        // you can add listener of elements here
          /*Button mButton = (Button) view.findViewById(R.id.button);
            mButton.setOnClickListener(this); */



        sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);
        String id = sharedPreferences.getString(KEY_ID, null);
        String base64 = sharedPreferences.getString(KEY_PROFILE_PIC, null);
        byte[] imgdate = Base64.decode(base64, Base64.DEFAULT);
//                 String text = new String(imgdate, StandardCharsets.UTF_8);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);


        pro_pic_view.setImageBitmap(decodedByte);
        txtLoggedInUser.setText(name);


    }

    private void CreatepopUpwindow() {
      //  LayoutInflater inflater =getActivity (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentPopupWindow();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        // and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void CreatepopUpwindowMidWives() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentPopupWindowMidwives();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        // and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    //premium payment popup window
    private void PremiumPaymentPopupWindow() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_paymentgate_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);


            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconClosepayementGate);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentCardDetails();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }

    private void PremiumPaymentPopupWindowMidwives() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_paymentgate_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);


            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconClosepayementGate);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentCardDetailsMidwives();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }

    //Premium payment enter card details popup

    private void PremiumPaymentCardDetails() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_details_enter_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentDone();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }

    private void PremiumPaymentCardDetailsMidwives() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_details_enter_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PremiumPaymentDoneMidwives();
                popupWindow.dismiss();

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }

    private void PremiumPaymentDone() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_approved_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView Gotit;
        ImageView iconclose;
        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                Intent i = new Intent(getActivity(), ForumActivity.class);
                startActivity(i);

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }

    private void PremiumPaymentDoneMidwives() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_approved_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                Intent i = new Intent(getActivity(), MidvivesLocationMapActivity.class);
                startActivity(i);

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
    }
}
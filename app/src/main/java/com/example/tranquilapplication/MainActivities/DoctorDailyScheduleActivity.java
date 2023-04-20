package com.example.tranquilapplication.MainActivities;


import static com.example.tranquilapplication.Services.Constants.KEY_NAME;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.EveningSessionAdapter;
import com.example.tranquilapplication.Services.MorningSessionAdapter;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorDailyScheduleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Users> users;
    private MorningSessionAdapter adapter;
    private  EveningSessionAdapter adapter2;
    private NetworkService apiInterface;
    CalendarView calendarView;
    SharedPreferences sharedPreferences;

    TextView search, DateselectHidden,txtMorning,txtEvening,txtRecordsAvailable;

    Button btnMorning, btnEvening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_daily_schedule);


        DateselectHidden = findViewById(R.id.DateselectHidden);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        calendarView = findViewById(R.id.calenderView);
        btnEvening = findViewById(R.id.btnEvening);
        btnMorning = findViewById(R.id.btnMorning);
        txtEvening =findViewById(R.id.txtEvening);
        txtMorning = findViewById(R.id.txtMorning);
        txtRecordsAvailable = findViewById(R.id.txtRecordsAvailable);



        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);




        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                String sd = String.format("%04d/%02d/%02d", year, month + 1, dayOfMonth);
                DateselectHidden.setText(sd);
                if (txtMorning.getText().toString().equals("True"))
                {
                    fetchUsers("", "");
                }
                else if(txtEvening.getText().toString().equals("True"))
                {
                    fetchUsers2("", "");
                }else {
                    Toast.makeText(DoctorDailyScheduleActivity.this, "Please select session", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMorning.setBackground(ContextCompat.getDrawable(DoctorDailyScheduleActivity.this, R.drawable.button_round));
                btnEvening.setBackground(ContextCompat.getDrawable(DoctorDailyScheduleActivity.this, R.drawable.button_round_grey));

                btnMorning.setTextColor(Color.WHITE);
                btnEvening.setTextColor(Color.WHITE);
                txtMorning.setText("True");
                txtEvening.setText("False");

                fetchUsers("", "");

            }
        });
        btnEvening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEvening.setBackground(ContextCompat.getDrawable(DoctorDailyScheduleActivity.this, R.drawable.button_round));
                btnMorning.setBackground(ContextCompat.getDrawable(DoctorDailyScheduleActivity.this, R.drawable.button_round_grey));
                btnEvening.setTextColor(Color.WHITE);
                btnMorning.setTextColor(Color.WHITE);
                txtMorning.setText("False");
                txtEvening.setText("True");

                fetchUsers2("", "");

            }
        });


    }
//    @Override
//    public void onClick(View view) {
//        btnEvening.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));
//        btnMorning.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));
//    }

    public void fetchUsers(String key, String doctorid) {
        key = DateselectHidden.getText().toString();
        doctorid = sharedPreferences.getString(KEY_NAME, null);

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<Users>> call = apiInterface.getUsers(key, doctorid);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                users = response.body();
                adapter = new MorningSessionAdapter(users, DoctorDailyScheduleActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (adapter.getItemCount()==0){
                    txtRecordsAvailable.setVisibility(View.VISIBLE);
                }else{
                    txtRecordsAvailable.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(DoctorDailyScheduleActivity.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchUsers2(String key, String doctorid) {
        key = DateselectHidden.getText().toString();
        doctorid = sharedPreferences.getString(KEY_NAME, null);

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<Users>> call = apiInterface.getUsers2(key, doctorid);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                users = response.body();
                adapter2 = new EveningSessionAdapter(users, DoctorDailyScheduleActivity.this);
                recyclerView.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
                if (adapter2.getItemCount()==0){
                    txtRecordsAvailable.setVisibility(View.VISIBLE);
                }else{
                    txtRecordsAvailable.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(DoctorDailyScheduleActivity.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
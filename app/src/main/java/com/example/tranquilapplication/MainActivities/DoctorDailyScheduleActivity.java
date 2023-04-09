package com.example.tranquilapplication.MainActivities;


import static com.example.tranquilapplication.Services.Constants.KEY_NAME;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.Adapter;
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
    private Adapter adapter;
    private NetworkService apiInterface;
    CalendarView calendarView;
    SharedPreferences sharedPreferences;

    TextView search,DateselectHidden;

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

        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);




        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                String sd=   String.format("%04d/%02d/%02d", year, month + 1, dayOfMonth);
                DateselectHidden.setText(sd);
                fetchUsers("","");
            }
        });
           }
    public void fetchUsers(String key, String doctorid){
        key =   DateselectHidden.getText().toString();
        doctorid = sharedPreferences.getString(KEY_NAME,null);

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<Users>> call = apiInterface.getUsers(key,doctorid);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                users = response.body();
                adapter = new Adapter(users, DoctorDailyScheduleActivity.this );
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t)
            {
                Toast.makeText(DoctorDailyScheduleActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
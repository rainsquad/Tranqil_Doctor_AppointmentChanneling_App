package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.KEY_ID;
import static com.example.tranquilapplication.Services.Constants.KEY_NAME;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.Adapter;
import com.example.tranquilapplication.Services.AdapterNotification;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private List<Users> users;

    private RecyclerView.LayoutManager layoutManager;
    private NetworkService apiInterface;
    private AdapterNotification adapterNotification;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fetchNotification();

    }


    public void fetchNotification(){
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String patientid = sharedPreferences.getString(KEY_NAME,null);
        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<Users>> call = apiInterface.getNotification(patientid);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                users = response.body();
                adapterNotification = new AdapterNotification(users, NotificationActivity.this);
                recyclerView.setAdapter(adapterNotification);
                adapterNotification.notifyDataSetChanged();
                Toast.makeText(NotificationActivity.this, "asdas" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t)
            {
                Toast.makeText(NotificationActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
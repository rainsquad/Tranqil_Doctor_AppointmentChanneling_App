package com.example.tranquilapplication.MainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.Adapter;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRecycle extends AppCompatActivity {
    private NetworkService apiInterface;
    private RecyclerView recyclerView;
    private CalendarView calendarView;
    Button testSubmit;
    TextInputEditText testDate;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Users> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycle);

        testSubmit = findViewById(R.id.testSubmit);
        testDate = findViewById(R.id.testdate);

testSubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fetchUsers();
    }
});



    }
    public void fetchUsers( ) {

        String tDate = testDate.getText().toString();
        // String key =  DateselectHidden.getText().toString();
        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<Users>> call = apiInterface.getUsers(tDate);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                users = response.body();
                adapter = new Adapter(users, TestRecycle.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(TestRecycle.this, "asdas" + tDate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

                Toast.makeText(TestRecycle.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
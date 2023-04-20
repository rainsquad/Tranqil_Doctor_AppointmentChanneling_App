package com.example.tranquilapplication.MainActivities;

import static com.example.tranquilapplication.Services.Constants.KEY_ID;
import static com.example.tranquilapplication.Services.Constants.KEY_NAME;
import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tranquilapplication.R;
import com.example.tranquilapplication.ResponseModels.TestResults;
import com.example.tranquilapplication.ResponseModels.Users;
import com.example.tranquilapplication.Services.AdapterNotification;
import com.example.tranquilapplication.Services.AdapterPreviousResults;
import com.example.tranquilapplication.Services.NetworkClient;
import com.example.tranquilapplication.Services.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreviousTestResultsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private List<TestResults> users;
    ImageView imgback;




    private RecyclerView.LayoutManager layoutManager;
    private NetworkService apiInterface;
    private AdapterPreviousResults adapterPreviousResults;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_test_results);

        imgback = findViewById(R.id.imgBack);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        fetchNotification();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void fetchNotification(){
        sharedPreferences =this.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String patientid = sharedPreferences.getString(KEY_ID,null);
        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<TestResults>> call = apiInterface.getTestResults(patientid);
        call.enqueue(new Callback<List<TestResults>>() {
            @Override
            public void onResponse(Call<List<TestResults>> call, Response<List<TestResults>> response) {
                users = response.body();
                adapterPreviousResults = new AdapterPreviousResults(users,PreviousTestResultsActivity.this);
                recyclerView.setAdapter(adapterPreviousResults);
                adapterPreviousResults.notifyDataSetChanged();
                //Toast.makeText(getContext(), "asdas" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<TestResults>> call, Throwable t)
            {
                Toast.makeText(PreviousTestResultsActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
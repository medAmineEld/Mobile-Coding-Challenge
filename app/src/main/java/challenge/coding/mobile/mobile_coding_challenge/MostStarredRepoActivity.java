package challenge.coding.mobile.mobile_coding_challenge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import challenge.coding.mobile.mobile_coding_challenge.adapter.AdapterRepo;
import challenge.coding.mobile.mobile_coding_challenge.api.Client;
import challenge.coding.mobile.mobile_coding_challenge.api.Service;
import challenge.coding.mobile.mobile_coding_challenge.model.MostStarredRepo;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Cache;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MostStarredRepoActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    String sort="stars";
    String order="desc";
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_starred_repo);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadJSONRepo();
    }
    private void initViews()
    {
        recyclerView = findViewById(R.id.recycler_view_repo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(null);
    }
    private void loadJSONRepo(){
        Retrofit retrofit = Client.getClient();
        Service apiService = retrofit.create(Service.class);
        Call <MostStarredRepo> call  = apiService.getMostStarredRepo(getQuery(),sort,order,page);


        call.enqueue(new Callback<MostStarredRepo>() {
            @Override
            public void onResponse(Call<MostStarredRepo> call, Response<MostStarredRepo> response) {
                try {
                    recyclerView.setAdapter(new AdapterRepo(MostStarredRepoActivity.this, response.body().getItems()));
                    recyclerView.smoothScrollToPosition(0);
                } catch (Exception ex) {
                    Log.d("error : ",ex.getMessage()); }

            }

            @Override
            public void onFailure(Call<MostStarredRepo> call, Throwable t) {
                Log.d("error : ",t.getMessage());
            }
        });
    }
    private String getQuery()
    {
        Calendar calendar = Calendar.getInstance();
        String _date = String.valueOf(calendar.get(Calendar.YEAR))
                +"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)
                +"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return "created:>"+"2017-10-22";
    }

}

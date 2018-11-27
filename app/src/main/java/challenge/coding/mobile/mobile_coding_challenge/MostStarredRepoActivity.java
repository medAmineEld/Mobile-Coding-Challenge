package challenge.coding.mobile.mobile_coding_challenge;

import android.app.ProgressDialog;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import challenge.coding.mobile.mobile_coding_challenge.adapter.AdapterRepo;
import challenge.coding.mobile.mobile_coding_challenge.api.Client;
import challenge.coding.mobile.mobile_coding_challenge.api.Service;
import challenge.coding.mobile.mobile_coding_challenge.model.MostStarredRepo;
import challenge.coding.mobile.mobile_coding_challenge.model.Repository;
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
    List<Repository> lstRepo = new ArrayList<>();
    AdapterRepo adapterRepo = null;
    ProgressDialog dialog;
    boolean loaded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_starred_repo);
        initViews();
    }

    private void showDialog()
    {
        dialog.setMessage("Wait Please...");
        dialog.setCancelable(false);
        dialog.show();
    }
    void dismissDialog()
    {
        try {
            if(dialog!=null && dialog.isShowing())
                dialog.hide();
        }catch (Exception ex){}
    }
    @Override
    protected void onStart() {
        super.onStart();
        showDialog();
        loadJSONRepo();
    }
    private void initViews()
    {
        dialog = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recycler_view_repo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(null);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1) && loaded) {
                loaded = false;
                page+=1;
                showDialog();
                loadJSONRepo();
            }
        }
    });
    }
    private void loadJSONRepo(){
        Retrofit retrofit = Client.getClient();
        Service apiService = retrofit.create(Service.class);
        Call <MostStarredRepo> call  = apiService.getMostStarredRepo(getQuery(),sort,order,page);
        call.enqueue(new Callback<MostStarredRepo>() {
            @Override
            public void onResponse(Call<MostStarredRepo> call, Response<MostStarredRepo> response) {
                try {
                    lstRepo.addAll(response.body().getItems());
                    if(page == 1)
                    {
                        adapterRepo=new AdapterRepo(MostStarredRepoActivity.this, lstRepo);
                        recyclerView.setAdapter(adapterRepo);
                        recyclerView.smoothScrollToPosition(0);
                    }else
                    {
                        adapterRepo.notifyDataSetChanged();
                    }
                    dismissDialog();
                    loaded = true;
                } catch (Exception ex) {
                    Log.d("error : ",ex.getMessage());
                    dismissDialog();
                    loaded = true;
                }

            }

            @Override
            public void onFailure(Call<MostStarredRepo> call, Throwable t) {
                Log.d("error : ",t.getMessage());
                dismissDialog();
                loaded = true;
            }
        });
    }
    private String getQuery()
    {
        Calendar calendar = Calendar.getInstance();
        /*String _date = String.valueOf(calendar.get(Calendar.YEAR))
                +"-"+String.valueOf(calendar.get(Calendar.MONTH)+1)
                +"-"+String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));*/
        return "created:>"+"2017-10-22";
    }

}

package cl.carreno.app_ev2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.ConnectivityManagerCompat;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

public class inicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inic);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                if (checkConnection()){
                    Intent intent = new Intent(
                            inicActivity.this , LoginActivity.class
                    );

                    startActivity(intent);
                }

                finish();
            }
        },1000);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean checkConnection(){
        boolean state = false;

        ConnectivityManager manager;
        manager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        Network[] networks = manager.getAllNetworks();
        for (Network network : networks){
            NetworkInfo info = manager.getNetworkInfo(network);
            if(info != null) {
                state = true;
            }
        }
        return state;
    }
}
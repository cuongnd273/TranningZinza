package cuongnguyen.app.com.day7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        token = (Button) findViewById(R.id.token);
        token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKyFCM dangKyFCM = new DangKyFCM();
                dangKyFCM.onTokenRefresh();
            }
        });
    }
}

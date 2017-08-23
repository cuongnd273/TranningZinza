package cuongnguyen.app.com.day7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by CuongNguyen on 07/14/17.
 */

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Đã cắm sạc",Toast.LENGTH_SHORT).show();
    }
}

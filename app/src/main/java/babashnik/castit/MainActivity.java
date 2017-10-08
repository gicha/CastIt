package babashnik.castit;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button bStart;
    public boolean isStarted;
    Intent serviceIntent;
    public static ApplicationInfo app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            app = this.getPackageManager().getApplicationInfo("babashnik.castit", 0);
        } catch (PackageManager.NameNotFoundException e) {
            Toast toast = Toast.makeText(this, "error in getting icon", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }

        bStart = (Button) findViewById(R.id.start);
        bStart.setOnClickListener(controlButtonListener);
        isStarted = false;


    }

    View.OnClickListener controlButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("MainCastIt", "onClick\t" + isStarted);
            if (!isStarted) {
                startPlayerService();
                isStarted = !isStarted;
            }
            else{
                stopPlayerService();
                isStarted = !isStarted;
            }
        }
    };

    private void stopPlayerService() {
        stopService(serviceIntent);
        Log.e("MainCastIt", "stop service");
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void startPlayerService() {
        serviceIntent = new Intent(MainActivity.this, PlayService.class);
        serviceIntent.setAction(String.valueOf(serviceIntent));
        startService(serviceIntent);
    }

}

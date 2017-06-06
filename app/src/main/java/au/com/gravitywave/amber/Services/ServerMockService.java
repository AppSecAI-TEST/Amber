package au.com.gravitywave.amber.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ServerMockService extends Service {

    private  final IBinder serverMockBinder = new ServiceMockBinder();

    public ServerMockService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return serverMockBinder;
    }

    public String getCurrentTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        return (simpleDateFormat.format(new Date()));

    }


    public class ServiceMockBinder extends Binder {
        public ServerMockService getService() {
            return ServerMockService.this;
        }
    }
}

package prova.biscoitodasorte;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Douglas Ballester on 13/11/2017.
 */

public class ServiceInitialize extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent(context, BiscoitoService.class);//explicita
        context.startService(intent);
    }
}

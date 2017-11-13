package prova.biscoitodasorte;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Douglas Ballester on 13/11/2017.
 */

public class NotificationTrigger extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent resultIntent = new Intent(context, MensagemActivity.class);
        resultIntent.putExtra("Mensagem", intent.getExtras().getString("Mensagem"));
        resultIntent.putExtra("Autor", intent.getExtras().getString("Autor"));
        PendingIntent resultPendingIntent = PendingIntent.getActivity( // atividade
                context, // Contexto que vem do receive "MUITO IMPORTANTE"
                0, // Parametro não usado
                resultIntent, // Intent que lancará
                PendingIntent.FLAG_ONE_SHOT
        );
        //String value = getIntent().getExtras().getString("Mensagem");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.gingerbread)
                .setContentTitle("Biscoitão")
                .setContentText("Você tem uma nova mensagem!");

        mBuilder.setContentIntent(resultPendingIntent);// Seta a intent que vai
        // abrir

        Notification n = mBuilder.build();
        n.vibrate = new long[]{150, 300, 150, 600, 10, 600};//Vibrar
        n.flags = Notification.FLAG_AUTO_CANCEL;

        mNotifyMgr.notify(1, n);

//        Uri som = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.msg);
//        Ringtone toque = RingtoneManager.getRingtone(context, som);
//        toque.play();
    }
}
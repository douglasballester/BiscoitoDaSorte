package prova.biscoitodasorte;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Douglas Ballester on 13/11/2017.
 */

public class BiscoitoService extends Service {

    private static final String URL = "http://biscoito.herokuapp.com/";
    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.v("BISCOITO","SERVICE - onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId){
        Log.i("BISCOITO", "onStartCommand()");

        //Wtf?
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        new BiscoitoWebService().execute(URL);
                    }
                });
            }};

        timerAtual.schedule(task, 300, 60000);

        return(super.onStartCommand(intent, flags, startId));//Continua ciclo de vida do meu serviço
    }

    private void showMensagem(Mensagem mensagem){
        Log.i("12345", mensagem.getMsg());
        if(!mensagem.getMsg().equals("Sem mensagem")){
            Context ct = getApplicationContext();
            Intent i = new Intent(ct, NotificationTrigger.class);
            i.putExtra("Autor", mensagem.getAutor());
            i.putExtra("Mensagem", mensagem.getMsg());
            ct.sendBroadcast(i);//Explicito
        }
    }

    private class BiscoitoWebService extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                Log.i("URL", WebServiceUtil.getContentAsString(urls[0]));
                return WebServiceUtil.getContentAsString(urls[0]);

            } catch (IOException e) {
                Log.e("Exception", e.toString());
                return "Problema ao montar a requisição";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject my_obj = new JSONObject(result);
                String mensagem =  my_obj.getString("mensagem").toString();
                String autor = "";
                if(!mensagem.equals("Sem mensagem")){
                    autor = my_obj.getString("autor").toString();
                }

                Mensagem m = new Mensagem(mensagem, autor);
                showMensagem(m);
                Log.d("Rolas", my_obj.getString("mensagem").toString());


            } catch (Exception e){
                Log.e("Erro",e.getMessage());
            }
        }
    }
}

package prova.biscoitodasorte;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://biscoito.herokuapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, BiscoitoService.class));
        new BiscoitoWebService().execute(URL);
    }


    private void showMensagem(Mensagem mensagem){
        TextView msg = (TextView)findViewById(R.id.mensagem);
        TextView autor = (TextView)findViewById(R.id.autor);

        autor.setText(mensagem.getAutor());
        msg.setText(mensagem.getMsg());
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
                String autor = my_obj.getString("autor").toString();
                Mensagem m = new Mensagem(autor, mensagem);
                showMensagem(m);
                Log.d("Rolas", my_obj.getString("mensagem").toString());


            } catch (Exception e){
                Log.e("Erro",e.getMessage());
            }
        }
    }
}

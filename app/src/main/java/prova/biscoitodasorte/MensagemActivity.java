package prova.biscoitodasorte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Douglas Ballester on 13/11/2017.
 */

public class MensagemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);

        TextView msg = (TextView)findViewById(R.id.mensagem);
        TextView autor = (TextView)findViewById(R.id.autor);

        String mensagem = getIntent().getExtras().getString("Mensagem");
        String aut = getIntent().getExtras().getString("Autor");

        autor.setText(aut);
        msg.setText(mensagem);
    }}

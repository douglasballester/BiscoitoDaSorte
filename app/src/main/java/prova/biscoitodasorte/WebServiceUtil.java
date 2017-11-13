package prova.biscoitodasorte;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Douglas Ballester on 13/11/2017.
 */

public class WebServiceUtil {

    public static String getContentAsString(String urlStr) throws IOException {
        URL url = null;
        InputStream is = null;
        try {
            try {
                String urlFinal = URLEncoder.encode(urlStr, "utf-8");
                url = new URL(urlStr);
            } catch (MalformedURLException e) {
                Log.e("SENHOR PROGRAMADOR", "Você fez caca verifique");
                e.printStackTrace();
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("DEBUG", "Resposta HTTP: " + response);

            is = conn.getInputStream();

            return readIt(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }

    }

    private static String readIt(InputStream stream) throws IOException {
        Reader reader = null;
        StringBuffer buffer = new StringBuffer();
        reader = new InputStreamReader(stream, "UTF-8");
        Reader in = new BufferedReader(reader);
        int ch;
        while ((ch = in.read()) > -1) {
            buffer.append((char) ch);
        }
        in.close();
        return new String(buffer);
    }
}
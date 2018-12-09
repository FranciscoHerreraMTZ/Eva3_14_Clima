package com.example.spart.eva3_14_clima;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
TextView txt;
Button bot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        txt = findViewById(R.id.Txt);
        bot = findViewById(R.id.botone);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick (View v){
    Conexion c = new Conexion();
    c.execute();
    }
    class Conexion extends AsyncTask< Void, Void,String>{
final String sLink = "https://samples.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric&appid=b6907d289e10d714a6e88b30761fae22";
        @Override
        protected String doInBackground(Void... voids) {
            String sRes = "";
            try {
                URL url = new URL(sLink);
                HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                if(httpcon.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader brDatos = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
                    sRes = brDatos.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sRes;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONArray jCiudades = null;

            if(!s.equals("")){

                try {

                    JSONObject jsDatos = new JSONObject(s);
                    jCiudades = jsDatos.getJSONArray("list");
                    for (int i =0;i< jCiudades.length();i++){
                        JSONObject jCiudad = jCiudades.getJSONObject(i);
                        txt.append("Ciudad "+jCiudad.getString("name"));
                        Toast.makeText(getApplicationContext(),"hooollaaaaaa",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
e.printStackTrace();
                }
            }else
            txt.setText(s);

        }
    }
}

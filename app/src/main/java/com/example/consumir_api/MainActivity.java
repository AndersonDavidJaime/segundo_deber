package com.example.consumir_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://dummyjson.com/users",
        datos, this, this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {

        TextView txt = (TextView) findViewById(R.id.txtDatos);
        txt.setText(result);
        String ListaDatos = "";
        JSONObject objecto = new JSONObject(result);
        JSONArray JSONlista = objecto.getJSONArray("users");
        for (int i = 0; i<JSONlista.length();i++){
            JSONObject datos = JSONlista.getJSONObject(i);

            ListaDatos = ListaDatos +"\n"+
                    datos.getString("firstName").toString() +", "+
                    datos.getString("age").toString()+", "+
                    datos.getString("email");
        }
        txt.setText(ListaDatos);
    }
}
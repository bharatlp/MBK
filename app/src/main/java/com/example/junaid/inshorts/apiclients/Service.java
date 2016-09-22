package com.example.junaid.inshorts.apiclients;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.junaid.inshorts.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by junaid on 25/01/16.
 */
public class Service {

    Context context;
    Result result;
    public Service(Result result,Context context){
        this.result     =   result;
        this.context    =   context;
    }

    public interface Result{
        void result(String response);
    }

    public void callApi(){
        new BaseService().execute();
    }

    public class BaseService extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String response = "";

           // HttpURLConnection urlConnection = null;
            try {
                int c = 0;
               /*
                URL url = new URL("http://demo0427532.mockable.io/get_cards");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        in, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }

                response = sb.toString();


                //response    = t;
                in.close();
                urlConnection.disconnect();
                */
               // response    =   context.getResources().getString(R.string.response);
                //response    =   context.getResources().getString(R.string.response);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result.result(s);
        }
    }
}

package com.tom.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go(View v){
        Log.d("JOB1", "sleeping");
//        new Job1Task().execute();
//        new Job2Task().execute(3);
        new Job3Task().execute(5);
    }

    class Job3Task extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            int n = params[0];
            for (int i=n; i>0; i--){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = (TextView) findViewById(R.id.info);
            info.setText("DONE");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView info = (TextView) findViewById(R.id.info);
            info.setText(values[0]+"");
        }
    }

    class Job2Task extends AsyncTask<Integer, Void, Void>{

        @Override
        protected Void doInBackground(Integer... params) {

            try {
                Thread.sleep(params[0]*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = (TextView) findViewById(R.id.info);
            info.setText("DONE");
        }
    }

    class Job1Task extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = (TextView) findViewById(R.id.info);
            info.setText("DONE");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

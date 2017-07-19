package com.example.amit.assignment162paralleltaskusingasynctask;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import static android.os.Build.VERSION.SDK;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar1,progressBar2,progressBar3,progressBar4,progressBar5;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1=(ProgressBar)findViewById(R.id.progressBar1);
        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar3=(ProgressBar)findViewById(R.id.progressBar3);
        progressBar4=(ProgressBar)findViewById(R.id.progressBar4);
        progressBar5=(ProgressBar)findViewById(R.id.progressBar5);
        button=(Button)findViewById(R.id.btndownload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask myAsyncTask1=new MyAsyncTask(progressBar1);
                myAsyncTask1.execute();
                MyAsyncTask myAsyncTask2=new MyAsyncTask(progressBar2);
                myAsyncTask2.execute();
                MyAsyncTask myAsyncTask3=new MyAsyncTask(progressBar3);
                myAsyncTask3.execute();
                MyAsyncTask myAsyncTask4=new MyAsyncTask(progressBar4);
                StartAsyncTaskInParallel(myAsyncTask4);
                MyAsyncTask myAsyncTask5=new MyAsyncTask(progressBar5);
                StartAsyncTaskInParallel(myAsyncTask5);

            }

          @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            private void StartAsyncTaskInParallel(MyAsyncTask myAsyncTask) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB)
                    myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
              else
                  myAsyncTask.execute();
            }
        });
    }
    public class MyAsyncTask extends AsyncTask<Void,Integer,Void>{
        ProgressBar progressBar;

        public MyAsyncTask(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0;i<100;i++){
                publishProgress(i);
                SystemClock.sleep(100);

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.INVISIBLE);
            super.onPostExecute(aVoid);
        }
    }
}

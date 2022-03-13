package com.example.cmpe277_assignment_second;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AndoridFileDownloading extends AsyncTask<String, Integer, String> {

    private String TagName = "Andorid File Downloader";
    @Override
    protected String doInBackground(String... sUrl) {
        Log.e(TagName, "File Download in progress" + sUrl[0]);
        HttpURLConnection conn = null;
        OutputStream ou = null;
        InputStream in = null;


        try {
            URL url = new URL(sUrl[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            Log.e(TagName, String.valueOf(conn.getResponseCode()));
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "HTTP " + conn.getResponseCode()
                        + " " + conn.getResponseMessage();
            }


            int fileLength = conn.getContentLength();
            Log.e(TagName, String.valueOf(fileLength) );

            in = conn.getInputStream();

            File folder = new File(Environment.getExternalStorageDirectory().toString() + "/Download/");
            boolean value = folder.mkdir();
            String fileName = sUrl[0].substring(sUrl[0].lastIndexOf('/') + 1);
            String fullNAme = folder.getAbsolutePath() + "/" + fileName;
            File documentFile = new File(fullNAme);
            if(!documentFile.exists()){
                try {
                    documentFile.createNewFile();
                } catch (Exception e) {
                    Log.e(TagName, e.toString());
                    e.printStackTrace();
                }
            }



            ou = new FileOutputStream(documentFile, false);

            byte data[] = new byte[4096];
            long total = 0;
            int count;

            while ((count = in.read(data)) != -1) {

                if (isCancelled()) {
                    in.close();
                    return null;
                }
                total += count;

                if (fileLength > 0)
                    publishProgress((int) (total * 100 / fileLength));

                ou.write(data, 0, count);
            }
        } catch (Exception e) {
            Log.e(TagName, e.toString());
            return e.toString();
        } finally {
            try {
                if (ou != null)
                    ou.close();
                if (in != null)
                    in.close();
            } catch (IOException ignored) {
            }

            if (conn != null)
                conn.disconnect();
        }

        Log.e(TagName, "File Download Completed" + sUrl[0]);
        return null;
    }




        }

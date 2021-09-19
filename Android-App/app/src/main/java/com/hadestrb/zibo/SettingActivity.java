package com.hadestrb.zibo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hadestrb.zibo.Model.Display;
import com.hadestrb.zibo.Model.RequiredFile.FileItem;
import com.hadestrb.zibo.Model.RequiredFile.FileWrapper;
import com.hadestrb.zibo.Model.HardwareKey;
import com.hadestrb.zibo.Model.RegisterDisplay;
import com.hadestrb.zibo.Model.Schedule.Schedule;
import com.hadestrb.zibo.Model.Schedule.ScheduleDependents;
import com.hadestrb.zibo.Model.Schedule.ScheduleItem;
import com.hadestrb.zibo.Model.Schedule.ScheduleWrapper;
import com.hadestrb.zibo.Service.APIService;
import com.hadestrb.zibo.Service.Constant;
import com.hadestrb.zibo.Service.RetrofitClient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    String TAG = "SETTING_ACTIVITY";
    String HARDWARE_CODE;
    Button btn_start;
    TextInputEditText txt_cms_address, txt_display_name;
    ProgressDialog progressDialog;

    private String fileName;
    private String cmsAddress;
    private String displayName;
    private Display display;
    private FileWrapper fWrapper;
    private ScheduleWrapper sWrapper;
    private Schedule schedule;
    private String file_name;
    private com.hadestrb.zibo.Model.RequiredFile.File file = new com.hadestrb.zibo.Model.RequiredFile.File();

    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initInterface();

        progressDialog = new ProgressDialog(SettingActivity.this);
        btn_start.setOnClickListener(view -> {
            if (txt_cms_address.getText() != null && txt_display_name.getText() != null) {
                cmsAddress = txt_cms_address.getText().toString();
                displayName = txt_display_name.getText().toString();
                Log.d(TAG, "CMS" + cmsAddress);
                //start
                APIRegisterDisplay();
            } else {
                Toast.makeText(this, "Address must not be empty!!", Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.setOnCancelListener(DialogInterface::dismiss);
    }

    private void initInterface() {
        HARDWARE_CODE = Constant.getHardwareCode(getApplicationContext());
        btn_start = findViewById(R.id.btn_start);
        txt_cms_address = findViewById(R.id.txt_address);
        txt_display_name = findViewById(R.id.txt_display_name);
    }

    /*API Service*/
    private void APIRegisterDisplay() {

        RegisterDisplay registerDisplay = new RegisterDisplay();
        registerDisplay.setHardwareKey(Constant.getHardwareCode(getApplicationContext()));
        registerDisplay.setDisplayName(displayName);
        registerDisplay.setClientCode("2");
        registerDisplay.setClientType(Constant.CLIENT_TYPE);
        registerDisplay.setMacAddress(Constant.MAC_ADDRESS);
        registerDisplay.setOperatingSystem(Constant.OPERATING_SYSTEM);
        registerDisplay.setClientVersion(Constant.CLIENT_VERSION);
        registerDisplay.setXmrChannel("");
        registerDisplay.setXmrPubKey("");

        Log.d(TAG, registerDisplay.toString());

        apiService = RetrofitClient.getClient(cmsAddress).create(APIService.class);
        apiService.Register(registerDisplay).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    JsonObject json = response.body().getAsJsonObject("response").getAsJsonObject("display").getAsJsonObject("$");

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String string = gson.toJson(json);
                    Log.d(TAG, "display:" + string);
                    display = gson.fromJson(string, Display.class);
                    Log.d(TAG, display.toString());
                    Toast.makeText(getApplicationContext(), display.getMessage(), Toast.LENGTH_SHORT).show();
                    if (display != null) {
                        if (display.getCode().equals("READY")) {
                            //code == READY Display is active and ready to start

                            Log.d(TAG, " REGISTER_DISPLAY: get display successful, code:" + display.getCode());

                            APISchedule();
                            APIGetRequiredFile();
                        } else {
                            Toast.makeText(getApplicationContext(), display.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d(TAG, "failed");
                        Toast.makeText(getApplicationContext(), "Unable to connect to CMS!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Log.e(TAG, "REGISTER_DISPLAY:" + t.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), "Unable to connect to CMS!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void APIGetRequiredFile() {
        Log.d(TAG, "API REQUIRED FILE: " + cmsAddress);
        apiService = RetrofitClient.getClient(cmsAddress).create(APIService.class);
        apiService.getRequireFile(new HardwareKey(HARDWARE_CODE)).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    //get json
                    JsonObject json = response.body().getAsJsonObject("response").getAsJsonObject("files");
                    Gson gsonBuilder = new GsonBuilder().create();
                    String stringFile = gsonBuilder.toJson(json);
                    fWrapper = gsonBuilder.fromJson(stringFile, FileWrapper.class);

                    for (FileItem fileItem : fWrapper.getListFiles()) {
                        com.hadestrb.zibo.Model.RequiredFile.File f = fileItem.getFile();
                        if (f.getSaveAs() != null) {
                            if (f.getSaveAs().equals(file_name)) {
                                file = f;
                            }
                        }
                    }
                    Log.d(TAG, "file in api file: " + file.toString());
                    String fileURL = file.getPath();

                    getVideoPlayer(fileURL, file_name);
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Log.e(TAG, "REQUIRED_FILE:" + t.getLocalizedMessage());
            }
        });
    }

    private void APISchedule() {
        Log.d(TAG, "API SCHEDULE: " + cmsAddress);
        apiService = RetrofitClient.getClient(cmsAddress).create(APIService.class);
        apiService.getSchedule(new HardwareKey(HARDWARE_CODE)).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.body() != null) {
                    //get json
                    JsonObject json = response.body().getAsJsonObject("response").getAsJsonObject("schedule");
                    Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
                    //string json
                    String stringFile = gsonBuilder.toJson(json);
                    Log.d(TAG, "StringFile:" +stringFile);
                    //cast to object
                    sWrapper = gsonBuilder.fromJson(stringFile, ScheduleWrapper.class);
                    Log.d(TAG, "SCHEDULE:" + sWrapper.toString());

                    List layout = null;
                    String jsonSchedule;
                    String strSchedule = null;
                    ScheduleItem item;

                    Log.d(TAG, "Default:" + sWrapper.getsDefault().toString());

                    if (sWrapper != null) {
                        if (sWrapper.getsLayout() == null) {
                            layout = sWrapper.getsDefault();
                            strSchedule = "default";
                        } else {
                            Log.d(TAG, "Layout:" + sWrapper.getsLayout().toString());
                            layout = sWrapper.getsLayout();
                            strSchedule = "layout";
                        }
                    }

                    jsonSchedule = new Gson().toJson(layout);
                    jsonSchedule = jsonSchedule.substring(1, jsonSchedule.length() - 1);
                    item = new Gson().fromJson(jsonSchedule, ScheduleItem.class);
                    schedule = item.getItem();
                    Log.d(TAG, "schedule:" + schedule.toString());

                    file_name = response.body().getAsJsonObject("response")
                            .getAsJsonObject("schedule")
                            .getAsJsonArray(strSchedule)
                            .get(0).getAsJsonObject()
                            .getAsJsonArray("dependents")
                            .get(0).getAsJsonObject()
                            .getAsJsonArray("file")
                            .get(0).getAsString();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Log.e(TAG, "SCHEDULE:" + t.getLocalizedMessage());
            }
        });
    }
    /*end API Service*/


    /*Function*/
    private void getVideoPlayer(String URL, String fileName) {
        String ending = getFileName(URL);
        String URLVideo = getVideoURL(cmsAddress) + ending;
        new DownloadAsyncTask().execute(URLVideo, fileName);
    }

    private String getVideoURL(String cmsAddress) {
        int index = cmsAddress.lastIndexOf(":");
        Log.d(TAG, "index:" + index);
        return cmsAddress.substring(0, index) + "/";
    }

    @NonNull
    private String getFilePath(String fileName) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        File file = new File(directory, fileName);
        return file.getPath();
    }

    @NonNull
    private String getFileName(String filePath) {
        return new File(filePath).getName();
    }

    @SuppressLint("StaticFieldLeak")
    class DownloadAsyncTask extends AsyncTask<String, String, String> {

        private PowerManager.WakeLock wakeLock;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
            progressDialog.setIndeterminate(false);
            progressDialog.setProgress(0);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Waiting for the video get ready!!");
            progressDialog.setTitle("Download");
            progressDialog.setMax(100);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String path = strings[0];
            fileName = strings[1];
            File file = new File(getFilePath(fileName));
            if (file.exists()) {
                return "File exists";
            }

            try {
                URL url = new URL(path);
                Log.d(TAG, "url: " + url);
                Log.d(TAG, "File name: " + fileName);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();

                int fileLength = urlConnection.getContentLength();
                Log.d(TAG, "file length: " + fileLength);
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                byte[] data = new byte[1027];
                int total = 0;
                int count;
                int percent;

                OutputStream outputStream = new FileOutputStream(getFilePath(fileName));
                Log.d(TAG, "filepath: " + getFilePath(fileName));

                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    percent = (int) (total * 100L / fileLength);
                    publishProgress(String.valueOf(percent));
                    //write file
                    outputStream.write(data, 0, count);
                    //Log.e(TAG, "Descartes: "+ total);
                }
                outputStream.flush();
                inputStream.close();
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            String filePath = getFilePath(fileName);
            Log.d(TAG, "file path: " + filePath);

            return "Download file successful";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(Integer.parseInt(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            wakeLock.release();
            progressDialog.dismiss();
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            Gson gson = new Gson();
            String scheduleJson = gson.toJson(schedule);
            intent.putExtra("scheduleJson", scheduleJson);
            intent.putExtra("fileName", fileName);
            Log.d("MAIN_ACTIVITY", fileName);
            startActivity(intent);
        }
    }
}
package com.spt.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONArray;

import android.os.Environment;
import android.util.Base64;

import java.io.*;
import org.json.JSONException;
import org.json.JSONObject;

public class Base64ToPNG extends CordovaPlugin {

    @Override
    public boolean execute (String action, JSONArray args, CallbackContext callbackContext) throws JSONException
    {
        if (!action.equals("saveImage")) {

            PluginResult pluginResult = new  PluginResult(PluginResult.Status.INVALID_ACTION);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return false;
        }

        try {

            String b64String = "";
            if (b64String.startsWith("data:image")) {
                b64String = args.getString(0).substring(21);
            } else {
                b64String = args.getString(0);
            }
            JSONObject params = args.getJSONObject(1);

            //Optional parameter
            String filename = params.has("filename")
                    ? params.getString("filename")
                    : "b64Image_" + System.currentTimeMillis() + ".png";

            String folder = params.has("folder")
                    ? params.getString("folder")
                    : Environment.getExternalStorageDirectory() + "/Pictures";

            Boolean overwrite = params.has("overwrite") 
                    ? params.getBoolean("overwrite") 
                    : false;

            return this.saveImage(b64String, filename, folder, overwrite, callbackContext);

        } catch (JSONException e) {

            e.printStackTrace();
            PluginResult pluginResult = new  PluginResult(PluginResult.Status.JSON_EXCEPTION);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return false;

        } catch (InterruptedException e) {
            e.printStackTrace();
            PluginResult pluginResult = new  PluginResult(PluginResult.Status.ERROR);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return false;
        }

    }

    private boolean saveImage(String b64String, String fileName, String dirName, Boolean overwrite, CallbackContext callbackContext) throws InterruptedException, JSONException {

        try {
            PluginResult pluginResult = new  PluginResult(PluginResult.Status.OK);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);

            //Directory and File
            File dir = new File(dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dirName, fileName);

            //Avoid overwriting a file
            if (!overwrite && file.exists()) {

                return true;
            }

            //Decode Base64 back to Binary format
            byte[] decodedBytes = Base64.decode(b64String.getBytes(),Base64.DEFAULT);

            //Save Binary file to phone
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            fOut.write(decodedBytes);
            fOut.close();


            return true;

        } catch (FileNotFoundException e) {
            PluginResult pluginResult = new  PluginResult(PluginResult.Status.ERROR);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        } catch (IOException e) {
            PluginResult pluginResult = new  PluginResult(PluginResult.Status.ERROR);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        }

    }
}
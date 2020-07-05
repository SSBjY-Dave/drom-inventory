package hu.davidorcsik.dorm.inventory.logic;

import android.app.Application;
import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import hu.davidorcsik.dorm.inventory.Config;

public class RESTHelper {
    private static RESTHelper instance;
    public static RESTHelper getInstance() {
        if (instance != null) return instance;
        instance = new RESTHelper();
        return instance;
    }

    private HttpURLConnection getConnection(Context context, String restApiUrl) throws IOException {
        URL url = new URL(Config.getServerAddress(context) + "/" + restApiUrl);
        return (HttpURLConnection)url.openConnection();
    }

    private void sendPostMessage(Context context, String restApiUrl, byte[] message) throws IOException {
        HttpURLConnection httpConnection = getConnection(context, restApiUrl);

        try {
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpConnection.setDoOutput(true);
            httpConnection.setChunkedStreamingMode(0);
            OutputStream os = new BufferedOutputStream(httpConnection.getOutputStream());
            os.write(message);
            os.flush();
            new BufferedInputStream(httpConnection.getInputStream());
        } finally {
            httpConnection.disconnect();
        }
    }

    public void addQR(Context context, InventoryItem item) throws IOException {
        sendPostMessage(context, "addQR/", new ObjectMapper().writeValueAsBytes(item));
    }
}

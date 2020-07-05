package hu.davidorcsik.dorm.inventory;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class Config {
    private static final String SHARED_PREFERENCES_NAME = "DormInventoryConfig";
    private static final String CONFIG_KEY_PREFIX = Config.class.getName() + ".";

    private static final String SERVER_ADDRESS_KEY = "ServerAddress";
    private static final String SERVER_ADDRESS_DEFAULT = "";

    private static int getSharedPreferenceConfig(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    private static int[] getSharedPreferenceConfig(Context context, String key, int[] defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        int storedArrayLength = sharedPreferences.getInt(key + "_size", 0);
        if (storedArrayLength == 0) {
            // if config is not yet stored
            return defaultValue;
        }

        int[] values = new int[storedArrayLength];
        for (int i = 0; i < storedArrayLength; ++i) {
            int currentValue = sharedPreferences.getInt(key + "_" + i, -1);
            if (currentValue != -1) {
                values[i] = currentValue;
            } else {
                // if stored length is bigger than the actual length
                return defaultValue;
            }
        }

        return values;
    }

    private static String getSharedPreferenceConfig(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    private static void updateSharedPreferencesConfig(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private static void updateSharedPreferencesConfig(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private static void updateSharedPreferencesConfig(Context context, String key, Set<? extends Number> value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key + "_size", value.size());
        int i = 0;
        for (Number item : value) {
            editor.putInt(key + "_" + i, item.intValue());
            ++i;
        }
        editor.apply();
    }
    public static String getServerAddress(Context context) {
        return getSharedPreferenceConfig(context, SERVER_ADDRESS_KEY, SERVER_ADDRESS_DEFAULT);
    }

    public static void setServerAddress(Context context, String serverAddress) {
        updateSharedPreferencesConfig(context, SERVER_ADDRESS_KEY, serverAddress);
    }
}

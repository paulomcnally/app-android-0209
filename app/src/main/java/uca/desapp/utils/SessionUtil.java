package uca.desapp.utils;

import android.content.Context;
import android.content.Intent;

import com.tumblr.remember.Remember;

import uca.desapp.ui.activities.SignInActivity;

public class SessionUtil {

    public interface OnOpenActivity {
        void onOpen(boolean session);
    }

    public static final String ACCESS_TOKEN = "api_access_token";
    public static final String ACCESS_TOKEN_HEADER = "Authorization";

    private static OnOpenActivity listener;

    public void setListener(OnOpenActivity onOpenActivity) {
        listener = onOpenActivity;
    }

    public static String getAccessToken() {
        return Remember.getString(ACCESS_TOKEN, "");
    }

    public static boolean isActive() {
        return !getAccessToken().isEmpty();
    }

    public static void openActivity(Context context) {
        if (isActive()) {
            listener.onOpen(false);
        } else {
            Intent intent = new Intent(context, SignInActivity.class);
            context.startActivity(intent);
        }
    }

    public static void close(Remember.Callback callback) {
        if (isActive()) {
            Remember.remove(ACCESS_TOKEN, callback);
        }
    }
}
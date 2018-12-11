package app.xandone.ygame.utils;

import android.content.Context;

/**
 * author: xandone
 * created on: 2017/9/15 17:22
 */
public class Utils {

    public static int dp2px(Context context, int values) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }
}

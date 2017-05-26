package cn.dreamtobe.toolset;

import android.app.Notification;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * @author Jacks gong
 * @since 2014-1-8 下午7:31:31
 */
public class NotificationAdapter {

    private static Integer TITLE_COLOR = null;
    private static float TITLE_SIZE = 11;

    private static Integer CONTENT_COLOR = null;
    private static float CONTENT_SIZE = 11;

    private final static String TITLE_TEXT = "SOME-SAMPLE-TEXT";
    private final static String CONTENT_TEXT = "SOME-CONTENT-TEXT";

    /**
     * 适配多种通知栏，得到默认文字颜色
     *
     * @param context
     * @return 默认文字颜色
     */
    public static Integer getTitleColor(Context context) {
        extractColors(context);
        return TITLE_COLOR;
    }

    public static float getTitleSize(Context context) {
        extractColors(context);
        return TITLE_SIZE;
    }

    public static Integer getContentColor(Context context) {
        extractColors(context);
        return CONTENT_COLOR;
    }


    public static float getContentSize(Context context) {
        extractColors(context);
        return CONTENT_SIZE;
    }

    private static void recurseGroup(final ViewGroup viewGroup) {

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof TextView) {
                final TextView text = (TextView) v;
                final String szText = text.getText().toString();
                if (TITLE_TEXT.equals(szText)) {
                    TITLE_COLOR = text.getTextColors().getDefaultColor();
                    TITLE_SIZE = text.getTextSize();
                } else if (CONTENT_TEXT.equals(szText)) {
                    CONTENT_COLOR = text.getTextColors().getDefaultColor();
                    CONTENT_SIZE = text.getTextSize();
                }

                if (TITLE_COLOR != null && CONTENT_COLOR != null) {
                    return;
                }
            }

            if (v instanceof ViewGroup) {
                recurseGroup((ViewGroup) viewGroup.getChildAt(i));
            }
        }
    }

    /**
     * 枚举Notification颜色
     */
    private static synchronized void extractColors(final Context context) {
        if (context == null) throw new IllegalArgumentException();

        if (TITLE_COLOR != null) {
            return;
        }

        try {
            final Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle(TITLE_TEXT);
            builder.setContentText(CONTENT_TEXT);


            LinearLayout group = new LinearLayout(context);
            final RemoteViews tempView;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                tempView = builder.createContentView();
            } else {
                final Notification notification;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    notification = builder.build();
                } else {
                    notification = builder.getNotification();
                }
                tempView = notification.contentView;
            }

            ViewGroup event = (ViewGroup) tempView.apply(context, group);
            recurseGroup(event);
            group.removeAllViews();

        } catch (Exception e) {
            TITLE_COLOR = null;
        }
    }
}

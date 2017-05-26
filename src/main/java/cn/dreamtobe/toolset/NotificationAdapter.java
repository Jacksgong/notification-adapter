/*
 * Copyright (C) 2017 Jacksgong(blog.dreamtobe.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.dreamtobe.toolset;

import android.app.Notification;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * The adapter for notification, what is used for adapter various system styles on notification.
 */
public class NotificationAdapter {

    private static Integer TITLE_COLOR = null;
    private static float TITLE_SIZE = 0;

    private static Integer CONTENT_COLOR = null;
    private static float CONTENT_SIZE = 0;

    private final static String TITLE_TEXT = "SAMPLE-TITLE";
    private final static String CONTENT_TEXT = "SAMPLE-TEXT";

    /**
     * Get the color of title on default notification style.
     *
     * @return the color of title on default notification style.
     */
    public static Integer getTitleColor(Context context) {
        if (TITLE_COLOR == null) extractNotificationStyle(context);
        return TITLE_COLOR;
    }

    /**
     * Get the size(on pixel) of title on default notification style.
     *
     * @return the size(on pixel) of title on default notification style.
     */
    public static float getTitleSize(Context context) {
        if (TITLE_SIZE == 0) extractNotificationStyle(context);
        return TITLE_SIZE;
    }

    /**
     * Get the color of text on default notification style.
     *
     * @return the color of text on default notification style.
     */
    public static Integer getTextColor(Context context) {
        if (CONTENT_COLOR == null) extractNotificationStyle(context);
        return CONTENT_COLOR;
    }


    /**
     * Get the size(on pixel) of text on default notification style.
     *
     * @return the size(on pixel) of text on default notification style.
     */
    public static float getTextSize(Context context) {
        if (CONTENT_SIZE == 0) extractNotificationStyle(context);
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
     * Extract the style on notification and assign to params.
     */
    private static void extractNotificationStyle(final Context context) {
        if (context == null) throw new IllegalArgumentException();

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
                    //noinspection deprecation
                    notification = builder.getNotification();
                }
                //noinspection deprecation
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

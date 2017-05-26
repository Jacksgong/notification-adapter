import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.RemoteViews;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.dreamtobe.toolset.NotificationAdapter;
import cn.dreamtobe.toolset.test.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.util.TypedValue.COMPLEX_UNIT_PX;

/**
 * Created by Jacksgong on 18/05/2017.
 */

@RunWith(AndroidJUnit4.class)
public class NotificationAdapterTest {

    @Test
    public void testNotificationAdapter() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        RemoteViews contentView = new RemoteViews("cn.dreamtobe.toolset.test", R.layout.custom_layout);
        contentView.setTextViewText(R.id.title, "Custom notification");
        contentView.setTextViewText(R.id.text, "This is a custom layout");
        contentView.setTextColor(R.id.title, NotificationAdapter.getTitleColor(appContext));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            contentView.setTextViewTextSize(R.id.title, COMPLEX_UNIT_PX, NotificationAdapter.getTitleSize(appContext));
        }
        contentView.setTextColor(R.id.text, NotificationAdapter.getContentColor(appContext));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            contentView.setTextViewTextSize(R.id.text, COMPLEX_UNIT_PX, NotificationAdapter.getContentSize(appContext));
        }

        Notification notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        notification.contentView = contentView;

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        NotificationManager notifyMgr =
                (NotificationManager) appContext.getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(1, notification);

//        assertEquals("cn.dreamtobe.toolset", appContext.getPackageName());
    }
}

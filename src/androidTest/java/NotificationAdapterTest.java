import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.Until;
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
        final String NOTIFICATION_TEXT = "adapter-text";
        final String NOTIFICATION_TITLE = "adapter-title";
        final long TIMEOUT = 5000;

        Context appContext = InstrumentationRegistry.getTargetContext();

        RemoteViews contentView = new RemoteViews("cn.dreamtobe.toolset.test", R.layout.custom_layout);
        contentView.setTextViewText(R.id.title, NOTIFICATION_TITLE);
        contentView.setTextViewText(R.id.text, NOTIFICATION_TEXT);

        // Fix the Notification-Style problem ---------------
        // Set the default title style color to title view.
        contentView.setTextColor(R.id.title, NotificationAdapter.getTitleColor(appContext));
        // Set the default title style size to title view
        contentView.setTextViewTextSize(R.id.title, COMPLEX_UNIT_PX, NotificationAdapter.getTitleSize(appContext));
        // Set the default text style color to text view
        contentView.setTextColor(R.id.text, NotificationAdapter.getTextColor(appContext));
        // Set the default text style size to text view
        contentView.setTextViewTextSize(R.id.text, COMPLEX_UNIT_PX, NotificationAdapter.getTextSize(appContext));
        // End fix the Notification-Style problem ---------------

        Notification notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        notification.contentView = contentView;

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;

        NotificationManager notifyMgr =
                (NotificationManager) appContext.getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(1, notification);

        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.openNotification();
        device.wait(Until.hasObject(By.text(NOTIFICATION_TITLE)), TIMEOUT);
    }
}

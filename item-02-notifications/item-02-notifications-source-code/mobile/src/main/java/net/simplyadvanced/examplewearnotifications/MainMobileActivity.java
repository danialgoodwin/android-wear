package net.simplyadvanced.examplewearnotifications;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** The main page that shows to user when they open the app. */
public class MainMobileActivity extends Activity {

    /** An arbitrary number so that the same notification can be referenced
     * after user interacts with it. */
    private static final int EXAMPLE_NOTIFICATION_ID = 11111;

    /** An arbitrary number for referencing a particular event or action. */
    private static final int EXAMPLE_EVENT_ID = 32123;

    /** An arbitrary key reference to sent EXAMPLE_EVENT_ID through an Intent extra. */
    private static final String EXAMPLE_EVENT_KEY = "keyExampleEvent";

    private Button mSendNotificationButton;
    private EditText mSendNotificationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mobile);
        mSendNotificationButton = (Button) findViewById(R.id.sendNotificationButton);
        mSendNotificationInput = (EditText) findViewById(R.id.customNotificationInput);

        mSendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mSendNotificationInput.getText().toString();
                sendNotification("Dynamic message", message);
                showToast("Sent notification");
            }
        });
    }

    /** Creates and shows a notification to user. If a wearable is connected,
     * then the notification will be shown there also. */
    private void sendNotification(String title, String message) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)

                // When user clicks on notification this intent will be called.
                .setContentIntent(getPendingIntent());

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(EXAMPLE_NOTIFICATION_ID, notificationBuilder.build());
    }

    /** Returns an intent to open MainMobileActivity. */
    private PendingIntent getPendingIntent() {
        Intent viewIntent = new Intent(this, MainMobileActivity.class);
        viewIntent.putExtra(EXAMPLE_EVENT_KEY, EXAMPLE_EVENT_ID);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);
        return viewPendingIntent;
    }

    /** Shows a quick message to user. */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

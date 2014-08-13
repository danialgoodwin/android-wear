# Android Wear - Item 02 - Notifications #

Here's the most straight-forward notes you need to push a notification from the handheld app to the wearable app. Many notifications will be done this way because of the handheld's more power features and access to Internet (via WiFi and cell providers).

The code in here builds off of the code in (Item 01 - First setup and publish)[https://github.com/danialgoodwin/android-wear/tree/master/item-01-first-setup-and-publish]


## Basic Notifications ##
There are three places at a minimum to add code to be able to send a notification from the handheld to wearable: the handheld's build.grade, a class file for notification itself, and the layout XML for a button to activate the notification.

Note: The code following is the absolute minimum required. The attached source code use better practices and has more comments.

    // In the handheld's build.gradle file.
    compile "com.android.support:support-v4:20.0.+"
    
    // Add this as a child to the handheld's layout XML's root ViewGroup.
    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Send notification" />
    
    // The in handheld's Activity.
    public class MainMobileActivity extends Activity {
        
        /** An arbitrary number so that the same notification can be referenced
         * after user interacts with it. */
        private static final int EXAMPLE_NOTIFICATION_ID = 11111;
        
        /** An arbitrary number for referencing a particular event or action. */
        private static final int EXAMPLE_EVENT_ID = 32123;
        
        /** An arbitrary key reference to sent EXAMPLE_EVENT_ID through an Intent extra. */
        private static final String EXAMPLE_EVENT_KEY = "keyExampleEvent";
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_mobile);
            findViewById(R.id.sendNotificationButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendNotification("Dynamic message", message);
                }
        });
        
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
            return PendingIntent.getActivity(this, 0, viewIntent, 0);
        }
    }

## Further Resources ##
- https://developer.android.com/training/wearables/notifications/index.html

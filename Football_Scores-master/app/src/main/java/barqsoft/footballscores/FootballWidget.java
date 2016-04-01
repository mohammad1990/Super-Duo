package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.widget.ListView;
import android.widget.RemoteViews;

/**
 * Created by hamzaK on 25.2.2016.
 */
public class FootballWidget extends AppWidgetProvider {
    public static final String EXTRA_ITEM = "position";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
       // context.startService(new Intent(context, WidgetService.class));
        for (int id : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget);

            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            remoteViews.setEmptyView(R.id.list,R.id.empty_view);
            remoteViews.setRemoteAdapter(id, R.id.list, intent);




            Intent clickIntent = new Intent(context, MainActivity.class);
            clickIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           /* PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clickintent,
                    PendingIntent.FLAG_UPDATE_CURRENT);*/

            PendingIntent clickPI=PendingIntent
                    .getActivity(context, 0,
                            clickIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
            /* PendingIntent clickPI = PendingIntent.getBroadcast(context,
                    0,clickIntent,PendingIntent.FLAG_UPDATE_CURRENT);*/
            remoteViews.setPendingIntentTemplate(R.id.list, clickPI);

            appWidgetManager.updateAppWidget(id, remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
    @Override
    public void onReceive( Context context, Intent intent) {
        super.onReceive(context, intent);

        //if (scoresAdapter.ACTION_DATA_UPDATED.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                    new ComponentName(context, getClass()));
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list);
       // }
    }
}

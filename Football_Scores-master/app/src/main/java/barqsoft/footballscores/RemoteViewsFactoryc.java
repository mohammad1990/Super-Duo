package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import org.joda.time.LocalDate;

/**
 * Created by hamzaK on 11.3.2016.
 */
public class RemoteViewsFactoryc implements RemoteViewsService.RemoteViewsFactory {




    private static final String[] FOOTBALLSCORE_COLUMNS = {
            DatabaseContract.scores_table.MATCH_ID,
            DatabaseContract.scores_table.DATE_COL,
            DatabaseContract.scores_table.TIME_COL,
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL,
            DatabaseContract.scores_table.LEAGUE_COL,
            DatabaseContract.scores_table.MATCH_DAY

    };
    //DatabaseContract.scores_table._ID,
    // DatabaseContract.scores_table.LEAGUE_COL,
    // DatabaseContract.scores_table.DATE_COL,
    //DatabaseContract.scores_table.TIME_COL,
    //DatabaseContract.scores_table.MATCH_DAY
    //public static final int COL_DATE = 1;
    //public static final int MATCH_DAY = 2;
    // public static final int COL_LEAGUE = 5;
    // public static final int COL_HOME = 3;

    private static final int MATCH_ID = 0;
    private static final int HOME_COL = 3;
    private static final int AWAY_COL = 4;
    private static final int HOME_GOALS_COL = 5;
    private static final int AWAY_GOALS_COL = 6;


    private Cursor data = null;
    private Context mContext;
    private int mAppWidgetId;

    public RemoteViewsFactoryc(Context context, Intent intent) {
        this.mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
               AppWidgetManager.INVALID_APPWIDGET_ID);
    }
    @Override
    public void onCreate() {
// Nothing to do
    }

    @Override
    public void onDataSetChanged() {
        if (data != null) {
            data.close();
        }
        //DatabaseContract.scores_table.DATE_COL + "=?"
        final long identityToken = Binder.clearCallingIdentity();
        Uri uri = DatabaseContract.scores_table.buildScoreWithDate();
        long todayMillis = new LocalDate().toDateTimeAtStartOfDay().getMillis();
        data = mContext.getContentResolver().query(uri, FOOTBALLSCORE_COLUMNS, null

                , new String[]{Utilies.getDateMillisForQueryFormat(todayMillis)}, null);

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
        if (data != null) {
            data.close();
            data = null;
        }
    }

    @Override
    public int getCount() {
       // return (items.length);
        return data == null ? 0 : data.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION ||
                data == null || !data.moveToPosition(position)) {
            return null;
        }
        RemoteViews views = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_item);

         String homeTeam = data.getString(HOME_COL);
        String awayTeam = data.getString(AWAY_COL);
        String homeGoal = data.getString(HOME_GOALS_COL);
        String awayGoal = data.getString(AWAY_GOALS_COL);

        views.setTextViewText(R.id.tv_first_team, homeTeam);
        //views.setTextViewText(R.id.tv_first_team_goal, homeGoal);
        views.setTextViewText(R.id.tv_second_team, awayTeam);
        //views.setTextViewText(R.id.tv_second_team_goal, awayGoal);

        views.setImageViewResource(R.id.image_view_team, Utilies.getTeamCrestByTeamName(homeTeam));
        views.setImageViewResource(R.id.image_view_second_team, Utilies.getTeamCrestByTeamName(awayTeam));

       // views.setContentDescription(R.id.image_view_team, homeTeam + "image");
       // views.setContentDescription(R.id.image_view_second_team,awayTeam+"image");
        views.setContentDescription(R.id.tv_first_team,homeTeam);
        views.setContentDescription(R.id.tv_second_team,homeTeam);
        views.setContentDescription(R.id.list, "list of the teams");

        //final Intent i = new Intent();
        //final Bundle bundle = new Bundle();
       // bundle.putInt(FootballWidget.EXTRA_ITEM, data.getInt(MATCH_ID));
        //Intent fillInIntent = new Intent();
         Bundle extras = new Bundle();
         Intent fillInIntent = new Intent();

        extras.putString(FootballWidget.EXTRA_ITEM,String.valueOf(data.getCount()));
        fillInIntent.putExtras(extras);


       // Intent fillInIntent = new Intent(Intent.ACTION_MAIN);
       // fillInIntent.putExtra(FootballWidget.EXTRA_ITEM, position);
       // fillInIntent.putExtras(bundle);
        views.setOnClickFillInIntent(R.id.headerBarLinearLayout, fillInIntent);

//        Intent intent = new Intent(mContext, MainActivity.class);
  //      PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
     //   views.setOnClickPendingIntent(R.id.widget_item, pendingIntent);


        //Bundle extras = new Bundle();
      //  extras.putInt(WidgetProvider.EXTRA_ITEM, position);
        /*Intent fillInIntent = new Intent();
        Uri scoreUri = DatabaseContract.scores_table.buildScoreWithDate();
        fillInIntent.setData(scoreUri);*/
      //  views.setOnClickPendingIntent(R.id.list, pendingIntent);
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
         return new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        //
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        if (data.moveToPosition(position))
            return data.getInt(MATCH_ID);
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

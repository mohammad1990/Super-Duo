package barqsoft.footballscores;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies
{
    public static final int SERIE_A = 357;
    public static final int PREMIER_LEGAUE = 354;
    public static final int CHAMPIONS_LEAGUE = 362;
    public static final int PRIMERA_DIVISION = 358;
    public static final int BUNDESLIGA = 351;


    public static String getLeague(int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return "Seria A";
            case PREMIER_LEGAUE : return "Premier League";
            case CHAMPIONS_LEAGUE : return "UEFA Champions League";
            case PRIMERA_DIVISION : return "Primera Division";
            case BUNDESLIGA : return "Bundesliga";
            default: return "Not known League Please report";
        }
    }
    public static String getMatchDay(int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return "Group Stages, Matchday : 6";
            }
            else if(match_day == 7 || match_day == 8)
            {
                return "First Knockout round";
            }
            else if(match_day == 9 || match_day == 10)
            {
                return "QuarterFinal";
            }
            else if(match_day == 11 || match_day == 12)
            {
                return "SemiFinal";
            }
            else
            {
                return "Final";
            }
        }
        else
        {
            return "Matchday : " + String.valueOf(match_day);
        }
    }

   public static String getDateMillisForQueryFormat(long dateMillis) {
       Date fragmentdate = new Date(System.currentTimeMillis()+((2-2)*86400000));
       SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
      String mDate=mformat.format(fragmentdate);

   /*     LocalDate localDate = new LocalDate(dateMillis);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

        String dateForQueryFormat = fmt.print(localDate);*/
        return mDate;
    }
    /*public  static void   insertV(Context mContext)
    {
        Vector<ContentValues> values = new Vector <ContentValues> (1);
        ContentValues match_values = new ContentValues();
        match_values.put(DatabaseContract.scores_table.MATCH_ID,44);
        match_values.put(DatabaseContract.scores_table.DATE_COL,23);
        match_values.put(DatabaseContract.scores_table.TIME_COL,12);
        match_values.put(DatabaseContract.scores_table.HOME_COL,"ahmad");
        match_values.put(DatabaseContract.scores_table.AWAY_COL,"sdf");
        match_values.put(DatabaseContract.scores_table.HOME_GOALS_COL,3);
        match_values.put(DatabaseContract.scores_table.AWAY_GOALS_COL,2);
        match_values.put(DatabaseContract.scores_table.LEAGUE_COL,3);
        match_values.put(DatabaseContract.scores_table.MATCH_DAY,23);
        values.add(match_values);

        int inserted_data = 0;
        ContentValues[] insert_data = new ContentValues[values.size()];
        values.toArray(insert_data);
        inserted_data = mContext.getContentResolver().bulkInsert(
                DatabaseContract.BASE_CONTENT_URI,insert_data);

    }*/

   /* public static void scheduleUpdate(Context context) {
        *//*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String interval = prefs.getString(.INTERVAL_PREF, null);*//*
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, WidgetService.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 3 seconds
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 1000 * 3, 1000 , pi);
       *//* AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long intervalMillis = Integer.parseInt(interval)*60*1000;

        PendingIntent pi = getAlarmIntent(context);
        am.cancel(pi);
        am.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), intervalMillis, pi);*//*
    }

    private static PendingIntent getAlarmIntent(Context context) {
        Intent intent = new Intent(context, FootballWidget.class);
        intent.setAction(FootballWidget.ACTION_UPDATE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pi;
    }

    public static void clearUpdate(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(getAlarmIntent(context));
    }*/
    public static String getScores(int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }
    public static String getCurrentTime(String timeformat){
        Format formatter = new SimpleDateFormat(timeformat);
        return formatter.format(new Date());
    }
    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;

           case "vfl Bochum" : return R.drawable.vfl_bochum;
            case "FC Kaiserslautern" : return R.drawable.fc_kaiserslautern;
            case "Granada CF" : return R.drawable.granadacf;
            case "RCD Espanyol" : return R.drawable.rcd_espanyol;
            case "Leicester City FC" : return R.drawable.leicester_city_fc;
            case "Levante UD" : return R.drawable.levante_ud;
            case "Valencia CF" : return R.drawable.valencia_cf;
            //case "AC Chievo Verona" : return R.drawable.ac_ch;
            //case "AC Milan" : return R.drawable.arsenal;
            //case "Karlsruher SC" : return R.drawable.arsenal;
            case "1.FC Heidenheim" : return R.drawable.fc_heidenheim;
            case "FSV Frankfurt" : return R.drawable.fsv_frankfurt;



            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            default: return R.drawable.no_icon;
















        }
    }
}

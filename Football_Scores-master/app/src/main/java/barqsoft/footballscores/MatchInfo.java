package barqsoft.footballscores;

import android.database.Cursor;

/**
 * Created by hamzaK on 3.3.2016.
 */
public class MatchInfo
{
    public String league;
    public String date;
    public  String time;
    public String home;
    public  String away;
    public  String home_goals;
    public String away_goals;
    public String match_id;
    public String match_day;

    public static MatchInfo getFromCursor(Cursor c)
    {
        MatchInfo matchInfo = new MatchInfo();
        matchInfo.league=c.getString(c.getColumnIndex("league"));
        matchInfo.date=c.getString(c.getColumnIndex("date"));
        matchInfo.time=c.getString(c.getColumnIndex("time"));
        matchInfo.home=c.getString(c.getColumnIndex("home"));
        matchInfo.away=c.getString(c.getColumnIndex("away"));
        matchInfo.home_goals=c.getString(c.getColumnIndex("home_goals"));
        matchInfo.away_goals=c.getString(c.getColumnIndex("away_goals"));
        matchInfo.match_id=c.getString(c.getColumnIndex("match_id"));
        matchInfo.match_day=c.getString(c.getColumnIndex("match_day"));
        return  matchInfo;
    }


}

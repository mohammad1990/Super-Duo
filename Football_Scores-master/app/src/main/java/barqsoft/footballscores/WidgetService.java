package barqsoft.footballscores;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import org.joda.time.LocalDate;

/**
 * Created by hamzaK on 1.3.2016.
 */
public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return(new RemoteViewsFactoryc(this.getApplicationContext(),
                intent));
    }
}


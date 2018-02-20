package com.example.moaazfathy.bakingapp.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by MoaazFathy on 20-Feb-18.
 */

public class WidgetListViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetListViewFactory(this.getApplicationContext());
    }
}

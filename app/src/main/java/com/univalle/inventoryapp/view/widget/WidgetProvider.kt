package com.univalle.inventoryapp.view.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.univalle.inventoryapp.R

class WidgetProvider: AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        //appWidgetIds: IntArray?
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (widgetId in appWidgetIds) {
            val views = RemoteViews(context?.packageName, R.layout.widget)

            // Set text
            views.setTextViewText(R.id.widgetText, "Updated: ${System.currentTimeMillis()}")

            // Handle button click (broadcast intent)
            val intent = Intent(context, Widget::class.java).apply {
                action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
            }
//            context?.sendBroadcast(intent)//what?
            val pendingIntent = PendingIntent.getBroadcast(
                context, widgetId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
//            views.setOnClickPendingIntent(R.id.widgetButton, pendingIntent)
            views.setOnClickPendingIntent(R.id.widgetText, pendingIntent)

            appWidgetManager?.updateAppWidget(widgetId, views)
        }
    }
}
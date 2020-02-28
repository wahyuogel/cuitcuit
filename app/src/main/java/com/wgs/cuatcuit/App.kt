package com.wgs.cuatcuit

import android.app.Application
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.persistence.GanderPersistence

/**
 * Created by Alvin Rusli on 02/28/2020.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Gander.setGanderStorage(GanderPersistence.getInstance(this))
    }
}

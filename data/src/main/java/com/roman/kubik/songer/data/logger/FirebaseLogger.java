package com.roman.kubik.songer.data.logger;

import com.google.firebase.analytics.FirebaseAnalytics;

import com.roman.kubik.songer.domain.logger.Logger;
import com.roman.kubik.songer.domain.logger.event.CategoryEvent;
import com.roman.kubik.songer.domain.logger.event.SettingsEvent;

import android.os.Bundle;

import javax.inject.Inject;

public class FirebaseLogger implements Logger {

    private FirebaseAnalytics firebaseAnalytics;

    @Inject
    public FirebaseLogger(final FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

    @Override
    public void log(final CategoryEvent categoryEvent) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "category");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, categoryEvent.getCategoryName());
        firebaseAnalytics.logEvent("select_category", bundle);
    }

    @Override
    public void log(final SettingsEvent settingsEvent) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "settings");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, settingsEvent.getSelectedInstrument());
        bundle.putBoolean(FirebaseAnalytics.Param.ITEM_VARIANT, settingsEvent.isShowChord());
        firebaseAnalytics.logEvent("changed_settings", bundle);
    }
}

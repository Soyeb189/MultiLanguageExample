package com.bankasia.multilanguageexample;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

public class ContextUtils extends ContextWrapper {
    public ContextUtils(Context base) {
        super(base);
    }

    public static ContextWrapper updateLocale(Context context, Locale localeToSwitchTo) {
        Resources resources = context.getResources();
        Configuration configuration = new Configuration();
        configuration.locale = localeToSwitchTo;
        resources.updateConfiguration(configuration,context.getResources().getDisplayMetrics());
        //Configuration configuration = resources.getConfiguration(); // 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(localeToSwitchTo); // 2
            LocaleList.setDefault(localeList); // 3
            configuration.setLocales(localeList); // 4
        } else {
            configuration.locale = localeToSwitchTo; // 5
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            context = context.createConfigurationContext(configuration); // 6
        } else {
            resources.updateConfiguration(configuration, resources.getDisplayMetrics()); // 7
        }

        return new ContextUtils(context); // 8
    }
}

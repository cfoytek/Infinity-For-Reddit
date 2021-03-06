package ml.docilealligator.infinityforreddit.Settings;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import ml.docilealligator.infinityforreddit.Activity.LinkResolverActivity;
import ml.docilealligator.infinityforreddit.R;
import ml.docilealligator.infinityforreddit.Utils.SharedPreferencesUtils;

/**
 * A simple {@link PreferenceFragmentCompat} subclass.
 */
public class CreditsPreferenceFragment extends PreferenceFragmentCompat {

    private Activity activity;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.credits_preferences, rootKey);

        Preference iconForegroundPreference = findPreference(SharedPreferencesUtils.ICON_FOREGROUND_KEY);
        Preference iconBackgroundPreference = findPreference(SharedPreferencesUtils.ICON_BACKGROUND_KEY);
        Preference errorImagePreference = findPreference(SharedPreferencesUtils.ERROR_IMAGE_KEY);
        Preference gildedIconPreference = findPreference(SharedPreferencesUtils.GILDED_ICON_KEY);
        Preference crosspostIconPreference = findPreference(SharedPreferencesUtils.CROSSPOST_ICON_KEY);
        Preference thumbtackIconPreference = findPreference(SharedPreferencesUtils.THUMBTACK_ICON_KEY);
        Preference materialIconsPreference = findPreference(SharedPreferencesUtils.MATERIAL_ICONS_KEY);

        if (iconForegroundPreference != null) {
            iconForegroundPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(activity, LinkResolverActivity.class);
                intent.setData(Uri.parse("https://www.freepik.com/free-photos-vectors/technology"));
                startActivity(intent);
                return true;
            });
        }

        if (iconBackgroundPreference != null) {
            iconBackgroundPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(activity, LinkResolverActivity.class);
                intent.setData(Uri.parse("https://www.freepik.com/free-photos-vectors/background"));
                startActivity(intent);
                return true;
            });
        }

        if (errorImagePreference != null) {
            errorImagePreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(activity, LinkResolverActivity.class);
                intent.setData(Uri.parse("https://www.freepik.com/free-photos-vectors/technology"));
                startActivity(intent);
                return true;
            });
        }

        if (gildedIconPreference != null) {
            gildedIconPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(activity, LinkResolverActivity.class);
                intent.setData(Uri.parse("https://br.flaticon.com/icone-gratis/medalha_1007239"));
                startActivity(intent);
                return true;
            });
        }

        if (crosspostIconPreference != null) {
            crosspostIconPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(activity, LinkResolverActivity.class);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icon/crossed-arrows_2291"));
                startActivity(intent);
                return true;
            });
        }

        if (thumbtackIconPreference != null) {
            thumbtackIconPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(activity, LinkResolverActivity.class);
                intent.setData(Uri.parse("https://www.flaticon.com/free-icon/tack-save-button_61845#term=thumbtack&page=1&position=3"));
                startActivity(intent);
                return true;
            });
        }

        if (materialIconsPreference != null) {
            materialIconsPreference.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(activity, LinkResolverActivity.class);
                intent.setData(Uri.parse("https://material.io/resources/icons/"));
                startActivity(intent);
                return true;
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }
}

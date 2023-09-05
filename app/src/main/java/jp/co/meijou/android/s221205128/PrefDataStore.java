package jp.co.meijou.android.s221205128;

import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

import java.util.Optional;

import io.reactivex.rxjava3.core.Single;


public class PrefDataStore {
    private static PrefDataStore instance;
    private final RxDataStore<Preferences> dataStore;

    private PrefDataStore(RxDataStore<Preferences> dataStore){
        this.dataStore = dataStore;
    }

    public static PrefDataStore getInstance(Context context){
        if(instance == null){


            var dataStore = new RxPreferenceDataStoreBuilder(
                    context.getApplicationContext(), "settings").build();
            instance = new PrefDataStore(dataStore);

        }
        return instance;
    }

    public void setString(String key, String value){
        dataStore.updateDataAsync(prefsIn -> {
            var mutablePreferences = prefsIn.toMutablePreferences();
            var prefKey = PreferencesKeys.stringKey(key);


            mutablePreferences.set(prefKey, value);
            return Single.just(mutablePreferences);
        }).subscribe();
    }

    //値がないかもしれないのでOptionalで返す
    public Optional<String> getString(String key){
        return dataStore.data().map(prefs ->{
            //入れてある型にあったPreferences.Key のインスタンスを作成
            var prefKey = PreferencesKeys.stringKey(key);
            //null を返すとクラッシュするので Optional でラップする
            return Optional.ofNullable(prefs.get(prefKey));
            //RxJavaで最初の値を返す
        }).blockingFirst();
    }
}

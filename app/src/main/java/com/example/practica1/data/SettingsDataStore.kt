package com.example.practica1.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        val THEME_KEY = booleanPreferencesKey("dark_theme")
        val VOLUME_KEY = floatPreferencesKey("volume")
    }

    // Guardar tema
    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = isDark
        }
    }

    // Guardar volumen
    suspend fun saveVolume(volume: Float) {
        context.dataStore.edit { prefs ->
            prefs[VOLUME_KEY] = volume
        }
    }

    // Leer tema
    val themeFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[THEME_KEY] ?: false
    }

    // Leer volumen
    val volumeFlow: Flow<Float> = context.dataStore.data.map { prefs ->
        prefs[VOLUME_KEY] ?: 0.5f
    }
}
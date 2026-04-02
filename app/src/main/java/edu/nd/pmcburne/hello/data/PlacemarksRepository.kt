package edu.nd.pmcburne.hello.data

import android.text.Html
import android.util.Log
import edu.nd.pmcburne.hello.network.PlacemarksApi

class PlacemarksRepository(
    private val dao: PlacemarkDao,
    private val api: PlacemarksApi
) {
    suspend fun syncPlacemarks() {
        try {
            val response = api.getPlacemarks()
            val entities = response.mapNotNull { placemark ->
                val center = placemark.visualCenter ?: return@mapNotNull null
                val rawDesc = placemark.description ?: ""
                val cleanDesc = Html.fromHtml(rawDesc, Html.FROM_HTML_MODE_LEGACY).toString()
                PlacemarkEntity(
                    id = placemark.id,
                    name = placemark.name,
                    description = cleanDesc,
                    latitude = center.latitude,
                    longitude = center.longitude,
                    tags = placemark.tagList.joinToString(",")
                )
            }
            dao.insertAll(entities)
            Log.d("PlacemarksRepository", "Synced ${entities.size} placemarks")
        } catch (e: Exception) {
            Log.e("PlacemarksRepository", "Failed to sync placemarks", e)
        }
    }

    suspend fun getAllUniqueTags(): List<String> {
        return dao.getAllTagStrings()
            .flatMap { it.split(",") }
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .distinct()
            .sorted()
    }

    suspend fun getPlacemarksByTag(tag: String): List<PlacemarkEntity> {
        return dao.getPlacemarksByTag(tag)
            .filter { entity ->
                entity.tags.split(",").map { it.trim() }.contains(tag)
            }
    }
}

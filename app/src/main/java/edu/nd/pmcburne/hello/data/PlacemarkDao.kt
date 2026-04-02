package edu.nd.pmcburne.hello.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlacemarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(placemarks: List<PlacemarkEntity>)

    @Query("SELECT DISTINCT tags FROM placemarks")
    suspend fun getAllTagStrings(): List<String>

    @Query("SELECT * FROM placemarks WHERE tags LIKE '%' || :tag || '%'")
    suspend fun getPlacemarksByTag(tag: String): List<PlacemarkEntity>

    @Query("SELECT COUNT(*) FROM placemarks")
    suspend fun getCount(): Int
}

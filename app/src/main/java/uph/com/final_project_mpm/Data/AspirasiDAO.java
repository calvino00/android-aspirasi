package uph.com.final_project_mpm.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import uph.com.final_project_mpm.Model.Aspirasi;

@Dao
public interface AspirasiDAO {

    @Insert
    long insertAspi(Aspirasi aspirasi);

    @Delete
    int deleteAspi(Aspirasi aspirasi);

    @Query("Select * from tAspirasi")
    Aspirasi[] SelectAllAspirasi();
}

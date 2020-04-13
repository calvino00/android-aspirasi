package uph.com.final_project_mpm.Data.factory;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import uph.com.final_project_mpm.Data.AspirasiDAO;
import uph.com.final_project_mpm.Data.UserDAO;
import uph.com.final_project_mpm.Model.Aspirasi;
import uph.com.final_project_mpm.Model.User;

@Database(entities = {User.class, Aspirasi.class}, version = 1 ,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AspirasiDAO AspirasiDAO();
    public abstract UserDAO UserDAO();
}

package uph.com.final_project_mpm.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

import uph.com.final_project_mpm.Model.User;

@Dao
public interface UserDAO {

    @Insert
    long insertUser(User user);
    @Update
    int updateUser(User user);
}

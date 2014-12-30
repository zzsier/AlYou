package com.imalu.alyou.db;

import java.util.List;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.db.gen.Clock;
import com.imalu.alyou.db.gen.ClockDao;
import com.imalu.alyou.db.gen.DaoSession;
import com.imalu.alyou.db.gen.User;
import com.imalu.alyou.db.gen.UserDao;

import android.content.Context;
import android.util.Log;

public class UserDbService {
	private static final String TAG = UserDbService.class.getSimpleName();  
    private static UserDbService instance;  
    private static Context appContext;  
    private DaoSession mDaoSession;  
    private UserDao userDao;  
      
      
    private UserDbService() {  
    }  
  
    public static UserDbService getInstance(Context context) {  
        if (instance == null) {  
            instance = new UserDbService();  
            if (appContext == null){  
                appContext = context.getApplicationContext();  
            }  
            instance.mDaoSession = AlUApplication.getDaoSession(context);  
            instance.userDao = instance.mDaoSession.getUserDao();  
        }  
        return instance;  
    }  
      
      
    public User loadUser(long id) {  
        return userDao.load(id);  
    }
      
    public List<User> loadAllUser(){  
        return userDao.loadAll();  
    }
      
      
    /** 
     * insert or update note 
     * @param clock 
     * @return insert or update clock id 
     */  
    public long saveUser(User user){  
        return userDao.insertOrReplace(user);  
    } 
      
    /** 
     * delete note by id 
     * @param id 
     */  
    public void deleteUser(long id){  
    	userDao.deleteByKey(id);  
        Log.i(TAG, "delete");  
    }  
      
    public void deleteNote(User user){  
    	userDao.delete(user);  
    }  

}

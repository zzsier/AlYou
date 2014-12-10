package com.imalu.alyou.db;

import java.util.List;

import com.imalu.alyou.AlUApplication;
import com.imalu.alyou.db.gen.Clock;
import com.imalu.alyou.db.gen.ClockDao;
import com.imalu.alyou.db.gen.DaoSession;

import android.content.Context;
import android.util.Log;

public class ClockDbService {
	private static final String TAG = ClockDbService.class.getSimpleName();  
    private static ClockDbService instance;  
    private static Context appContext;  
    private DaoSession mDaoSession;  
    private ClockDao clockDao;  
      
      
    private ClockDbService() {  
    }  
  
    public static ClockDbService getInstance(Context context) {  
        if (instance == null) {  
            instance = new ClockDbService();  
            if (appContext == null){  
                appContext = context.getApplicationContext();  
            }  
            instance.mDaoSession = AlUApplication.getDaoSession(context);  
            instance.clockDao = instance.mDaoSession.getClockDao();  
        }  
        return instance;  
    }  
      
      
    public Clock loadClock(long id) {  
        return clockDao.load(id);  
    }  
      
    public List<Clock> loadAllClock(){  
        return clockDao.loadAll();  
    }  
      
    /** 
     * query list with where clause 
     * ex: begin_date_time >= ? AND end_date_time <= ? 
     * @param where where clause, include 'where' word 
     * @param params query parameters 
     * @return 
     */  
      
    public List<Clock> queryClock(String where, String... params){  
        return clockDao.queryRaw(where, params);  
    }  
      
      
    /** 
     * insert or update note 
     * @param clock 
     * @return insert or update clock id 
     */  
    public long saveClock(Clock clock){  
        return clockDao.insertOrReplace(clock);  
    }  
      
      
    /** 
     * insert or update clockList use transaction 
     * @param list 
     */  
    public void saveClockLists(final List<Clock> list){  
            if(list == null || list.isEmpty()){  
                 return;  
            }  
            clockDao.getSession().runInTx(new Runnable() {  
            @Override  
            public void run() {  
                for(int i=0; i<list.size(); i++){  
                	Clock clock = list.get(i);  
                	clockDao.insertOrReplace(clock);  
                }  
            }  
        });  
          
    }  
      
    /** 
     * delete all note 
     */  
    public void deleteAllNote(){  
    	clockDao.deleteAll();  
    }  
      
    /** 
     * delete note by id 
     * @param id 
     */  
    public void deleteNote(long id){  
    	clockDao.deleteByKey(id);  
        Log.i(TAG, "delete");  
    }  
      
    public void deleteNote(Clock clock){  
    	clockDao.delete(clock);  
    }  

}

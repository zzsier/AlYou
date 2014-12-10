package com.imalu.alyou.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.imalu.alyou.db.dao.AbstractDao;
import com.imalu.alyou.db.dao.Property;
import com.imalu.alyou.db.dao.internal.DaoConfig;

import com.imalu.alyou.db.gen.Clock;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CLOCK.
*/
public class ClockDao extends AbstractDao<Clock, Long> {

    public static final String TABLENAME = "CLOCK";

    /**
     * Properties of entity Clock.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Description = new Property(1, String.class, "description", false, "DESCRIPTION");
        public final static Property Type = new Property(2, Short.class, "type", false, "TYPE");
        public final static Property Order = new Property(3, Short.class, "order", false, "ORDER");
        public final static Property Pic = new Property(4, byte[].class, "pic", false, "PIC");
        public final static Property Readonly = new Property(5, Boolean.class, "readonly", false, "READONLY");
        public final static Property Clock1 = new Property(6, Integer.class, "clock1", false, "CLOCK1");
        public final static Property Clock2 = new Property(7, Integer.class, "clock2", false, "CLOCK2");
        public final static Property Clock3 = new Property(8, Integer.class, "clock3", false, "CLOCK3");
    };


    public ClockDao(DaoConfig config) {
        super(config);
    }
    
    public ClockDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CLOCK' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'DESCRIPTION' TEXT," + // 1: description
                "'TYPE' INTEGER," + // 2: type
                "'ORDER' INTEGER," + // 3: order
                "'PIC' BLOB," + // 4: pic
                "'READONLY' INTEGER," + // 5: readonly
                "'CLOCK1' INTEGER," + // 6: clock1
                "'CLOCK2' INTEGER," + // 7: clock2
                "'CLOCK3' INTEGER);"); // 8: clock3
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CLOCK'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Clock entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(2, description);
        }
 
        Short type = entity.getType();
        if (type != null) {
            stmt.bindLong(3, type);
        }
 
        Short order = entity.getOrder();
        if (order != null) {
            stmt.bindLong(4, order);
        }
 
        byte[] pic = entity.getPic();
        if (pic != null) {
            stmt.bindBlob(5, pic);
        }
 
        Boolean readonly = entity.getReadonly();
        if (readonly != null) {
            stmt.bindLong(6, readonly ? 1l: 0l);
        }
 
        Integer clock1 = entity.getClock1();
        if (clock1 != null) {
            stmt.bindLong(7, clock1);
        }
 
        Integer clock2 = entity.getClock2();
        if (clock2 != null) {
            stmt.bindLong(8, clock2);
        }
 
        Integer clock3 = entity.getClock3();
        if (clock3 != null) {
            stmt.bindLong(9, clock3);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Clock readEntity(Cursor cursor, int offset) {
        Clock entity = new Clock( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // description
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2), // type
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3), // order
            cursor.isNull(offset + 4) ? null : cursor.getBlob(offset + 4), // pic
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // readonly
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // clock1
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // clock2
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8) // clock3
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Clock entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDescription(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setType(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2));
        entity.setOrder(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3));
        entity.setPic(cursor.isNull(offset + 4) ? null : cursor.getBlob(offset + 4));
        entity.setReadonly(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setClock1(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setClock2(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setClock3(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Clock entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Clock entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}

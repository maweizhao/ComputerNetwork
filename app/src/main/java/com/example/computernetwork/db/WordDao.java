package com.example.computernetwork.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.computernetwork.Word;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORD".
*/
public class WordDao extends AbstractDao<Word, Integer> {

    public static final String TABLENAME = "WORD";

    /**
     * Properties of entity Word.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, int.class, "ID", true, "ID");
        public final static Property Abbreviations = new Property(1, String.class, "abbreviations", false, "ABBREVIATIONS");
        public final static Property English = new Property(2, String.class, "English", false, "ENGLISH");
        public final static Property Chinese = new Property(3, String.class, "Chinese", false, "CHINESE");
        public final static Property Explain = new Property(4, String.class, "explain", false, "EXPLAIN");
    }


    public WordDao(DaoConfig config) {
        super(config);
    }
    
    public WordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORD\" (" + //
                "\"ID\" INTEGER PRIMARY KEY NOT NULL ," + // 0: ID
                "\"ABBREVIATIONS\" TEXT," + // 1: abbreviations
                "\"ENGLISH\" TEXT," + // 2: English
                "\"CHINESE\" TEXT," + // 3: Chinese
                "\"EXPLAIN\" TEXT);"); // 4: explain
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Word entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getID());
 
        String abbreviations = entity.getAbbreviations();
        if (abbreviations != null) {
            stmt.bindString(2, abbreviations);
        }
 
        String English = entity.getEnglish();
        if (English != null) {
            stmt.bindString(3, English);
        }
 
        String Chinese = entity.getChinese();
        if (Chinese != null) {
            stmt.bindString(4, Chinese);
        }
 
        String explain = entity.getExplain();
        if (explain != null) {
            stmt.bindString(5, explain);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Word entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getID());
 
        String abbreviations = entity.getAbbreviations();
        if (abbreviations != null) {
            stmt.bindString(2, abbreviations);
        }
 
        String English = entity.getEnglish();
        if (English != null) {
            stmt.bindString(3, English);
        }
 
        String Chinese = entity.getChinese();
        if (Chinese != null) {
            stmt.bindString(4, Chinese);
        }
 
        String explain = entity.getExplain();
        if (explain != null) {
            stmt.bindString(5, explain);
        }
    }

    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.getInt(offset + 0);
    }    

    @Override
    public Word readEntity(Cursor cursor, int offset) {
        Word entity = new Word( //
            cursor.getInt(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // abbreviations
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // English
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Chinese
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // explain
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Word entity, int offset) {
        entity.setID(cursor.getInt(offset + 0));
        entity.setAbbreviations(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEnglish(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setChinese(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setExplain(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Integer updateKeyAfterInsert(Word entity, long rowId) {
        return entity.getID();
    }
    
    @Override
    public Integer getKey(Word entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Word entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
package database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.said.homework.news.data.model.ArticleDB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "article".
*/
public class ArticleDBDao extends AbstractDao<ArticleDB, Long> {

    public static final String TABLENAME = "article";

    /**
     * Properties of entity ArticleDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ArticleSourceID = new Property(1, Long.class, "articleSourceID", false, "ARTICLE_SOURCE_ID");
        public final static Property Author = new Property(2, String.class, "author", false, "AUTHOR");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Description = new Property(4, String.class, "description", false, "DESCRIPTION");
        public final static Property Url = new Property(5, String.class, "url", false, "URL");
        public final static Property UrlToImage = new Property(6, String.class, "urlToImage", false, "URL_TO_IMAGE");
        public final static Property Content = new Property(7, String.class, "content", false, "CONTENT");
        public final static Property PublishedAt = new Property(8, java.util.Date.class, "publishedAt", false, "PUBLISHED_AT");
    }


    public ArticleDBDao(DaoConfig config) {
        super(config);
    }
    
    public ArticleDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"article\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ARTICLE_SOURCE_ID\" INTEGER," + // 1: articleSourceID
                "\"AUTHOR\" TEXT," + // 2: author
                "\"TITLE\" TEXT," + // 3: title
                "\"DESCRIPTION\" TEXT," + // 4: description
                "\"URL\" TEXT," + // 5: url
                "\"URL_TO_IMAGE\" TEXT," + // 6: urlToImage
                "\"CONTENT\" TEXT," + // 7: content
                "\"PUBLISHED_AT\" INTEGER);"); // 8: publishedAt
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"article\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ArticleDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long articleSourceID = entity.getArticleSourceID();
        if (articleSourceID != null) {
            stmt.bindLong(2, articleSourceID);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
 
        String urlToImage = entity.getUrlToImage();
        if (urlToImage != null) {
            stmt.bindString(7, urlToImage);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(8, content);
        }
 
        java.util.Date publishedAt = entity.getPublishedAt();
        if (publishedAt != null) {
            stmt.bindLong(9, publishedAt.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ArticleDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long articleSourceID = entity.getArticleSourceID();
        if (articleSourceID != null) {
            stmt.bindLong(2, articleSourceID);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
 
        String urlToImage = entity.getUrlToImage();
        if (urlToImage != null) {
            stmt.bindString(7, urlToImage);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(8, content);
        }
 
        java.util.Date publishedAt = entity.getPublishedAt();
        if (publishedAt != null) {
            stmt.bindLong(9, publishedAt.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ArticleDB readEntity(Cursor cursor, int offset) {
        ArticleDB entity = new ArticleDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // articleSourceID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // author
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // description
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // url
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // urlToImage
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // content
            cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)) // publishedAt
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ArticleDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setArticleSourceID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setAuthor(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUrl(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUrlToImage(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setContent(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPublishedAt(cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ArticleDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ArticleDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ArticleDB entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
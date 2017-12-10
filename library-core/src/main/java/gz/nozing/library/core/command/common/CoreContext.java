package gz.nozing.library.core.command.common;

import com.mongodb.client.MongoDatabase;

public interface CoreContext {

    public MongoDatabase getDatabase ();
}

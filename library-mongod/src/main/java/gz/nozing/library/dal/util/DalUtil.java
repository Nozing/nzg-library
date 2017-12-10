package gz.nozing.library.dal.util;

import org.bson.Document;

public class DalUtil {

    public static Document generateCommonRegex(String value) {
	
	Document exp = new Document("$regex", "^" + value);
	exp.put("$options", "i");
	    
	return exp;
    }
}

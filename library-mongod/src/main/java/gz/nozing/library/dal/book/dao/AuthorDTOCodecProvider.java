/**
 * 
 */
package gz.nozing.library.dal.book.dao;

import gz.nozing.library.dal.book.AuthorDTO;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * @author nozing
 *
 */
public class AuthorDTOCodecProvider implements CodecProvider {

    /* (non-Javadoc)
     * @see org.bson.codecs.configuration.CodecProvider#get(java.lang.Class, org.bson.codecs.configuration.CodecRegistry)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {

	if (clazz == AuthorDTO.class) {
            return (Codec<T>) new AuthorDTOCodec(registry);
        }
        return null;
    }

}

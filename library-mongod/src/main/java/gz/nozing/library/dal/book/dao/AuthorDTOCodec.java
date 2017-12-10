/**
 * 
 */
package gz.nozing.library.dal.book.dao;

import gz.nozing.library.dal.book.AuthorDTO;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * @author nozing
 *
 */
public class AuthorDTOCodec implements Codec<AuthorDTO> {

    private CodecRegistry codecRegistry;

    /**
     * 
     */
    public AuthorDTOCodec() {
	super();
    }
    
    /* FIXME Elminar */
    public AuthorDTOCodec(final CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    /* (non-Javadoc)
     * @see org.bson.codecs.Encoder#encode(org.bson.BsonWriter, java.lang.Object, org.bson.codecs.EncoderContext)
     */
    @Override
    public void encode(BsonWriter writer, AuthorDTO t,
	    EncoderContext encoderContext) {
	
	 writer.writeStartDocument();
	 
         writer.writeString("authorId", t.getAuthorId());
         writer.writeString("name", t.getName());
         if (t.getSurname() != null) {
             writer.writeString("surname", t.getSurname());
         }

         writer.writeEndDocument();
    }

    /* (non-Javadoc)
     * @see org.bson.codecs.Encoder#getEncoderClass()
     */
    @Override
    public Class<AuthorDTO> getEncoderClass() {
	
	return AuthorDTO.class;
    }

    /* (non-Javadoc)
     * @see org.bson.codecs.Decoder#decode(org.bson.BsonReader, org.bson.codecs.DecoderContext)
     */
    @Override
    public AuthorDTO decode(BsonReader reader, DecoderContext decoderContext) {
	
	reader.readStartDocument();
	        
	AuthorDTO author = new AuthorDTO(reader.readString("authorId"),
		reader.readString("name"));

	author.setSurname(reader.readString("surname"));
	
        reader.readEndDocument();
    
        return author;
    }
}

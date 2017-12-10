/**
 * 
 */
package gz.nozing.library.dal.common.pagination;

/**
 * @author nozing
 * @param <T>
 * 
 */
public class PaginationSearchDTO<T> extends PaginationDTO<T> {

    private static final long serialVersionUID = -6028960482775701423L;

    private T searchObj;

    /**
     * @param searchObj
     * @param intitalPosition
     * @param pageSize
     */
    public PaginationSearchDTO(T searchObj, Integer intitalPosition, Integer pageSize) {
	super(intitalPosition, pageSize);

	this.searchObj = searchObj;
    }

    /**
     * @return the searchObj
     */
    public T getSearchObj() {
	return searchObj;
    }

    /**
     * @param searchObj
     *            the searchObj to set
     */
    public void setSearchObj(T searchObj) {
	this.searchObj = searchObj;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	
	StringBuilder builder = new StringBuilder();
	builder.append("PaginationSearchDTO [searchObj=").append(searchObj)
		.append(", getInitialPosition()=").append(getInitialPosition())
		.append(", getPageSize()=").append(getPageSize()).append("]");
	return builder.toString();
    }
}

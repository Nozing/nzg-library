/**
 * 
 */
package gz.nozing.library.dal.common.pagination;

import java.util.Collection;

/**
 * @author nozing
 * @param <T>
 *
 */
public class PaginationResultDTO<T> extends PaginationDTO<T> {

    private static final long serialVersionUID = -8317083076104046604L;

    private Collection<T> result;
    private Integer numberOfResults;
    
    /**
     * @param initialPosition
     * @param pageSize
     * @param numberOfResult
     */
    public PaginationResultDTO(Integer initialPosition, Integer pageSize, Integer numberOfResult) {
	super(initialPosition, pageSize);
	
	this.numberOfResults = numberOfResult;
    }
	
    /**
     * @return the result
     */
    public Collection<T> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Collection<T> result) {
        this.result = result;
    }
    
    /**
     * @return
     */
    public Integer getNumberOfResults() {
	return numberOfResults;
    }
    
    /**
     * @return Returns if this results are the results of the last page
     */
    public boolean isLastPage() {
	
	return (this.getInitialPosition() * this.getPageSize()) >= this.numberOfResults;
    }
}

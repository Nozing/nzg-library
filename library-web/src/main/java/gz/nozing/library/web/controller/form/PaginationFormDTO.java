/**
 * 
 */
package gz.nozing.library.web.controller.form;

import java.util.LinkedList;
import java.util.List;

/**
 * @author nozing
 *
 */
public abstract class PaginationFormDTO extends BaseFormDTO {

	private static final long serialVersionUID = 7591357595560550790L;

	private List<Integer> pagesSize;
	
	//TODO mover el 'total' al paginationFormResult
	private Integer total;
	private Integer initial;
	private Integer pageSize;

	/**
	 * 
	 */
	public PaginationFormDTO() { 
		super();
		
		this.total = 0;
		this.initial = 1;
		this.pageSize = 10;
		
		this.pagesSize = new LinkedList<Integer>();
		pagesSize.add(-1);
		pagesSize.add(2);
		pagesSize.add(10);
		pagesSize.add(25);
		pagesSize.add(50);
	}
	
	/**
	 * @param total
	 * @param initial
	 * @param pageSize
	 */
	public PaginationFormDTO(Integer total, Integer initial, Integer pageSize) {
		this();
		
		this.total = total;
		this.initial = initial;
		this.pageSize = pageSize;
	}
	
	/**
	 * @param initial
	 * @param pageSize
	 */
	public PaginationFormDTO(Integer initial, Integer pageSize) {
		this(0, initial, pageSize);		
	}

	
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getInitial() {
		return initial;
	}

	public void setInitial(Integer initial) {
		this.initial = initial;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<Integer> getPagesSize() {
		return pagesSize;
	}
}

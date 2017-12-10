/**
 * 
 */
package gz.nozing.library.web.controller.form;

import java.util.Collection;

/**
 * @author nozing
 *
 * @param <T>
 */
public class PaginationFormResultDTO<T> extends PaginationFormDTO {

	private static final long serialVersionUID = -4345575020432715989L;

	private Collection<T> result;
	
	/**
	 * 
	 */
	public PaginationFormResultDTO() {
		super();
	}
	
	/**
	 * @param total
	 * @param initial
	 * @param pageSize
	 */
	public PaginationFormResultDTO(Integer total, Integer initial,
			Integer pageSize) {
		super(total, initial, pageSize);
	}
	
	/**
	 * @return
	 */
	public Collection<T> getResult() {
		return result;
	}
	
	/**
	 * @param result
	 */
	public void setResult(Collection<T> result) {
		this.result = result;
	}
}

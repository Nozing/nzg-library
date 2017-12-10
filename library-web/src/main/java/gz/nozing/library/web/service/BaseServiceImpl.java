/**
 * 
 */
package gz.nozing.library.web.service;

import gz.nozing.library.core.command.common.CoreContext;

/**
 * @author nozing
 *
 */
public abstract class BaseServiceImpl {

	private CoreContext coreContext;
	
	/**
	 * @return
	 */
	protected CoreContext getCoreContext() {
		return coreContext;
	}
	
	public void setCoreContext(CoreContext coreContext) {
		this.coreContext = coreContext;
	}
}

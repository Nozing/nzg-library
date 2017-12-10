/**
 * 
 */
package gz.nozing.library.core.command.common;

import gz.nozing.library.common.utils.exception.runtime.InstantiatingClassException;
import gz.nozing.library.core.exception.CoreException;
import gz.nozing.library.dal.common.BaseDAO;
import gz.nozing.library.dal.util.DaoFactory;

import org.apache.log4j.Logger;

/**
 * @author nozing
 * 
 */
public abstract class BaseCoreCmd<T> {

	private static Logger log = Logger.getLogger(BaseCoreCmd.class);

	private CoreContext coreContext;

	/**
	 * @return the coreContext
	 */
	public CoreContext getCoreContext() {
		return this.coreContext;
	}

	/**
	 * @param coreContext
	 *            the coreContext to set
	 */
	public void setCoreContext(CoreContext coreContext) {
		this.coreContext = coreContext;
	}

	public T preProcess() throws CoreException {

		return null;
	}

	protected abstract T process() throws CoreException;

	public void postProcess(T result) throws CoreException {

	}

	public T execute() throws CoreException {

		T result = // this.preProcess();
		this.process();

		// this.postProcess(result);

		return result;
	}

	protected <I extends BaseDAO<?>> BaseDAO<?> getDAO(Class<I> clazz)
			throws CoreException {

		try {

			log.trace("Asking for dao class '" + clazz + "'");
			BaseDAO<?> dao = DaoFactory.getDAO(clazz);

			dao.setDatabase(this.coreContext.getDatabase());

			return dao;

		} catch (InstantiatingClassException e) {

			throw new CoreException("Can't instantite class '"
					+ clazz.getName() + "'", e);
		}
	}
}

/**
 * 
 */
package gz.nozing.library.dal.common.annotation.mongo;

/**
 * @author nozing
 *
 */
public enum DBAction {

    MODIFIYING(1),
    QUERYING(2);

    private Integer code;

    /**
     * @param code
     */
    private DBAction(Integer code) {
	this.code = code;
    }

    public Integer getCode() {
	return this.code;
    }
}

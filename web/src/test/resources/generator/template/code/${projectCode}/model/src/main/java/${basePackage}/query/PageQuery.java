package ${basePackage}.query;

/**
 * 分页查询
 * @author ${author}
 *
 */
public class PageQuery {

	private Integer offset;
	
	private Integer limit;

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}

package ${basePackage}.query;

/**
 * 分页查询
 * @author ${author}
 *
 */
public class PageQuery {

	private String sort;
	
	private Integer offset;
	
	private Integer limit;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}	
	
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

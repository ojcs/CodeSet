package sep.frameworkutil.mybatis.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>分页信息</p>
 * <p>继承ArrayList是因为如果BaseMapper.getPage方法的返回类型是Page，而MyBatis有如下判断：</p>
 * <pre>
 * if (List.class.isAssignableFrom(method.getReturnType())) {
 *    returnsList = true;//即只有返回List才执行selectList。
 * }
 * </pre>
 * @see PagingBaseMapper#getPage(Page, Object)
 * @author dixingxing	
 * @date 2012-7-12
 */
public class Paging<T> extends ArrayList<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**当前页*/
	private int currentPage = 0;
	
	/**当前记录起始索引*/
	private int currentResult = 0; 
	
	/**存放结果集*/
	private List<T> result = new ArrayList<T>(); 
	
	/**每页显示几条*/
	private int size = 20; 

	/**总条数*/
	private int total = 0;

	public int getCurrentPage() {
		if (currentPage <= 0) {
			currentPage = 1;
		}
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getSize();
		if (currentResult < 0) {
			currentResult = 0;
		}
		return currentResult;
	}

	/** 获取结果集 */
	public List<T> getResult() {
		if (result == null) {
			return new ArrayList<T>();
		}
		return result;
	}

	public int getSize() {
		return size;
	}

	/** 获取总条数 */
	public int getTotal() {
		return total;
	}

	/** 获取总页数 */
	public int getTotalPage() {
		if (total % size == 0) {
			return total / size;
		}
		return total / size + 1;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public void setSize(int size) {
		if (size == 0) {
			size = 10;
		}
		this.size = size;
	}

	/** 设置总条数 */
	public void setTotal(int total) {
		this.total = total;
	}
}
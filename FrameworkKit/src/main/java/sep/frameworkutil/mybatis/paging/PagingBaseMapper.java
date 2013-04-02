package sep.frameworkutil.mybatis.paging;

import java.io.Serializable;

import org.apache.ibatis.annotations.Param;

public interface PagingBaseMapper<T> {
	String PO_KEY = "po";

	T get(Serializable pk);

	Paging<T> getPaging(@Param(PagingInterceptor.PAGE_KEY) Paging<T> paging, @Param(PO_KEY) T object);
}
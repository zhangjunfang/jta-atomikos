package com.ocean.test.db2.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.test.core.baseclass.service.BaseService;
import com.ocean.test.db2.persistence.TsysPostMapper;
import com.ocean.test.exception.ServiceException;
@Service(value ="tsysPostService")
public class TsysPostService<T> extends BaseService<T> {
	@Autowired
	private TsysPostMapper<T> mapper;
	//@Autowired允许业务逻辑层调用其他服务
	//private TsysUserService<TsysUser> tsysUserService;

	public TsysPostMapper<T> getMapper() {
		return mapper;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List selectPageTsysPostByMap(Map map) throws ServiceException {
		return getMapper().selectPageTsysPostByMap(map);

	}

	@SuppressWarnings("rawtypes")
	public List selectPageTsysPost() throws ServiceException {
		return getMapper().selectPageTsysPost();
	}

}

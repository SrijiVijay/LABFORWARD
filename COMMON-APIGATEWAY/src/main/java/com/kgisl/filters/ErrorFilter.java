package com.kgisl.filters;

import com.kgisl.utils.ZuulConstant;
import com.netflix.zuul.ZuulFilter;

/**
 * Class for ErrorFilter which will be invoked when an error occurs while handling the
 * request.
 * 
 * @author rangasamy.g
 * @since Dec, 28 2018
 * @version 1.0
 */
public class ErrorFilter extends ZuulFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return ZuulConstant.ERROR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		System.out.println("Inside error Filter");
		return null;
	}
}

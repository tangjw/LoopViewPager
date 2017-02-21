package com.tjw.loopviewpager.net;

/**
 * ^-^
 * Created by tang-jw on 9/9.
 */
public class ApiException extends RuntimeException {
	private int mErrorCode;
	private String mErrorMessage;
	
	public ApiException(int errorCode, String errorMessage) {
		super(errorMessage);
		mErrorCode = errorCode;
		mErrorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return mErrorCode;
	}
	
	public void setErrorCode(int errorCode) {
		mErrorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return mErrorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		mErrorMessage = errorMessage;
	}
	
	/**
	 * 判断是否是token失效
	 *
	 * @return 失效返回true, 否则返回false;
	 */
	/*public boolean isTokenExpried() {
		return mErrorCode == Constants.TOKEN_EXPRIED;
	}*/
}

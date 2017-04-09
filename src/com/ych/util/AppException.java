<<<<<<< HEAD
package com.ych.util;
/**
 * 
 * @ClassName: AppException 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yangchenghuan
 * @date 2016年9月19日 下午4:20:37
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private String[] args;
	private String defaultMessage;
	
	public AppException(String message){
		this.message = message;
	}
	public AppException(String message, String...args){
		this.message = message;
		if(args != null && args.length > 0){
			
		}
	}
	public AppException(String message, String[] args, String defaultMessage) {
		this.message = message;
		this.args = args;
		this.defaultMessage = defaultMessage;
	}
	@Override
	public String getMessage() {
		return message;
	}
	public String[] getArgs() {
		return args;
	}
	public String getDefaultMessage() {
		return defaultMessage;
	}
}
=======
package com.ych.util;
/**
 * 
 * @ClassName: AppException 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yangchenghuan
 * @date 2016年9月19日 下午4:20:37
 */
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private String[] args;
	private String defaultMessage;
	
	public AppException(String message){
		this.message = message;
	}
	public AppException(String message, String...args){
		this.message = message;
		if(args != null && args.length > 0){
			
		}
	}
	public AppException(String message, String[] args, String defaultMessage) {
		this.message = message;
		this.args = args;
		this.defaultMessage = defaultMessage;
	}
	@Override
	public String getMessage() {
		return message;
	}
	public String[] getArgs() {
		return args;
	}
	public String getDefaultMessage() {
		return defaultMessage;
	}
}
>>>>>>> eb63eded9899513af9538a187ddf701ead10b135

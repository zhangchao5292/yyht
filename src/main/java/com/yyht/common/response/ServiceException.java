package com.yyht.common.response;

/**统一异常处理*/
public class ServiceException extends Exception {

	private static final long serialVersionUID = 6654830544228411564L;
	
	private ResultMessage errorMessage;
	
	public ResultMessage getResultMessage() {
		return errorMessage;
	}
	
	public int getErrorCode() {
		return errorMessage.getCode();
	}

	public void setErrorCode(ResultMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public ServiceException(){
		//Hessian序列化保留方式
		super(ResultMessage.FAILURE.getMessage());
		this.errorMessage = ResultMessage.FAILURE;
	}
	
	public ServiceException(ResultMessage errorMessage, Object... args){
		super(String.format(errorMessage.getMessage(), args));
		this.errorMessage = errorMessage;
	}
	
	public ServiceException(ResultMessage errorMessage){
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(ResultMessage.F2021.getCode()+"==="+ResultMessage.F2021.getMessage());
			throw new ServiceException(ResultMessage.F2021,123456);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}

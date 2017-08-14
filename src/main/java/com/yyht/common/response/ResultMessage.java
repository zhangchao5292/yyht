package com.yyht.common.response;


/**
 * 返回消息枚举
 * @author lonyee
 *
 */
public enum ResultMessage {
	/** 操作成功 */
	SUCCESS(2000, "操作成功"),
	/** 操作失败 */
	FAILURE(4020, "操作失败"),
	/** 服务器无法理解此请求 */
	F4000(4000, "服务器无法理解此请求，%s"),	
	/** [服务器]运行时异常 */
	F4001(4001, "[服务器]运行时异常"),
	/** [服务器]空值异常 */
	F4002(4002, "[服务器]空值异常"),
	/** [服务器]数据类型转换异常 */
	F4003(4003, "[服务器]数据类型转换异常"),
	/** [服务器]IO异常 */
	F4004(4004, "[服务器]IO异常"),
	/** [服务器]未知方法异常 */
	F4005(4005, "[服务器]未知方法异常"),
	/** [服务器]数组越界异常 */
	F4006(4006, "[服务器]数组越界异常"),
	/** [服务器]网络异常 */
	F4007(4007, "[服务器]网络异常"),
	/** 访问超时，请重新登陆 */
	F4010(401, "访问超时，请重新登陆"),
	/** 不支持的媒体类型 */
	F4150(4150, "不支持的媒体类型"),
	/** 没有页面访问权限 */
	F4030(403, "没有页面访问权限"),
	/** 没有找到请求的接口资源 */
	F4040(4040, "没有找到请求的接口资源"),
	/** 不支持的请求方法 GET,POST */
	F4050(4050, "不支持的请求方式"),
	/** 客户端不接受所请求的 MIME类型 */
	F4060(4060, "客户端不接受所请求的MIME类型"),
	/** 服务器内部异常 */
	F5000(5000, "服务器内部异常"),
	
	//****** 2000操作正常的提示 ***********//
	/** 密码重置成功，新密码为 %s */
	F2021(2000, "密码重置成功，新密码为 %s "),
	
	//****** 操作异常的提示 ***************//
	/** 获取图形验证码失败 */
	F6001(6001, "获取图形验证码失败"),
	/** 获取图形验证码失败 */
	F6010(6010, "获取图形验证码失败"),
	/** 图形验证码不能为空 */
	F6011(6011, "图形验证码不能为空"),
	/** 图形验证码输入错误 */
	F6012(6012, "图形验证码输入错误"),
	/** sessionId不能为空 */
	F6013(6013, "sessionId不能为空"),
	/** 手机号码不能为空 */
	F6014(6014, "手机号码不能为空"),
	/** 缺少数据签名信息 */
	F6015(6015, "缺少数据签名信息"),
	/** 数据签名错误 */
	F6016(6016, "数据签名错误"),
	/** 短信验证码发送失败%s */
	F6017(6017, "短信验证码发送失败%s"),
	/** 短信验证码错误%s */
	F6018(6018, "短信验证码错误%s"),
	/** 短信验证码不能为空 */
	F6019(6019, "短信验证码不能为空"),
	/** uuid不能为空 */
	F6020(6020, "uuid不能为空"),
	/** 时间戳不能为空 */
	F6021(6021, "时间戳不能为空"),
	/** 错误的登录账号信息 */
	F6022(6022, "错误的登录账号信息"),
	/** 手机号码错误 */
	F6023(6023, "手机号码错误"),
	/** 区服编号不能为空 */
	F6025(6025, "区服编号不能为空"),
	
	
	
	
	
	
	/** 用户名或密码错误 */
	F5001(5001, "用户名或密码错误"),
	/** 当前登录密码错误 */
	F5002(5002, "当前登录密码错误"),
	/** 手机号错误 */
	F5003(5003, "手机号错误"),
	/** 验证码错误 */
	F5004(5004, "验证码错误"),
	/** 用户名不能为空 */
	F5005(5005, "用户名不能为空"),
	/** 用户名已存在 */
	F5006(5006, "用户名已存在"),
	/** 授权信息获取失败 */
	F5007(5007, "授权信息获取失败"),
	/** 授权用户信息获取失败 */
	F5008(5008, "授权用户信息获取失败"),
	/** 网页授权票据获取失败 */
	F5009(5009, "网页授权票据获取失败"),
	/** 模板消息发送失败 */
	F5010(5010, "模板消息发送失败"),
	/** 认证URL获取失败 */
	F5011(5011, "认证URL获取失败"),
	/** 1小时内最多只能发送%s次验证码 */
	F5020(5020, "1小时内最多能发送%s次验证码"),
	/** 手机验证码发送失败 */
	F5021(5021, "手机验证码发送失败"),
	/** 存在子节点数据 */
	F6002(6002, "删除失败，存在子节点数据"),
	/** 数据签名验证失败 */
	F8001(8001, "数据签名验证失败"),
	/** 调起支付失败，错误代码：%s，错误信息：%s*/
	F8002(8002, "调起支付失败，错误代码：%s，错误信息：%s"),
	/** 调起支付失败 %s*/
	F8003(8003, "调起支付失败 %s"),
	/** 支付失败，错误代码：%s，错误信息：%s*/
	F8004(8004, "支付失败，错误代码：%s，错误信息：%s"),
	/** 支付失败 %s*/
	F8005(8005, "支付失败 %s"),
	
	/** 提现转账失败，错误代码：%s，错误信息：%s*/
	F8012(8012, "提现转账失败，错误代码：%s，错误信息：%s"),
	/** 提现转账失败 %s*/
	F8013(8013, "提现转账失败 %s"),
	/** 发放红包失败，错误代码：%s，错误信息：%s*/
	F8022(8022, "发放红包失败，错误代码：%s，错误信息：%s"),
	/** 发放红包失败 %s*/
	F8023(8023, "发放红包失败 %s"),
	/** 退款失败，错误代码：%s，错误信息：%s*/
	F8032(8032, "退款失败，错误代码：%s，错误信息：%s"),
	/** 退款失败 %s*/
	F8033(8033, "退款失败 %s");
	
	private int code;
	private String message;
	ResultMessage(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public static String getMessage(int code) {
		ResultMessage[] arrayMessages = ResultMessage.values();
		for(ResultMessage message: arrayMessages) {
			if (message.getCode()==code) {
				return message.getMessage();
			}
		}
		return "";
	}
	public static void main(String[] args) {
		System.out.println(ResultMessage.F6013);
	}
}
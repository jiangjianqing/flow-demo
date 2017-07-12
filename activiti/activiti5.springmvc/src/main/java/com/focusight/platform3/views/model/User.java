package com.focusight.platform3.views.model;

import java.util.Date;

import javax.validation.Valid;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

/**
 *  @AssertTrue //用于boolean字段，该字段只能为true  
    @AssertFalse//该字段的值只能为false  
    @CreditCardNumber//对信用卡号进行一个大致的验证  
    @DecimalMax//只能小于或等于该值  
    @DecimalMin//只能大于或等于该值  
    @Digits(integer=2,fraction=20)//检查是否是一种数字的整数、分数,小数位数的数字。  
    @Email//检查是否是一个有效的email地址  
    @Future//检查该字段的日期是否是属于将来的日期  
    @Length(min=,max=)//检查所属的字段的长度是否在min和max之间,只能用于字符串  
    @Max//该字段的值只能小于或等于该值  
    @Min//该字段的值只能大于或等于该值  
    @NotNull//不能为null  
    @NotBlank//不能为空，检查时会将空格忽略  
    @NotEmpty//不能为空，这里的空是指空字符串  
    @Null//检查该字段为空  
    @Past//检查该字段的日期是在过去  
    @Size(min=, max=)//检查该字段的size是否在min和max之间，可以是字符串、数组、集合、Map等  
    @URL(protocol=,host,port)//检查是否是一个有效的URL，如果提供了protocol，host等，则该URL还需满足提供的条件  
    @Valid//该注解只要用于字段为一个包含其他对象的集合或map或数组的字段，或该字段直接为一个其他对象的引用，  
            //这样在检查当前对象的同时也会检查该字段所引用的对象  
 * @author cz_jjq
 *
 */
public class User {
	@NotEmpty(message="姓名不能为空")
	@Length(min=8, max=30, message="姓名（ ${validatedValue} ） 长度必须大于 {min} 小于 {max}")
	private String name;
	@NotEmpty(message="密码不能为空")
	@Length(min=5, max=10, message="密码 （ ${validatedValue} ） 长度必须大于 {min} 小于 {max}")  //这里使用了el表达式，需要在pom中引用javax.el
	private String password;  
	@NotNull(message="生日不能为空")
	private String birthday;
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
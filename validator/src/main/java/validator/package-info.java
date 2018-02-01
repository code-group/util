/**
 * @author zhull
 * @date 2018/1/31
 * <P>description: hibernate验证框架应用</P>
 * reference: http://hibernate.org/validator/
 * <p>
 * 1. 用@Valid注解标识需要校验的类 <br/>
 * 2. 手动调用ValidateUtil对参数进行校验 <br/>
 * @see validator.ValidateUtil <br/>
 * 3. 自动验证spring-context BindingResult验证框架Validation
 */
package validator;
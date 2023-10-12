package co.yixiang.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author hupeng
 * 微信模板事件
 */
@Getter
public class TemplateEvent extends ApplicationEvent {

	private TemplateBean templateBean;
	/**
	 * 重写构造函数
	 * @param source 发生事件的对象
	 * @param templateBean 自定义
	 */
	public TemplateEvent(Object source,TemplateBean templateBean) {
		super(source);
		this.templateBean = templateBean;
	}
}

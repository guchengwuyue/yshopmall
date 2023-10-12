package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author hupeng
 * sort关枚举
 */
@Getter
@AllArgsConstructor
public enum SortEnum {

	DESC("desc","降序"),
	ASC("asc","升序");


	private String value;
	private String desc;

	public static SortEnum toType(String value) {
		return Stream.of(SortEnum.values())
				.filter(p -> p.value == value)
				.findAny()
				.orElse(null);
	}


}

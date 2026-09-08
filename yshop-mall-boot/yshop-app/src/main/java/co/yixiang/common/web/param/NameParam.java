package co.yixiang.common.web.param;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;


@Schema(description = "名称参数")
public class NameParam implements Serializable {
    private static final long serialVersionUID = -3710501706034574149L;

    @Schema(description = "名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameParam{" +
                "name='" + name + '\'' +
                '}';
    }
}

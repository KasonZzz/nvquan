package cn.aisism.nvquan.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@ApiModel(value = "Redis配置",description = "Redis所需要的配置信息")
public class SetData {

    @ApiModelProperty(value = "key值",example = "name")
    private String key;


    @ApiModelProperty(value = "value值",example = "KasonZzz")
    private String value;


    @ApiModelProperty(value = "键值对")
    private Map<String,String> kvs;


    @ApiModelProperty(value = "key集合")
    private List<String> keys;


    @ApiModelProperty(value = "超时时间(单位：秒)",example = "15")
    private Long time;






}

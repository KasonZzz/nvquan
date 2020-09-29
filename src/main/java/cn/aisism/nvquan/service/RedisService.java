package cn.aisism.nvquan.service;

import cn.aisism.nvquan.pojo.BaseResult;
import cn.aisism.nvquan.pojo.SetData;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: KasonZzz
 * @Date: 2020/9/28 17 19
 * @Description:
 */
public interface RedisService {

     BaseResult set( SetData data);

     BaseResult multiSet( SetData data);



     BaseResult multiSetForAccess( Map<String,String> map);


     BaseResult delete(SetData data);

     BaseResult multiDelete( SetData data);

     BaseResult multiDeleteForAccess( List<String> keys);



     BaseResult get( SetData data);

     BaseResult multiGet(SetData data);

     BaseResult multiGetForAccess(List<String> keys);


}

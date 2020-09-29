package cn.aisism.nvquan.controller;

import cn.aisism.nvquan.pojo.BaseResult;
import cn.aisism.nvquan.pojo.SetData;
import cn.aisism.nvquan.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("real")
@Api(tags = "Redis服务接口",description = "如果接口返回4001，最有可能的是未正确传入值")
public class StringRedisController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "SetData单条插入",notes = "SetData中传入key、value,如果有超时时间就传入time")
    @PostMapping("set")
    public BaseResult set(@RequestBody SetData data){
        return redisService.set(data);
    }


    @ApiOperation(value = "SetData批量插入",notes = "SetData中传入key、value形式的kvs,如果有超时时间就传入time")
    @PostMapping("multiSet")
    public BaseResult multiSet(@RequestBody SetData data){
        return redisService.multiSet(data);
    }



    @ApiOperation(value = "Map批量插入",notes = "直接传入map,无法设置超时时间")
    @PostMapping("multiSetForAccess")
    public BaseResult multiSetForAccess(@RequestBody Map<String,String> map){
        return redisService.multiSetForAccess(map);
    }


    @ApiOperation(value = "SetData单删",notes = "SetData中传入对应的Key")
    @PostMapping("delete")
    public BaseResult delete(@RequestBody SetData data){
        return redisService.delete(data);
    }

    @ApiOperation(value = "SetData批量删除",notes = "SetData中传入对应的keys，如果有成功与失败都会返回map，如果没有返回值，就代表未进行操作")
    @PostMapping("multiDelete")
    public BaseResult multiDelete(@RequestBody SetData data){
        return redisService.multiDelete(data);
    }



    @ApiOperation(value = "List批量删除",notes = "直接传入List，如果有成功与失败都会返回map，如果没有返回值，就代表未进行操作")
    @PostMapping("multiDeleteForAccess")
    public BaseResult multiDeleteForAccess(@RequestBody List<String> keys){
        return redisService.multiDeleteForAccess(keys);
    }



    @ApiOperation(value = "SetData单条查询",notes = "SetData中传入Key")
    @PostMapping("get")
    public BaseResult get(@RequestBody SetData data){
        return redisService.get(data);
    }

    @ApiOperation(value = "SetData批量查询",notes = "SetData中传入Keys")
    @PostMapping("multiGet")
    public BaseResult multiGet(@RequestBody SetData data){
        return redisService.multiGet(data);
    }

    @ApiOperation(value = "List批量查询",notes = "传入List格式的Key")
    @PostMapping("multiGetForAccess")
    public BaseResult multiGetForAccess(@RequestBody List<String> keys){
        return redisService.multiGetForAccess(keys);
    }

}

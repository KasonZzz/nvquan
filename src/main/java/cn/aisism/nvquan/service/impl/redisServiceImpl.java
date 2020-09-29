package cn.aisism.nvquan.service.impl;

import cn.aisism.nvquan.pojo.BaseResult;
import cn.aisism.nvquan.pojo.SetData;
import cn.aisism.nvquan.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
@Service
public class redisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private BaseResult result = new BaseResult(200);

    private BaseResult paramResult = new BaseResult(4001);


    @Override
    public BaseResult set(SetData data) {
        if (!ObjectUtils.isEmpty(data.getKey()) && !ObjectUtils.isEmpty(data.getValue())){
            if (ObjectUtils.isEmpty(data.getTime())){
                redisTemplate.opsForValue().set(data.getKey(),data.getValue());
                return result;
            }else {
                redisTemplate.opsForValue().set(data.getKey(), data.getValue(), data.getTime(), TimeUnit.SECONDS);
                return result;
            }
        }
        return paramResult;
    }

    @Override
    public BaseResult multiSet(SetData data) {
        if (!ObjectUtils.isEmpty(data.getKvs())){
            if (ObjectUtils.isEmpty(data.getTime())){
                redisTemplate.opsForValue().multiSet(data.getKvs());
                return result;
            }else {
                data.getKvs().forEach((key,value)->{
                    redisTemplate.opsForValue().set(key,value,data.getTime(),TimeUnit.SECONDS);
                });
                return result;
            }
        }
        return paramResult;
    }

    @Override
    public BaseResult multiSetForAccess(Map<String, String> map) {
        if (!ObjectUtils.isEmpty(map)){
            redisTemplate.opsForValue().multiSet(map);
            return result;
        }
        return paramResult;
    }

    @Override
    public BaseResult delete(SetData data) {
        if (!ObjectUtils.isEmpty(data.getKey())){
            Boolean delete = redisTemplate.delete(data.getKey());
            return new BaseResult(delete);
        }
        return paramResult;
    }

    @Override
    public BaseResult multiDelete(SetData data) {
        if (!ObjectUtils.isEmpty(data.getKeys()) && data.getKeys().size() > 0){
            List<String> keys = data.getKeys();
            Map<String,List<String>> map = new HashMap<>();
            List<String> successKey = new ArrayList<>();
            List<String> failKey = new ArrayList<>();
            for (String key : keys) {
                if (redisTemplate.hasKey(key)){
                    successKey.add(key);
                    redisTemplate.delete(key);
                }else {
                    failKey.add(key);
                }
            }
            if (!ObjectUtils.isEmpty(successKey)){
                map.put("successKey",successKey);
            }
            if (!ObjectUtils.isEmpty(failKey)){
                map.put("failKey",failKey);
            }
            if (!ObjectUtils.isEmpty(map)){
                return new BaseResult(map);
            }
            return result;
        }
        return paramResult;
    }

    @Override
    public BaseResult multiDeleteForAccess(List<String> keys) {
        if (!ObjectUtils.isEmpty(keys) && keys.size()>0){
            Map<String,List<String>> map = new HashMap<>();
            List<String> successKey = new ArrayList<>();
            List<String> failKey = new ArrayList<>();
            for (String key : keys) {
                if (redisTemplate.hasKey(key)){
                    successKey.add(key);
                    redisTemplate.delete(key);
                }else {
                    failKey.add(key);
                }
            }
            if (!ObjectUtils.isEmpty(successKey)){
                map.put("successKey",successKey);
            }
            if (!ObjectUtils.isEmpty(failKey)){
                map.put("failKey",failKey);
            }
            if (!ObjectUtils.isEmpty(map)){
                return new BaseResult(map);
            }
            return result;
        }
        return paramResult;
    }

    @Override
    public BaseResult get(SetData data) {
        if (!ObjectUtils.isEmpty(data.getKey())){
            String value = redisTemplate.opsForValue().get(data.getKey());
            return new BaseResult(value);
        }
        return paramResult;
    }

    @Override
    public BaseResult multiGet(SetData data) {
        if (!ObjectUtils.isEmpty(data.getKeys())){
            List<String> strings = redisTemplate.opsForValue().multiGet(data.getKeys());
            return new BaseResult(strings);
        }
        return paramResult;
    }

    @Override
    public BaseResult multiGetForAccess(List<String> keys) {
        if (!ObjectUtils.isEmpty(keys)){
            List<String> strings = redisTemplate.opsForValue().multiGet(keys);
            return new BaseResult(strings);
        }
        return paramResult;
    }
}

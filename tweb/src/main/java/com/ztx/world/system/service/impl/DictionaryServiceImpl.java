package com.ztx.world.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ztx.world.common.config.CustomSession;
import com.ztx.world.common.constants.BaseConstants;
import com.ztx.world.common.constants.ConfigConstants;
import com.ztx.world.common.enums.ResultEnum;
import com.ztx.world.common.exception.BasicException;
import com.ztx.world.common.redis.RedisOperator;
import com.ztx.world.system.entity.Dictionary;
import com.ztx.world.system.entity.DictionaryExample;
import com.ztx.world.system.mapper.DictionaryMapper;
import com.ztx.world.system.service.DictionaryService;
import com.ztx.world.system.vo.DictionaryVo;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	@Autowired
	private RedisOperator redisOperator;
	
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> getDictionaryByType(String dictionaryType) {
		String cacheType = ConfigConstants.DICTIONARY_PRE + dictionaryType;
		List<Dictionary> dictionaryList = null;
		if(redisOperator.hasKey(cacheType)){
			dictionaryList = (List<Dictionary>)redisOperator.get(cacheType);
		}else{
			DictionaryExample example = new DictionaryExample();
			example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
					.andDictionaryTypeEqualTo(dictionaryType);
			dictionaryList = dictionaryMapper.selectByExample(example);
			redisOperator.set(cacheType, dictionaryList);
		}
		return dictionaryList;
	}
	
	@Override
	public String getDictionaryValue(String dictionaryType, String dictionaryKey) {
		String value = "";
		List<Dictionary> dictionaryList = this.getDictionaryByType(dictionaryType);
		if(!CollectionUtils.isEmpty(dictionaryList)){
			for(Dictionary d : dictionaryList){
				if(d.getDictionaryKey().equals(dictionaryKey)){
					value = d.getDictionaryValue();
					break;
				}
			}
		}
		return value;
	}

	@Override
	public Long saveDictionary(DictionaryVo dictionary) {
    	if(dictionary == null){
    		throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
    	}
		if(StringUtils.isEmpty(dictionary.getDictionaryType())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典类型不能为空.");
		}
		if(StringUtils.isEmpty(dictionary.getDictionaryName())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典名称不能为空.");
		}
		if(StringUtils.isEmpty(dictionary.getDictionaryKey())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典键不能为空.");
		}
		if(StringUtils.isEmpty(dictionary.getDictionaryValue())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典值不能为空.");
		}
		DictionaryExample example = new DictionaryExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andDictionaryTypeEqualTo(dictionary.getDictionaryType())
			.andDictionaryKeyEqualTo(dictionary.getDictionaryKey());
		int count = dictionaryMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典类型与键的组合已存在.");
		}
		dictionary.setStatus(BaseConstants.UNDELETE_STATUS);
		dictionary.setCreateTime(new Date());
		dictionary.setUpdateTime(new Date());
		CustomSession customSession = (CustomSession)SecurityUtils.getSubject().getPrincipal();
		dictionary.setCreateUserId(customSession.getUserId());
		dictionaryMapper.insertSelective(dictionary);
		if(dictionary.getId() == null){
			throw new BasicException(ResultEnum.BASE_DATA_ERROR.getCode(), "新增字典失败.");
		}
		return dictionary.getId();
	}

	@Override
	public Long updateDictionary(DictionaryVo dictionary) {
    	if(dictionary == null){
    		throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "数据不能为空.");
    	}
		if(dictionary.getId() == null){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典数据不存在.");
		}
		if(StringUtils.isEmpty(dictionary.getDictionaryType())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典类型不能为空.");
		}
		if(StringUtils.isEmpty(dictionary.getDictionaryName())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典名称不能为空.");
		}
		if(StringUtils.isEmpty(dictionary.getDictionaryKey())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典键不能为空.");
		}
		if(StringUtils.isEmpty(dictionary.getDictionaryValue())){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典值不能为空.");
		}
		DictionaryExample example = new DictionaryExample();
		example.createCriteria().andStatusEqualTo(BaseConstants.UNDELETE_STATUS)
			.andDictionaryTypeEqualTo(dictionary.getDictionaryType())
			.andDictionaryKeyEqualTo(dictionary.getDictionaryKey())
			.andIdNotEqualTo(dictionary.getId());
		int count = dictionaryMapper.countByExample(example);
		if(count >= 1){
			throw new BasicException(ResultEnum.BASE_ARG_ERROR.getCode(), "字典类型与键的组合已存在.");
		}
		dictionary.setUpdateTime(new Date());
		dictionaryMapper.updateByPrimaryKeySelective(dictionary);
		
		// 删除缓存信息
		String cacheType = ConfigConstants.DICTIONARY_PRE + dictionary.getDictionaryType();
		if(redisOperator.hasKey(cacheType)){
			redisOperator.del(cacheType);
		}
		return dictionary.getId();
	}

	@Override
	public void deleteDictionary(List<Long> ids) {
		if(!CollectionUtils.isEmpty(ids)){
			for(Long id : ids){
				Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(id);
				dictionary.setId(id);
				dictionary.setStatus(-id);
				dictionaryMapper.updateByPrimaryKeySelective(dictionary);
				
				// 删除缓存信息
				String cacheType = ConfigConstants.DICTIONARY_PRE + dictionary.getDictionaryType();
				if(redisOperator.hasKey(cacheType)){
					redisOperator.del(cacheType);
				}
			}
		}
	}

}

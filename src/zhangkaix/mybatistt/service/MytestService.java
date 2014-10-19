
package zhangkaix.mybatistt.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhangkaix.mybatistt.dao.mybatis.MytestDao;
import zhangkaix.mybatistt.domain.Mytest;

@Service
@Transactional
public class MytestService {

	private static Logger logger = LoggerFactory.getLogger(MytestService.class);

	@Autowired
	private MytestDao mytestDao;
	
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public Mytest findById(long id){
		return mytestDao.findById(id);
	}
	
	/**
	 * 按条件查询
	 * @param 列表记录
	 * @return
	 */
	public List<Mytest> searchByPara(Map<String,Object> map){
		return mytestDao.searchByPara(map);
	}
	
	/**
	 * 按条件查询
	 * @param 列表记录
	 * @return
	 */
	public List<Mytest> searchByParas(Map<String,Object> map){
		return mytestDao.searchByParas(map);
	}
	
	/**
	 * save
	 * @param Mytest
	 * @return
	 */
	public void save(Mytest mytest){
		
	}
	
	/**
	 * 修改
	 * @param mytest
	 * @return
	 */
	public void updateAllColums(Mytest mytest){
		
	}
	
	/**
	 * 修改多条记录
	 * @param list
	 * @return
	 */
	public void  update(List<Long> list){
		
	}
	
}

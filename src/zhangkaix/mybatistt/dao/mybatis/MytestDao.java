
package zhangkaix.mybatistt.dao.mybatis;

import java.util.List;
import java.util.Map;

import zhangkaix.mybatistt.domain.Mytest;
import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface MytestDao {
 
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public Mytest findById(long id);
	
	/**
	 * 按条件查询
	 * @param 列表记录
	 * @return
	 */
	public List<Mytest> searchByPara(Map<String,Object> map);
	/**
	 * 按条件查询
	 * @param 列表记录
	 * @return
	 */
	public List<Mytest> searchByParas(Map<String,Object> map);
	
	/**
	 * save
	 * @param Mytest
	 * @return
	 */
//	public void save(Mytest mytest);
	
	/**
	 * 修改
	 * @param mytest
	 * @return
	 */
//	public void updateAllColums(Mytest mytest);
	
	/**
	 * 修改多条记录
	 * @param list
	 * @return
	 */
//	public void  update(List<Long> list);
	
}

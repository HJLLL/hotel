package school.libenhe.hotel.dao;

import java.util.List;

import school.libenhe.hotel.entity.Orders;
import school.libenhe.hotel.utils.PageBean;

/**
 * 订单Dao接口
 * 
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-1 下午2:43:40
 */
public interface IOrdersDao {

	/**
	 * 增加
	 * 
	 * @author: Li Benhe Email: libenhe919@gmail.com
	 * @version: 2016-3-4 下午10:51:48 
	 * @param orders
	 */
	void add(Orders orders);
	
	/**
	 * 更新数据
	 * 
	 * @author: Li Benhe Email: libenhe919@gmail.com
	 * @version: 2016-3-4 下午10:49:56 
	 * @param orders
	 */
	void update(Orders orders);

	/**
	 * 查询
	 * 
	 * @author: Li Benhe Email: libenhe919@gmail.com
	 * @version: 2016-3-4 下午10:50:46 
	 * @return
	 */
	List<Orders> query();
	
	/**
	 * 订单记录数
	 * @author: Li Benhe Email: libenhe919@gmail.com
	 * @version: 2016-3-4 下午10:53:07 
	 * @return
	 */
	int getCount();
	/**
	 * 订单总记录数
	 * 
	 * @author: Li Benhe Email: libenhe919@gmail.com
	 * @version: 2016-3-4 下午10:53:04 
	 * @return
	 */
	int getTotalCount();

	/**
	 * 获得所有(分页用)
	 * 
	 * @author: Li Benhe Email: libenhe919@gmail.com
	 * @version: 2016-3-4 下午10:53:47 
	 * @param pb
	 */
	void getAll(PageBean<Orders> pb);
}

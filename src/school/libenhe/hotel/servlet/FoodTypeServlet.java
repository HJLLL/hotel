package school.libenhe.hotel.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import school.libenhe.hotel.entity.FoodType;
import school.libenhe.hotel.factory.BeanFactory;
import school.libenhe.hotel.service.IFoodTypeService;
import school.libenhe.hotel.utils.WebUtils;

/**
 * @author Li Benhe Email: libenhe919@gmail.com
 * @version 2016-3-8 下午6:51:32
 */
public class FoodTypeServlet extends HttpServlet {

	//创建service
	private IFoodTypeService service = BeanFactory.getInstance("foodTypeService", IFoodTypeService.class);
	
	private Object uri;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<FoodType> list = service.query();
		config.getServletContext().setAttribute("foodtype", list);
	}
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	String method = request.getParameter("method");
		
		if("add".equals(method)){
			add(request,response);
		}else if("list".equals(method)){
			list(request,response);
		}else if("update".equals(method)){
			update(request,response);
		}else if("delete".equals(method)){
			delete(request,response);
		}else if("search".equals(method)){
			search(request,response);
		}else if("show".equals(method)){
			show(request, response);
		}
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			FoodType foodtype = new FoodType();
			foodtype.setTypeName(name);
			service.add(foodtype);
			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<FoodType> list1 = service.query();
			request.setAttribute("list", list1);
			request.getServletContext().setAttribute("foodtype", list1);
			uri = request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri="/error/error.jsp";
		}finally {
			WebUtils.goTo(request, response, uri);
		}
	}
	

	//在菜系更新中显示菜系对应的类型的名称
		private void show(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				
				String id = request.getParameter("id");
				FoodType type = service.findById(Integer.parseInt(id));
				request.setAttribute("type", type);
				uri = request.getRequestDispatcher("/sys/foodtype/updateCuisine.jsp");
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
			}finally {
				WebUtils.goTo(request, response, uri);
			}
		}
	
	
		private void update(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				FoodType type = new FoodType();
				Map<String, String[]> map = request.getParameterMap();
				BeanUtils.populate(type, map);
				service.updata(type);
				list(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
			}
		}
		
		
		private void delete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				String id = request.getParameter("id");
				service.delete(Integer.parseInt(id));
				list(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
				WebUtils.goTo(request, response, uri);
			}
		}
		
		
		
		private void search(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				String keyword = request.getParameter("keyword");
				if(keyword!=null){
					List<FoodType> list = service.query(keyword);
					request.setAttribute("list",list);
					uri=request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
				}
			} catch (Exception e) {
				uri="/error/error.jsp";
				e.printStackTrace();
			}
			WebUtils.goTo(request, response, uri);
		}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	   this.doGet(request, response);
		
	}

}

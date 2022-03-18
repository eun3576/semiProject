package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import dto.ProductCategory;
import service.face.ProductService;
import service.impl.ProductServiceImpl;

@WebServlet("/product/search")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/product_view/productSearch.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Product> list = productService.getProductList(req);
		
		List<ProductCategory> categoryList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			for(int j=0; j<productService.getCategoryList(list.get(i)).size(); j++) {
				categoryList.add(productService.getCategoryList(list.get(i)).get(j));
			}
		}
		req.setAttribute("list", list);
		req.setAttribute("categoryList", categoryList);
		
		req.getRequestDispatcher("/WEB-INF/views/product_view/productList.jsp").forward(req, resp);

	}
}

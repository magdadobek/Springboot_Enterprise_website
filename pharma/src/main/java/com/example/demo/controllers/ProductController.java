package com.example.demo.controllers;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Cart;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Order;
import com.example.demo.model.Problem;
import com.example.demo.model.Producer;
import com.example.demo.model.Product;
import com.example.demo.services.CartService;
import com.example.demo.services.IngredientService;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProblemService;
import com.example.demo.services.ProducerService;
import com.example.demo.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	@Autowired
	private ProducerService servicep;
	@Autowired
	private ProblemService servicepr;
	@Autowired
	private IngredientService servicei;
	@Autowired
	private CartService serviceCart;
	@Autowired
	private OrderService serviceOrder;
	public ProductController() {

	}
	  @GetMapping("/")
	  public String viewIndex(HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		currentUserId = (String) session.getAttribute("userID");
	    System.out.println(currentUserId);
	    return "index";
	  }
	@RequestMapping("/medicine")
	public String viewProductList(Model model, HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		List<Product> lp=service.findAll();
		model.addAttribute("lp", lp);
		return "medicine";
	}
	@RequestMapping("/contact")
	public String viewContact(HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		return "contact";
	}
	@RequestMapping("/show/{id}")
	public ModelAndView viewProduct(@PathVariable(name = "id") Long id, HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		ModelAndView mav = new ModelAndView("show");
		Optional<Product> p = service.findById(id);
		Product product = p.orElse(null);
		if (product != null) {
		    mav.addObject("product", product);
		}
		Optional<Producer> pro = servicep.findById(product.getProducer());
		Producer producer = pro.orElse(null);
		if (product != null) {
		    mav.addObject("producer", producer);
		}
		Optional<Problem> prob = servicepr.findById(product.getIndication());
		Problem indication = prob.orElse(null);
		if (product != null) {
		    mav.addObject("indication", indication);
		}
		Optional<Problem> probl = servicepr.findById(product.getSide_effect());
		Problem sidee = probl.orElse(null);
		if (product != null) {
		    mav.addObject("side_effect", sidee);
		}
		Optional<Ingredient> ing = servicei.findById(product.getIngredient());
		Ingredient ingredient = ing.orElse(null);
		if (product != null) {
		    mav.addObject("ingredient", ingredient);
		}
		return mav;
	}
	@RequestMapping("/search")
	public String viewSearchProduct(Model model, HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		List<Problem> lp=servicepr.findAll();
		model.addAttribute("indication", lp);
		List<Ingredient> li=servicei.findAll();
		model.addAttribute("ingredient", li);
		return "search";
	}
	@RequestMapping("/searchingredient/{id}")
	public ModelAndView viewProductsIngredientSearch(@PathVariable(name = "id") Long id, HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		ModelAndView mav = new ModelAndView("searchingredient");
		List<Product> lp=service.findByIngredient(id);
		mav.addObject("lp", lp);
		return mav;
	}
	@RequestMapping("/searchindication/{id}")
	public ModelAndView viewProductsIndicationSearch(@PathVariable(name = "id") Long id, HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		ModelAndView mav = new ModelAndView("searchindication");
		List<Product> lp=service.findByIndication(id);
		mav.addObject("lp", lp);
		return mav;
	}
	@RequestMapping("/searchname/{name}")
	public ModelAndView viewProductsNameSearch(@PathVariable(name = "name") String name, HttpSession session) {
		String currentUserId = (String) session.getAttribute("userID");
		if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
		ModelAndView mav = new ModelAndView("searchname");
		List<Product> lp=service.findByName(name);
		mav.addObject("lp", lp);
		return mav;
	}
	  @GetMapping("/cart")
	  public ModelAndView viewCart(HttpSession session) {
		    String currentUserId = (String) session.getAttribute("userID");
		    System.out.println(currentUserId);
	    ModelAndView mav = new ModelAndView("cart");
	    if (currentUserId == null) {
	      return null;
	    }
	    List<Cart> carts = serviceCart.findByUserId(currentUserId);
	    mav.addObject("carts", carts);
	    List<Product> products = new ArrayList<>();
	    for(Cart cart : carts){
	      if(cart.getProduct()==null) continue;
	      else {
	        Optional<Product> Oproduct = service.findById(cart.getProduct());
	        Product product = Oproduct.orElse(null);
	        if(product!=null) products.add(product);
	      }
	    }
	    mav.addObject("products", products);
	    return mav;
	  }
	  @RequestMapping("/addToCart/{id}")
	  public String addToCart(HttpSession session, @PathVariable(name = "id") Long id) {
		    String currentUserId = (String) session.getAttribute("userID");
		    System.out.println(currentUserId);
		    if (currentUserId == null) {
		      return "redirect:/";
		    }
		    Optional <Product> productO = service.findById(id);
		    Product product = productO.orElse(null);
		    if(product!=null) {
		    	Cart cart = new Cart();
		    	cart.setUserId(currentUserId);
		    	cart.setProduct(id);
		    	serviceCart.save(cart);
		    	return "redirect:/cart";
		    }
		    else return "addToCart";
	  }
	  
	  @RequestMapping("/makeorder")
	  public String makeOrder(HttpSession session) {
		    String currentUserId = (String) session.getAttribute("userID");
		    System.out.println(currentUserId);
		    if (currentUserId == null) {
		    	return "redirect:/";
		    }
		    List<Cart> carts = serviceCart.findByUserId(currentUserId);
		    List<Product> products = new ArrayList<>();
		    for(Cart cart : carts){
		      if(cart.getProduct()==null) continue;
		      else {
		    	  Order order = new Order();
		    	  order.setDone(false);
		    	  order.setUserid(currentUserId);
		        Optional<Product> Oproduct = service.findById(cart.getProduct());
		        Product product = Oproduct.orElse(null);
		    	  serviceCart.delete(cart);
		        if(product!=null) {
		        	products.add(product);
		        	order.setProduct(product.getId());
		        	serviceOrder.save(order);
		        }
		      }
		    }
		    return "makeorder";
	  }
		@RequestMapping("/searchorder")
		public String viewSearchOrder(Model model, HttpSession session) {
			String currentUserId = (String) session.getAttribute("userID");
			if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
			return "searchorder";
		}
		@RequestMapping("/orderlist/{userId}")
		public ModelAndView viewOrderList(@PathVariable(name = "userId") String userId, HttpSession session) {
			String currentUserId = (String) session.getAttribute("userID");
			if(currentUserId==null) session.setAttribute("userID", ""+new Random().nextLong());
			ModelAndView mav = new ModelAndView("orderlist");
			List <Order> orders = serviceOrder.findByUserid(userId);
			List<Product> products = new ArrayList<>();
			Order orderT=new Order();
			for(Order order : orders){
			      if(order.getProduct()==null) continue;
			      else {
			        Optional<Product> Oproduct = service.findById(order.getProduct());
			        Product product = Oproduct.orElse(null);
			        if(product!=null) {
			        	products.add(product);
			        }
			      }
			      orderT=order;
			    }
			mav.addObject("order", orderT);
			mav.addObject("products", products);
			return mav;
		}
		@RequestMapping(value="/namesearch")
	    public String searchMedicine(@ModelAttribute("name") String name) {
	     return "redirect:/searchname/"+name; }
		@RequestMapping(value="/useridsearch")
	    public String searchOrder(@ModelAttribute("name") String name) {
	     return "redirect:/orderlist/"+name; }
}

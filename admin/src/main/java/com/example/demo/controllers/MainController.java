package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
public class MainController {
	
	@Autowired
	private ProductService service;
	@Autowired
	private ProblemService serviceProb;
	@Autowired
	private ProducerService serviceProd;
	@Autowired
	private IngredientService serviceIng;
	@Autowired
	private CartService serviceCart;
	@Autowired
	private OrderService serviceOrder;

	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/products")
	public ModelAndView viewProducts() {
		ModelAndView mav = new ModelAndView("products");
		List<Product> products = service.findAll();
		mav.addObject("products",products);
		List<Problem> problems = serviceProb.findAll();
		List<Producer> producers = serviceProd.findAll();
		List<Ingredient> ingredients = serviceIng.findAll();
		mav.addObject("problem",problems);
		mav.addObject("ingredient", ingredients);
		mav.addObject("producer", producers);
		return mav;
	}
	
	@RequestMapping("/ingredients")
	public ModelAndView viewIngredients() {
		ModelAndView mav = new ModelAndView("ingredients");
		List<Ingredient> ingredients = serviceIng.findAll();
		mav.addObject("ingredient", ingredients);
		return mav;
	}
	
	@RequestMapping("/problems")
	public ModelAndView viewProblems() {
		ModelAndView mav = new ModelAndView("problems");
		List<Problem> problems = serviceProb.findAll();
		mav.addObject("problem", problems);
		return mav;
	}
	
	@RequestMapping("/producers")
	public ModelAndView viewProducers() {
		ModelAndView mav = new ModelAndView("producers");
		List<Producer> producers = serviceProd.findAll();
		mav.addObject("producer", producers);
		return mav;
	}
	
	@RequestMapping("/orders")
	public ModelAndView viewOrders() {
		ModelAndView mav = new ModelAndView("orders");
		List<Product> products = service.findAll();
		mav.addObject("product", products);
		List<Order> orders = serviceOrder.findAll();
		mav.addObject("order", orders);
		return mav;
	}
	
	@RequestMapping("deleteOrder/{userid}")
	public String deleteOrder(@PathVariable(name="userid") String userid) {
		List<Order> orders = serviceOrder.findAll();
		for(Order order : orders) {
			if(order.getUserid().equals(userid)) serviceOrder.deleteById(order.getId());
		}
		return "/deleteOrder";
	}
	
	@RequestMapping("finishOrder/{userid}")
	public String finishOrder(@PathVariable(name="userid") String userid) {
		List<Order> orders = serviceOrder.findAll();
		for(Order order : orders) {
			if(order.getUserid().equals(userid)) {
				order.setDone(true);
				serviceOrder.save(order);
			}
		}
		return "finishOrder";
	}
	
	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		  List<Product> products = service.findAll();
		  for(Product product : products) {
		    if(product.getId()==id) {
		      List<Cart> carts = serviceCart.findAll();
		      List<Order> orders = serviceOrder.findAll();
		      for (Cart cart : carts) {
		        if (cart.getProduct() == product.getId()) {
		          serviceCart.deleteById(cart.getId());
		        }
		      }
		      for (Order order : orders) {
			        if (order.getProduct() == product.getId()) {
			          serviceOrder.deleteById(order.getId());
			        }
			      }
		      service.deleteById(product.getId());
		    }
		  }
		  return "/deleteProduct";
		}
	
	@RequestMapping("/deleteIngredient/{id}")
	public String deleteIngredient(@PathVariable(name = "id") Long id) {
	  List<Product> products = service.findAll();
	  for(Product product : products) {
	    if(product.getIngredient()==id) {
	      List<Cart> carts = serviceCart.findAll();
	      List<Order> orders = serviceOrder.findAll();
	      for (Cart cart : carts) {
	        if (cart.getProduct() == product.getId()) {
	          serviceCart.deleteById(cart.getId());
	        }
	      }
	      for (Order order : orders) {
		        if (order.getProduct() == product.getId()) {
		          serviceOrder.deleteById(order.getId());
		        }
		      }
	      service.deleteById(product.getId());
	    }
	  }
	  serviceIng.deleteById(id);
	  return "/deleteIngredient";
	}
	
	@RequestMapping("/deleteProblem/{id}")
	public String deleteProblem(@PathVariable(name = "id") Long id) {
	  List<Product> products = service.findAll();
	  for(Product product : products) {
	    if(product.getIndication()==id||product.getSide_effect()==id) {
	      List<Cart> carts = serviceCart.findAll();
	      List<Order> orders = serviceOrder.findAll();
	      for (Cart cart : carts) {
	        if (cart.getProduct() == product.getId()) {
	          serviceCart.deleteById(cart.getId());
	        }
	      }
	      for (Order order : orders) {
		        if (order.getProduct() == product.getId()) {
		          serviceOrder.deleteById(order.getId());
		        }
		      }
	      service.deleteById(product.getId());
	    }
	  }
	  serviceProb.deleteById(id);
	  return "/deleteProblem";
	}
	
	@RequestMapping("/deleteProducer/{id}")
	public String deleteProducer(@PathVariable(name = "id") Long id) {
	  List<Product> products = service.findAll();
	  for(Product product : products) {
	    if(product.getProducer()==id) {
	      List<Cart> carts = serviceCart.findAll();
	      List<Order> orders = serviceOrder.findAll();
	      for (Cart cart : carts) {
	        if (cart.getProduct() == product.getId()) {
	          serviceCart.deleteById(cart.getId());
	        }
	      }
	      for (Order order : orders) {
		        if (order.getProduct() == product.getId()) {
		          serviceOrder.deleteById(order.getId());
		        }
		      }
	      service.deleteById(product.getId());
	    }
	  }
	  serviceProd.deleteById(id);
	  return "/deleteProducer";
	}
	
	@PostMapping(value="/save_product")
	public String saveProduct(@ModelAttribute("product") Product product) {
	 service.save(product);
	 return "redirect:/products"; }
	
	@RequestMapping("/addProduct")
	public ModelAndView addProduct() {
		ModelAndView mav = new ModelAndView("addProduct");
		List<Problem> problems = serviceProb.findAll();
		List<Producer> producers = serviceProd.findAll();
		List<Ingredient> ingredients = serviceIng.findAll();
		mav.addObject("problem",problems);
		mav.addObject("ingredient", ingredients);
		mav.addObject("producer", producers);
		mav.addObject("product", new Product());
		return mav;
	}
	
	@PostMapping(value="/save_ingredient")
	public String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
	 serviceIng.save(ingredient);
	 return "redirect:/ingredients"; }
	
	@RequestMapping("/addIngredient")
	public ModelAndView addIngredient() {
		ModelAndView mav = new ModelAndView("addIngredient");
		mav.addObject("ingredient", new Ingredient());
		return mav;
	}
	
	@PostMapping(value="/save_problem")
	public String saveProblem(@ModelAttribute("problem") Problem problem) {
	 serviceProb.save(problem);
	 return "redirect:/problems"; }
	
	@RequestMapping("/addProblem")
	public ModelAndView addProblem() {
		ModelAndView mav = new ModelAndView("addProblem");
		mav.addObject("problem", new Problem());
		return mav;
	}
	
	@PostMapping(value="/save_producer")
	public String saveProducer(@ModelAttribute("producer") Producer producer) {
	 serviceProd.save(producer);
	 return "redirect:/producers"; }
	
	@RequestMapping("/addProducer")
	public ModelAndView addProducer() {
		ModelAndView mav = new ModelAndView("addProducer");
		mav.addObject("producer", new Producer());
		return mav;
	}
}

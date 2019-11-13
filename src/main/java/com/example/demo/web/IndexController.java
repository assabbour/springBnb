package com.example.demo.web;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AppartementsRepository;
import com.example.demo.dao.BookingsRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.entities.Appartements;
import com.example.demo.entities.Bookings;
import com.example.demo.entities.Users;

@Controller
public class IndexController {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private AppartementsRepository appartementsRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/*   *******************************              page d'accuiel          ******************************************/
	
	
	@GetMapping("/")
	public String index(Model model,
			//@RequestParam(name="maxPrice")
			 BigDecimal maxPrice,
			//@RequestParam(name="minPrice") 
		BigDecimal minPrice) {
		if(maxPrice==null) {
			maxPrice=new BigDecimal(50.00);
		}
		if(minPrice==null) {
			minPrice=new BigDecimal(40.00);
		}
		List<Appartements> appartements=appartementsRepository.findByPrice(maxPrice, minPrice);
		model.addAttribute("appartements",appartements);
		return "Home";
		 
	}
	
	
	/*   ****************   page d'annonces afficher tous les appartements       **************************/
	
	
	@GetMapping("/index")
	public String getAll(Model model,@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		Page<Appartements> pageAppartements=appartementsRepository.findByTitleContains(mc, PageRequest.of(page, 4));
		model.addAttribute("listAppat",pageAppartements.getContent());
		model.addAttribute("pages", new int[pageAppartements.getTotalPages()]);
		model.addAttribute("currentPage", page);
		
		return "annonces";
	}
	
	
	/*   ****************************************      fin index        *************************************/
	
	
  @GetMapping("/users/profil")
	public String getannonces(Model model,@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="motCle", defaultValue = "") String mc) {
		Page<Appartements> pageAppartements=appartementsRepository.findByTitleContains(mc, PageRequest.of(page, 4));
		model.addAttribute("listAppat",pageAppartements.getContent());
		model.addAttribute("pages", new int[pageAppartements.getTotalPages()]);
		model.addAttribute("currentPage", page);
		
		return "users/profil";
	}
  

 /***************************                    create one appartement                       ***************************/
  
  
  @RequestMapping(value = "/users/create", method = RequestMethod.GET)
  public String createAppartement(Model model, String email) {
	  model.addAttribute("appartement", new Appartements());
      model.addAttribute("userId", this.usersRepository.findByEmail(email).getId());
      return "/users/create";

  }
  
  @RequestMapping(value = "/users/save", method = RequestMethod.POST)
  public String saveAppartement(Integer id,@Valid Appartements aprt,BindingResult bindingResult) {
	  if(bindingResult.hasErrors()) {
		  return "/users/create";
	  }
	  // pour get id connetceter par la jointure
	  aprt.setAppartement(this.usersRepository.getOne(id));
	  aprt.setCreatedAt(new Date());
	  appartementsRepository.save(aprt);
      return "redirect:/index";

  }

  
 /**********************************   **********                  update              **********************************/
  
  
  @RequestMapping(value = "/users/updateAppartement")
	public String updateAppartement(Integer id, Model model) {
	  Appartements aprt = appartementsRepository.getOne(id);
	  model.addAttribute("appartement", aprt);
		return "/users/updateApartForm";
	}
  
  @RequestMapping(value = "/users/updateSaveAppartement", method = RequestMethod.POST)
  public String updateSaveAppartement(String email, Integer id,@Valid Appartements aprt,BindingResult bindingResult) {
	  if(bindingResult.hasErrors()) {
		  return "/users/updateApartForm";
	  }
	  //aprt.setAppartement(this.usersRepository.getOne(id));
	  aprt.setAppartement(this.usersRepository.findByEmail(email));
	  appartementsRepository.save(aprt);
      return "redirect:/index";

  }

  
/************************************                       delete                          *******************************/
  
  
  
  @GetMapping("/delete")
	public String delete(Integer id) {
	  appartementsRepository.deleteById(id);
		return "redirect:/";
	}
  
  

/****************************************                   Login                     **************************************/
  
  @GetMapping("/users/login")
	
 	public String login(Model model) {
 		
 		return "users/login";
 	}
  
  
  /********************************                     detail User                       *****************************/ 
  
  
  @GetMapping("/users/detail")
	public String detail(Integer id, Model model) {
	  Appartements aprt = appartementsRepository.getOne(id);
	  model.addAttribute("appartement", aprt);
	  //create a new booking
	  model.addAttribute("booking", new Bookings());
		return "/users/detail";
	}
  
  
  /********************************                     create booking                         *****************************/
  
  
  @RequestMapping(value = "/users/saveBooking", method = RequestMethod.POST)
  public String saveBooking(Integer id, String email, Bookings booking,  Model model) {
	  booking.setCreatedAt(new Date());
	  booking.setBookings(this.appartementsRepository.getOne(id));
	  booking.setBooking(this.usersRepository.findByEmail(email));
	  booking.setAmount(new BigDecimal( booking.getTotalPrice()));
	  bookingsRepository.save(booking);
	  //get the last bookig create 
	  model.addAttribute("booking",booking);
	  return "/users/bookingconfirm";

  }
  
  /****************************************              profil  user            ******************************************/
  
  
  /*
  @RequestMapping(value ="/users/profiluser")
 	public String userprofil(Integer id, Model model) {
 	  Users use = usersRepository.getOne(id);
 	  
 	  model.addAttribute("user", use);
 	  //System.out.println("===== " + use.getEmail());
 		return "/users/profiluser";
 	}
  
  */
  /*********************** ****************                     403                ******************************************/
  
  
  @RequestMapping(value ="/403")
	public String accessDneied() {
		return "403";
	}
  
  
  
  /****************************************                          test               ******************************************/
  
  
  @RequestMapping(value ="/users/profiluser")
 	public String userprofilemail(String email, Model model) {
 	  Users user = usersRepository.findByEmail(email);
 	  //System.out.println(user.getAdress());
 	  model.addAttribute("user", user);
 		return "/users/profiluser";
 	}
  
  
  
 /************************************************         create User           ******************* ***************************/
  
  
  @RequestMapping(value = "/users/createUser", method = RequestMethod.GET)
  public String createUser(Model model) {
	  model.addAttribute("user", new Users());
      return "/users/signin";

  }
  
  @RequestMapping(value = "/users/saveUser", method = RequestMethod.POST)
  public String saveUser(@Valid Users user,BindingResult bindingResult) {
	  
	  String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
	  if(bindingResult.hasErrors()) {
		  return "/users/signin";
	  }
	  user.setCreatedAt(new Date());
	  user.setActive(1);
	  user.setPassword(hashPW);
	 
	  
	  usersRepository.save(user);
      return "redirect:/index";
  } 
  
  /************************************************         Fin create User           ******************* ***************************/
  
  
  
}

package com.example.demo.Controller;

import com.example.demo.DB.DAO.MenuDAO;
import com.example.demo.DB.DBconnect;
import com.example.demo.DB.DTO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
    @GetMapping("/evaluation")
    public String evaluation(Model model){
        return "evaluation";
    }
    @GetMapping("/payment")
    public String payment(Model model){
        return "payment";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }
    @GetMapping("/support")
    public String support(Model model){
        return "support";
    }
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @ResponseBody
    @PostMapping("/getMenu")
    public List<Food> getMenu(@RequestBody String data){

        System.out.println("data : "+data);

        MenuDAO menuDAO = new MenuDAO();
        List<Food> list = new ArrayList<Food>();
        List<Setmenu> setlist = new ArrayList<Setmenu>();
        List<Drink> drinkList = new ArrayList<Drink>();

        if(!data.equals("음료")) {
            list.addAll(menuDAO.getFood(data));
            setlist.addAll(menuDAO.getSetmenu(data));

            for (Setmenu setmenu : setlist) {
                Food food = new Food();
                food.setFoodname(setmenu.toStringCompoenet());
                food.setPrice(setmenu.getTotalprice());
                food.setImageurl(setmenu.getImageurl());
                list.add(food);
            }
        }else{
            drinkList.addAll(menuDAO.getDrink());
            for (Drink drink : drinkList) {
                Food food = new Food();
                food.setFoodname(drink.getDrinkname());
                food.setPrice(drink.getPrice());
                food.setImageurl(drink.getImageurl());
                list.add(food);
            }

        }

        return list;

    }

    @ResponseBody
    @PostMapping("/getAllergy")
    public List<Allergy> getAllergy(){
        MenuDAO menuDAO = new MenuDAO();
        List<Allergy> list = new ArrayList<Allergy>();

        list.addAll(menuDAO.getAllergyList());
        //.System.out.println("list size : "+list.size());

        return list;
    }

    @ResponseBody
    @PostMapping("/getCoupon")
    public Coupon getCoupon(@RequestBody String data){
        MenuDAO menuDAO = new MenuDAO();
        Coupon coupon = new Coupon();

        coupon = menuDAO.getCoupon(data);

        return coupon;
    }


}

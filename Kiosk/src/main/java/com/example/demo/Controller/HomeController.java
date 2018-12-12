package com.example.demo.Controller;

import com.example.demo.DB.DAO.MenuDAO;
import com.example.demo.DB.DAO.OrderDAO;
import com.example.demo.DB.DAO.StoreDAO;
import com.example.demo.DB.DTO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public List<Menu> getMenu(@RequestBody String data){

        System.out.println("data : "+data);

        MenuDAO menuDAO = new MenuDAO();
        List<Menu> menulist = new ArrayList<>();
        List<Food> foodList = new ArrayList<Food>();
        List<Setmenu> setlist = new ArrayList<Setmenu>();
        List<Drink> drinkList = new ArrayList<Drink>();

        if(!data.equals("음료")) {
            foodList.addAll(menuDAO.getFood(data));
            setlist.addAll(menuDAO.getSetmenu(data));

            for (Food food : foodList) {
                Menu menu = new Menu();
                menu.setName(food.getFoodname());
                menu.setComponent(food.getFoodname());
                menu.setImageurl(food.getImageurl());
                menu.setPrice(food.getPrice());
                menu.setType("food");
                menu.setSoldout(food.getSoldout());
                menulist.add(menu);
            }
            for (Setmenu setmenu : setlist) {
                Menu menu = new Menu();
                menu.setName(setmenu.getSetmenuid());
                menu.setComponent(setmenu.toStringCompoenet());
                menu.setImageurl(setmenu.getImageurl());
                menu.setPrice(setmenu.getTotalprice());
                menu.setType("set");
                menu.setSoldout(setmenu.getSoldout());
                menulist.add(menu);
            }
        }else{
            drinkList.addAll(menuDAO.getDrink());
            for (Drink drink : drinkList) {
                Menu menu = new Menu();
                menu.setName(drink.getDrinkname());
                menu.setComponent(drink.getDrinkname());
                menu.setImageurl(drink.getImageurl());
                menu.setPrice(drink.getPrice());
                menu.setType("drink");
                menulist.add(menu);
            }

        }

        return menulist;

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


    @ResponseBody
    @PostMapping("/insertOrder")
    public String insertOrder(@RequestBody String data){

        OrderDAO orderDAO = new OrderDAO();

        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyymmddhhmmss");
        String ordercode = dayTime.format(new Date(time));

        String splitdata[] = data.split("_+");

        String[] name = new String[splitdata.length];
        String[] type = new String[splitdata.length];
        int cnt = 0;

       System.out.println(data);

        for(int i=4;i<splitdata.length+3;i+=4){
            for(int j=0;j<Integer.parseInt(splitdata[i-2]);j++) {
                type[cnt] = splitdata[i - 1];
                name[cnt++] = splitdata[i - 4];
            }
        }
        int totalprice = Integer.parseInt(splitdata[splitdata.length-1]);
        orderDAO.insertOrder(ordercode, type,name,cnt, totalprice);

        return ordercode;

    }

    @ResponseBody
    @PostMapping("/getStoreinfo")
    public Store getStorinfo(){
        Store store = new Store();

        StoreDAO storeDAO = new StoreDAO();

        store = storeDAO.getRandomStore();

        System.out.println(store.getPhone());
        System.out.println(store.getAddress());

        return store;
    }


}

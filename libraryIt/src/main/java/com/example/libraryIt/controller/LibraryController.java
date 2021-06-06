package com.example.libraryIt.controller;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletResponse;
import com.example.libraryIt.History;
import com.example.libraryIt.HistoryRepository;
import com.example.libraryIt.Item;
import com.example.libraryIt.ItemRepository;
import com.example.libraryIt.User;
import com.example.libraryIt.UserRepository;
import com.example.libraryIt.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LibraryController{

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    private UserSession userSession;

    @GetMapping("/")
    public String homeView(Model model){
        
        model.addAttribute("user", userSession.getUser());
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("popular", itemRepository.findTop3ByOrderByTimesLoanedDesc());
        return "homePage.html";
    }

    @GetMapping("/navigate")
    public String navigate(Model model){

        model.addAttribute("user", userSession.getUser());
        model.addAttribute("items", itemRepository.findAll());
        return "navigate.html";
    }

    @GetMapping("/itemSearch")
    public String itemSearch(@RequestParam("title") String title, Model model){
        model.addAttribute("user", userSession.getUser());
        model.addAttribute("items", itemRepository.findByTitleContainingIgnoreCase(title));
        return "navigate.html";
    }

    @GetMapping("/addItem")
    public String addItem(@RequestParam("title") String title, @RequestParam("type") String type, @RequestParam("genre") String genre, Model model){

        Item item = new Item();
        item.setTitle(title);
        item.setType(type);
        item.setGenre(genre);
        item.setDateReservedFrom(null);
        item.setDateReservedUntil(null);
        item.setOnLoan(false);
        item.setDateLoaned(null);
        item.setDateDue(null);
        item.setTimesLoaned(0);
        itemRepository.save(item);

        model.addAttribute("user", userSession.getUser());
        model.addAttribute("items", itemRepository.findAll());
        return "navigate.html";
    }

    @GetMapping("/deleteItem")
    public void deleteItem(@RequestParam("id") Long id, HttpServletResponse response) throws Exception{
        itemRepository.deleteById(id);
        response.sendRedirect("/navigate");
    }

    @GetMapping("/loan")
    public void loan(@RequestParam("id") Long id, HttpServletResponse response) throws Exception{
        if(userSession.getUser() == null){
            response.sendRedirect("/login");
        }
        else
        {
            Item item = itemRepository.getOne(id);
            User user = userSession.getUser();
            History history = new History();

            history.setItemId(item.getId());
            history.setUserId(user.getId());
            history.setItemTitle(item.getTitle());
            history.setItemType(item.getType());
            history.setItemGenre(item.getGenre());
            history.setReturnedOn(null);
            item.setTimesLoaned(item.getTimesLoaned() + 1);
            item.setUser(user);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String temp = sdf.format(c.getTime());
            history.setLoanedOn(sdf.parse(temp));
            c.add(Calendar.DAY_OF_WEEK, 7);
            temp = sdf.format(c.getTime());
            item.setDateDue(sdf.parse(temp));
           
            item.setOnLoan(true);
            itemRepository.save(item);
            historyRepository.save(history);
            
            response.sendRedirect("/navigate");
        }
       
        //model.addAttribute("items", itemRepository.findAllByOwner(userSession.getUser().getId()));
    }

    @GetMapping("/renewLoan")
    public void renewLoan(@RequestParam("id") Long id, HttpServletResponse response) throws Exception{
        if(userSession.getUser() == null){
            response.sendRedirect("/login");
        }
        else
        {
            Item item = itemRepository.getOne(id);
            User user = userSession.getUser();
            item.setUser(user);

            Date d = item.getDateDue();
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.add(Calendar.DAY_OF_WEEK, 7);
            String temp = sdf.format(cal.getTime());
            item.setDateDue(sdf.parse(temp));
            item.setOnLoan(true);
            itemRepository.save(item);
            response.sendRedirect("/userLoans");
        }       
    }


    @GetMapping("/reserve")
    public void reserve(@RequestParam("id") Long id, HttpServletResponse response) throws Exception{
        if(userSession.getUser() == null){
            response.sendRedirect("/login");
        }
        else
        {
            Item item = itemRepository.getOne(id);
            User user = userSession.getUser();
            item.setReservedUser(user);
            item.setReserved(true);

            Calendar c = Calendar.getInstance();
            c.setTime(item.getDateDue());
            c.add(Calendar.DAY_OF_WEEK, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String temp = sdf.format(c.getTime());
            item.setDateReservedFrom(sdf.parse(temp));

            c.add(Calendar.DAY_OF_WEEK, 7);
            temp = sdf.format(c.getTime());
            item.setDateReservedUntil(sdf.parse(temp));

            itemRepository.save(item);

            response.sendRedirect("/");
        }
    }

    @GetMapping("/renewAsLib")
    public void renewAsLib(@RequestParam("id") Long id, HttpServletResponse response) throws Exception{

        if(userSession.getUser() == null){
            response.sendRedirect("/");
        }
        else 
        {
            Item item = itemRepository.getOne(id);
            User user = item.getUser();
            Long ll = user.getId();
            Date d = item.getDateDue();
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            cal.add(Calendar.DAY_OF_WEEK, 7);
            String temp = sdf.format(cal.getTime());
            item.setDateDue(sdf.parse(temp));
            item.setOnLoan(true);
            itemRepository.save(item);

            response.sendRedirect("/");
            // response.sendRedirect("/memberLoans?id=\ll");
        }
    }

    @GetMapping("/cancelReservation")
    public void cancelReservation(@RequestParam("id") Long id, Model model, HttpServletResponse response) throws Exception{
        Item item = itemRepository.getOne(id);

        item.setReserved(false);
        item.setReservedUser(null);
        item.setDateReservedFrom(null);
        item.setDateReservedUntil(null);

        itemRepository.save(item);


        response.sendRedirect("/userLoans");


    }

    @GetMapping("/itemReturn")
    public void itemReturn(@RequestParam("id") Long id, HttpServletResponse response) throws Exception{
        if(userSession.getUser() == null){
            response.sendRedirect("/");
        }
        else 
        {
            Item item = itemRepository.getOne(id);
            User user = item.getUser();
            
            History history = historyRepository.findOneByUserIdAndItemIdAndReturnedOnIsNull(user.getId(), item.getId());

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String temp = sdf.format(c.getTime());
            history.setReturnedOn(sdf.parse(temp));


            item.setUser(null);
            item.setDateDue(null);
            item.setOnLoan(false);
            item.setDateLoaned(null);

            itemRepository.save(item);
            historyRepository.save(history);
            response.sendRedirect("/userLoans");
            //May need to change redirect for librarian
        }
    }

    @GetMapping("/register")
    public String registerView(Model model, HttpServletResponse response) throws Exception{
        if(userSession.isLoginFailed()){
            model.addAttribute("error", "Unable to create account, passwords do not match");
            userSession.setLoginFailed(false);
        }
        if (userSession.getUser() != null){
            response.sendRedirect("/logout");
        }
        return "registerPage.html";
    }

    @PostMapping("/register")
    public void createUser(String username, String password, String passwordDuplicate, HttpServletResponse response) throws Exception{
        
    if(password.equals(passwordDuplicate)){
        User user = new User();
        user.setRole("member");
        user.setUserName(username);
        user.setPassword(password);
        userRepository.save(user);
        userSession.setUser(user);
        response.sendRedirect("/");
    }
    else{
        userSession.setLoginFailed(true);
        response.sendRedirect("/register");
    }
    }


    @GetMapping("/userProfile")
    public String userProfile(Model model) {
        model.addAttribute("user", userSession.getUser());
        model.addAttribute("items", itemRepository.findAllByUser(userSession.getUser()));
        return "userProfile.html";
    }

    

    @GetMapping("/userLoans")
    public String userLoans(Model model) {
        User user = userSession.getUser();
        model.addAttribute("user", user);
        model.addAttribute("items", itemRepository.findAllByUser(user));
        model.addAttribute("reservations", itemRepository.findAllByReservedUser(user));
        model.addAttribute("history", historyRepository.findAllByUserId(user.getId()));

        return "userLoans.html";
    }


    // @GetMapping("/memberLoans")
    // public String memberLoans(@RequestParameter("user") User user, Model model){

    // }
    @GetMapping("/membersLoans")
    public String memberLoans(@RequestParam("id") Long id, Model model){

        User user = userRepository.getOne(id);
        model.addAttribute("user2", user);
        model.addAttribute("items", itemRepository.findAllByUser(user));
        model.addAttribute("history", historyRepository.findAllByUserId(user.getId()));
        model.addAttribute("reservations", itemRepository.findAllByReservedUser(user) );

        return "userLoans.html";
    }

    @GetMapping("/editMembers")
    public String editMembers(Model model, HttpServletResponse response) throws Exception{
            model.addAttribute("users", userRepository.findAll());
            return "memberList.html";
    }

    @GetMapping("/memberSearch")
    public String editMembers(@RequestParam("username") String username, Model model){
        model.addAttribute("user", userSession.getUser());
        model.addAttribute("users", userRepository.findByUsernameContainingIgnoreCase(username));
        return "memberList.html";
    }


    @PostMapping("/editProfile")
    public void editProfile(String newUsername, String password, String passwordDuplicate, HttpServletResponse response)
            throws Exception {

        User user = userSession.getUser();

        
        if (password.equals(passwordDuplicate)) {

            user.setUserName(newUsername);
            user.setPassword(password);
            userRepository.save(user);
            response.sendRedirect("/");
        } else {
            userSession.setLoginFailed(true);
            response.sendRedirect("/");
        }
    }
}
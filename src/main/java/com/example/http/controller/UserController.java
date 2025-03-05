package com.example.http.controller;

import com.example.http.dto.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @GetMapping("/hello")
    public String homePage() {
        return "index";
    }

    //Получение параметров запроса
    @GetMapping("/user/search")
    @ResponseBody
    public String searchingUser(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return "Searching user: " + name + ", Age: " + age;
    }

    //Обработка сегментов пути
    @GetMapping("/user/{id}")
    @ResponseBody
    public String getUserId(@PathVariable("id") Integer id) {
        return "User id: " + id;
    }

    //Обработка тела запроса
    @PostMapping("/user")
    @ResponseBody
    public String receiveJson(@RequestBody User user) {
        return "User created: " + user.name() + ", Age: " + user.age();
    }

    //Обработка заголовков запроса
    @GetMapping("/user/info")
    @ResponseBody
    public String readHeaders(@RequestHeader("User-Agent") String userAgent) {
        return "Your User-Agent: " + userAgent;
    }

    //Работа с cookies
    @GetMapping("/user/profile")
    @ResponseBody
    public String readCookieUsername(@CookieValue(value = "username", defaultValue = "Guest") String username) {
        return "Hello, " + username;
    }

    //Установка cookies
    @GetMapping("/set-cookie")
    @ResponseBody
    public Map<String, String> setCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("username", "Alice")
                .maxAge(3600)
                .path("/")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        Map<String, String> res = new HashMap<>();
        res.put("message", "Cookie set!");
        return res;
    }
}

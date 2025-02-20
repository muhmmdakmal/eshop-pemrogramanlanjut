package id.ac.ui.cs.advprogid.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Home"; // Mengarahkan ke view "home.html"
    }
}

package com.defensoria.integral.controller.almacen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
@RequestMapping("/")
    public String index() {
        System.out.println("llamando index ");
        return "index";
    }
	

}

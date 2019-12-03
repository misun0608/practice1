package com.spring.springhello3;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);   // 콘솔창에 간단한 log정보를 띄워줌
   
   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "*.me", method = RequestMethod.GET)   // 특정한 url형식과 메소드를 연결
   public String home(Locale locale, Model model) {
      logger.info("Welcome home! The client locale is {}.", locale);
      
      Date date = new Date();
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
      
      String formattedDate = dateFormat.format(date);   // dateFormat.format(date) / 편집된 날짜를 문자열로 반환
      
      model.addAttribute("serverTime", formattedDate );
      
      return "home";   // view 이름이 home으로 설정
   }
   
}
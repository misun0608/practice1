package com.spring.springjsoup2;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/crawl.do", method = RequestMethod.GET)
	public ModelAndView crawl(@RequestParam(value="sel", required=false, defaultValue="cnt") String sel, ModelAndView model) {

		String url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=" + sel + "&date=20191216";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("table.list_ranking");
		System.out.println("################ table.list_ranking ###################");
		//System.out.println(element);
		
		ArrayList<String> list_title = new ArrayList<String>(); 
		ArrayList<String> list_point = new ArrayList<String>();
		
		for(Element el : element.select("tr")) {
			String title = el.select("a[class!='txt_link']").text().toString();
			String point = el.select("td.point").toString();
			//System.out.println(title);
			//System.out.println(point);
			//System.out.println("====================================================");
			if(!(title.equals(""))) {
				list_title.add(title);
				list_point.add(point);
			}

		}
		model.addObject("list_title", list_title);
		model.addObject("list_point", list_point);
		model.setViewName("crawl");
		return model;
	}
	
}

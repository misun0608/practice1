package com.spring.springjsoup;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/crawl.do", method = RequestMethod.GET)
	public ModelAndView crawl(ModelAndView model) {
		//Jsoup : https://jsoup.org/
		//Jsoup를 이용해서 네이버 스포츠 크롤링
		String url = "https://sports.news.naver.com/wfootball/index.nhn";
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();	// 해당사이트 url 들어가서 내용을 읽어오겠다 html 형식으로
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// 주요 뉴스로 나오는 태그를 찾아서 가져오도록 한다.
		Elements element = doc.select("div.home_news");	// doc는 문서 전체를 의미 / div태그에서 클래스가 home_news인거
		System.out.println("##########div.home_news #############");
		System.out.println(element);
		
		// 1. 헤더 부분의 제목을 가져온다.
		String title = element.select("h2").text().substring(0,4);
		
		System.out.println("==============================");
		System.out.println(title);
		System.out.println("==============================");
		
		ArrayList<String> list_text = new ArrayList<String>();
		ArrayList<String> list_link = new ArrayList<String>();
		
		// 2. 하위 뉴스 기사들을 for문 돌면서 출력
		for(Element el : element.select("li")) {
			String text = el.text().toString();
			String link = "https://sports.news.naver.com" + el.select("a").attr("href");	// fullpath 만드는 거
			System.out.println(text);
			System.out.println(link);
			
			System.out.println("------------------------------------");
			list_text.add(text);
			list_link.add(link);
		}
		
		model.addObject("title", title);
		model.addObject("list_text", list_text);
		model.addObject("list_link", list_link);
		model.setViewName("crawl");
		
		return model;
	}
}

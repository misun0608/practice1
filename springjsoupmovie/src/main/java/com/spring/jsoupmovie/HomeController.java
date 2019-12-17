package com.spring.jsoupmovie;

import java.io.IOException;
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
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/crawl.do", method = RequestMethod.GET)
	public ModelAndView crawl(@RequestParam(value="sel", required=false, defaultValue="cnt") String sel, ModelAndView model) {
		//Jsoup : https://jsoup.org/
		
		// 조회순
		String url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=" + sel + "&date=20191216";
//		String url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=cnt&date=20191216";
		// 평점순(현재상영영화)
//		String url2 = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=cur&date=20191216";
		// 평점순(모든영화)
//		String url3 = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20191216";
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();	// 해당사이트 url 들어가서 내용을 읽어오겠다 html 형식으로
		}catch(IOException e) {
			e.printStackTrace();
		}
		// 탭 가져오기
		Elements tab = doc.select("div.tab_type_6");
		System.out.println("테에에에에에에에에에에엡");
		System.out.println(tab);
		
		ArrayList<String> list_tabimg = new ArrayList<String>();
		ArrayList<String> list_tablink = new ArrayList<String>();
		for(Element tab1 : tab.select("li")) {
			String src = tab1.select("img").attr("src");
			String tablink = "https://movie.naver.com/movie/sdb/rank/" + tab1.select("a").attr("href");
			System.out.println(src);
			System.out.println(tablink);
			
			list_tabimg.add(src);
			list_tablink.add(tablink);
		}
		
		// 조회순 순위
		Elements element = doc.select("table.list_ranking");	// doc는 문서 전체를 의미 / div태그에서 클래스가 home_news인거
		System.out.println("########## table.list_ranking #############");
		System.out.println(element);
		
//		// 1. 헤더 부분의 제목을 가져온다.
//		String title = element.select("h2").text().substring(0,4);
//		
//		System.out.println("==============================");
//		System.out.println(title);
//		System.out.println("==============================");
		
//		// 1. 순위 긁어온다
//		ArrayList<String> list_rank = new ArrayList<String>();
//		for(Element rank : element.select("td.ac")) {
//			
//			System.out.println(rank);
//		}
		
		// 탭 이름 넣기
//		ArrayList<String> list_name = new ArrayList<String>();
//		for(Element n : element.select("div.type_6 li")) {
//			String name = n.select("img").attr("alt");
//			System.out.println(name);
//			list_name.add(name);
//		}
		
		// 제목 긁기
		ArrayList<String> list_title = new ArrayList<String>();
		ArrayList<String> list_point = new ArrayList<String>();
		
		for(Element title : element.select("td.title")) {
			String tl = title.select("a").attr("title");
			System.out.println(tl);
			list_title.add(tl);
		}
		
		for(Element point : element.select("td.point")) {
			String po = point.text().toString();
			System.out.println(po);
			list_point.add(po);
		}
		
		
		//model.addObject("list_tablink", list_tablink);
		// model.addObject("list_tabimg", list_tabimg);
		model.addObject("list_title", list_title);
		model.addObject("list_point", list_point);
		// model.addObject("list_name", list_name);
		model.setViewName("crawl");
		
		return model;
	}
	
}

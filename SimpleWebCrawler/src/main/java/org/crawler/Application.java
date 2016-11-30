package org.crawler;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Application {

	public static void main(String[] args) {
		WebCrawlerService webCrawlerService =  new WebCrawlerServiceImpl();
		String baseUrl= "http://wiprodigital.com/";
		Set<Page> pagesVisitedSoFar = new HashSet<Page>();
		Page rootPage = new Page(baseUrl);
		Page page = null;
		webCrawlerService.parsePage(baseUrl, rootPage, Optional.ofNullable(page), pagesVisitedSoFar);
		webCrawlerService.exportToFile(rootPage, "src/main/resources/siteMap.txt");
	}

}

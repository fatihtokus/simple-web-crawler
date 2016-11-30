package org.crawler;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
public class WebCrawlerServiceImplTest {
	@Test
	public void initialPageTest() {
		
		WebCrawlerService webCrawlerService =  new WebCrawlerServiceImpl();
		String url= "http://wiprodigital.com/";

		Set<Page> pageSetSoFar = new HashSet<Page>();
		Page processPage = new Page(url);
		webCrawlerService.parsePage(url, processPage, Optional.empty(), pageSetSoFar);
		pageSetSoFar.stream().forEach(page -> {			
			System.out.println("- "+ page.getUrl());			
		});		
		assertThat(pageSetSoFar, hasItem(new Page("http://wiprodigital.com/")));
		
	}
}

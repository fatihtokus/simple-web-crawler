package org.crawler;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class WebCrawlerServiceImplTest {
	
	private WebCrawlerService webCrawlerService;
	private Set<Page> pagesVisitedSoFar;
	private Page processPage;
	private String url;
	
	@Before
	public void init() {
		webCrawlerService =  new WebCrawlerServiceImpl();
		url= "http://wiprodigital.com/";
		pagesVisitedSoFar = new HashSet<Page>();
		processPage = new Page(url);
		webCrawlerService.parsePage(url, processPage, Optional.empty(), pagesVisitedSoFar);		
	}	
	
	@Test
	public void initialPageTest() {		
		assertThat(pagesVisitedSoFar, hasItem(new Page("http://wiprodigital.com/")));		
	}
	
	@Test
	public void externalLinkTest() {		
		assertThat(processPage.getLinksToExternal(), hasItem("https://twitter.com/wiprodigital"));
		assertThat(pagesVisitedSoFar, not(hasItem(new Page("https://twitter.com/wiprodigital"))));	
	}
}

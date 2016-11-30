package org.crawler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

import java.io.File;
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
		Page page = null;
		webCrawlerService.parsePage(url, processPage, Optional.ofNullable(page), pagesVisitedSoFar);		
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
	
	@Test
	public void staticContentLinkImgTest() {
		assertThat(processPage.getLinksToStaticContent(), hasItem("http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/themes/wiprodigital/images/wdlogo.png"));
		assertThat(pagesVisitedSoFar, not(hasItem(new Page("http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/themes/wiprodigital/images/wdlogo.png"))));
	}
	
	@Test
	public void staticContentLinkLinkTest() {
		assertThat(processPage.getLinksToStaticContent(), hasItem("http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/uploads/2016/08/Fav_icon_144x144.png"));
		assertThat(pagesVisitedSoFar, not(hasItem(new Page("http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/uploads/2016/08/Fav_icon_144x144.png"))));
	}
	
	@Test
	public void exportToFileTest() {
		String filePath = "src/main/resources/siteMap.txt";
		webCrawlerService.exportToFile(processPage, filePath);
		File file = new File(filePath);
		assertThat("File '" + file + "' exists", file.exists(), is(true));
	}
	
	
}

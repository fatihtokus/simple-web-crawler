package org.crawler;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawlerServiceImpl implements WebCrawlerService {
	public void parsePage(String baseUrl, Page processPage, Optional<Page> parentPage, Set<Page> pagesVisitedSoFar) {
		String url = processPage.getUrl();
		if (!pagesVisitedSoFar.contains(processPage)) {
			Document pageHtml;
			try {
				pageHtml = Jsoup.connect(url).get();
				// store the page to the set to avoid parsing again
				pagesVisitedSoFar.add(processPage);
			} catch (IOException e) {
				return;// if you cannot connect the page omit it.
			}

			findAHrefLink(baseUrl, processPage, pagesVisitedSoFar, pageHtml);
			
			findImgLink(baseUrl, processPage, pagesVisitedSoFar, pageHtml);			
			
			findLinkLink(baseUrl, processPage, pagesVisitedSoFar, pageHtml);	
			
			if (parentPage.isPresent()) {
				parentPage.get().getLinksToOtherPages().add(processPage);
			}
		}
	}

	private void findAHrefLink(String baseUrl, Page processPage, Set<Page> pagesVisitedSoFar, Document pageHtml) {
		Elements links = pageHtml.select("a[href]");
		for (Element link : links) {
			String href = link.attr("abs:href");
			if (href.startsWith(baseUrl)) {
				// get all links and recursively call the parsePage method
				parsePage(baseUrl, new Page(href), Optional.ofNullable(processPage), pagesVisitedSoFar);
			} else if (!processPage.getLinksToExternal().contains(href)) {
				processPage.getLinksToExternal().add(href);
			}
		}
	}
	
	private void findImgLink(String baseUrl, Page processPage, Set<Page> pagesVisitedSoFar, Document pageHtml) {
		Elements links = pageHtml.select("img");
		for (Element link : links) {
			String href = link.attr("src");
			if (href.startsWith(baseUrl)) {
				// get all links and recursively call the parsePage method
				parsePage(baseUrl, new Page(href), Optional.ofNullable(processPage), pagesVisitedSoFar);
			} else if (!processPage.getLinksToStaticContent().contains(href)) {
				processPage.getLinksToStaticContent().add(href);
			}
		}
	}
	
	private void findLinkLink(String baseUrl, Page processPage, Set<Page> pagesVisitedSoFar, Document pageHtml) {
		Elements links = pageHtml.select("link");
		for (Element link : links) {
			String href = link.attr("abs:href");
			if (href.startsWith(baseUrl)) {
				// get all links and recursively call the parsePage method
				parsePage(baseUrl, new Page(href), Optional.ofNullable(processPage), pagesVisitedSoFar);
			} else if (!processPage.getLinksToStaticContent().contains(href)) {
				processPage.getLinksToStaticContent().add(href);
			}
		}
	}
}

package org.crawler;

import java.util.Optional;
import java.util.Set;

public interface WebCrawlerService {
	public void parsePage(String baseUrl, Page processPage, Optional<Page> parentPage, Set<Page> pagesVisitedSoFar);
}

package org.crawler;

import java.util.HashSet;
import java.util.Set;

public class Page {
private String url;
private Set<Page> linksToOtherPages = new HashSet<Page>();
private Set<String> linksToExternal = new HashSet<String>();
private Set<String> linksToStaticContent = new HashSet<String>();

public Page(String url){
	this.url = url;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}

public Set<Page> getLinksToOtherPages() {
	return linksToOtherPages;
}
public void setLinksToOtherPages(Set<Page> linksToOtherPages) {
	this.linksToOtherPages = linksToOtherPages;
}
public Set<String> getLinksToExternal() {
	return linksToExternal;
}
public void setLinksToExternal(Set<String> linksToExternal) {
	this.linksToExternal = linksToExternal;
}
public Set<String> getLinksToStaticContent() {
	return linksToStaticContent;
}
public void setLinksToStaticContent(Set<String> linksToStaticContent) {
	this.linksToStaticContent = linksToStaticContent;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((url == null) ? 0 : url.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Page other = (Page) obj;
	if (url == null) {
		if (other.url != null)
			return false;
	} else if (!url.equals(other.url))
		return false;
	return true;
}
@Override
public String toString() {
	return "Page [url=" + url + ", linksToOtherPages=" + linksToOtherPages + ", linksToExternal=" + linksToExternal
			+ ", linksToStaticContent=" + linksToStaticContent + "]";
}

}

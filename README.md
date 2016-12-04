# simple-web-crawler

Simple web crawler is a crawler to crawl web pages on the same domain. It is output a JSON txt file. This file includes the sitemap of the website and,  showing links to other pages under the same domain, links to external URLs and links to static content such as images for each respective page.

1- How to build and run your solution:

- Switch to the Git Repository Exploring perspective in Eclipse.

- Click Clone a Git Repository and type the URI https://github.com/fatihtokus/simple-web-crawler.git

- Keep the master branch ticked and untick the other checkboxes. Click Next >

- Make any changes you wish in the Local Destination dialogue and click Finish

- Wait for the repository to be cloned—this may take a couple of minutes

- In Eclipse Indigo, right-click the repository and select Import Maven Projects.... 

- Select the project. Click Finish.

- Wait for the project to be imported, and the indexing to finish.

- Go to "Project" menu select Clean.

- Open "org.crawler.Application.java" file, right-click Run As -> Java Application

- After the program finishes, It will create an output which includes site map of the the domain 

2- Reasoning and describe any trade offs:

It is a good solution to use recursive algorithm for this kind of problems.

3- Explanation of what could be done with more time
- We could process static imported script files (e.g. <script type="text/javascript" src=" etc.)

- Every page can be processed with different thread to get better performance.

- Sitemap result can be inserted into database to analyse. 

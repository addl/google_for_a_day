package indexer.api.server.crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String URL = "http://localhost:5000/";
			Document doc = Jsoup.connect(URL).get();
			Elements links = doc.getElementsByTag("a");
			Set<String> lista = new HashSet<String>();
			for (Element element : links) {
				String absUrl = element.absUrl("href");
				System.out.println(absUrl);
				lista.add(absUrl);
			}
			for (String string : lista) {
				System.out.println("Link: " + string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

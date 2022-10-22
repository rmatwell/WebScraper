package org.rmatwell.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * @author Richard Atwell
 */
public class App {

    public static void main(String[] args){
        final String url =
                "https://www.microcenter.com/search/search_results.aspx?N=4294966937&NTK=all&sortby=match&rpp=96";
        final String rootURL = "https://www.microcenter.com";

        try {
            final Document document = Jsoup.connect(url).get();

            for(Element listing: document.select(
                    "li.product_wrapper"
            )){
                String name = listing.select("li.product_wrapper h2").text();
                String sku = listing.select("p.sku").text();
                String price = listing.select("div.price").text();
                String relativeURL = listing.select("a.image").attr("href");
                String absoluteURL = rootURL + "" + relativeURL;
                System.out.println(name + "  -  " + sku + "  -  " + price + "  -  " + absoluteURL);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

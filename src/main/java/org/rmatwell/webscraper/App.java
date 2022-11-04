package org.rmatwell.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * @author Richard Atwell
 */
public class App {

    public static void main(String[] args){
        final String url =
                "https://www.microcenter.com/search/search_results.aspx?N=4294966937&rpp=96&myStore=false" +
                        "&sortby=pricehigh&NTK=all&page=";

        final String rootURL = "https://www.microcenter.com";

        ArrayList<Listing> listings = new ArrayList<>();
        int pages = 1, checkedPage = 1;
        int count = 0;
        boolean arePagesChecked = false;
        try {

            while(pages >= checkedPage){
                String urlPage = url + checkedPage;

                Document document = Jsoup.connect(urlPage)
                    .followRedirects(true)
                    .userAgent("Mozilla")
                    .referrer("https://www.google.com")
                    .cookie("auth", "token")
                    .timeout(10000)
                    .get();

            if(!arePagesChecked){
                String s = document.select("p.status").text();
                System.out.println(s);
                pages = getPages(s);
                arePagesChecked = true;
            }

            for(Element listing: document.select(
                    "li.product_wrapper"

            )) {

                Element priceLink = listing.select("a[class^=image]").first();
                System.out.println(count);
                assert priceLink != null;
                if(priceLink.attr("data-price").isEmpty()){
                   continue;
                }

                String brand = listing.select("a[class^=image]").attr("data-brand");
                System.out.println(brand);
                double price = Double.parseDouble(priceLink.attr("data-price"));
                System.out.println(price);
                String relativeURL = listing.select("a[class^=image]").attr("href");
                String absoluteURL = rootURL + "" + relativeURL;
                System.out.println(absoluteURL);
                final Document doc = Jsoup.connect(absoluteURL)
                        .followRedirects(true)
                        .userAgent("Mozilla")
                        .referrer("https://www.google.com")
                        .cookie("auth", "token")
                        .timeout(10000)
                        .get().body().ownerDocument();

                assert doc != null;
                String partNum = doc.select("div:containsOwn(Mfr) + div").text();
                System.out.println(partNum);
                String chipSet = doc.select("div:containsOwn(GPU Chipset) + div").text();
                System.out.println(chipSet);
                Listing list = new Listing(count++, partNum, brand, price, chipSet, absoluteURL);
                listings.add(list);
                doc.empty();
            }
                document.empty();
                checkedPage++;
            }
            for(Listing l: listings){
                System.out.println(l.toString());
            }
            CSVReport report = new CSVReport("sample.csv");
            report.writeDataAtOnce(listings);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getPages(String s){

        int itemsPerPage = 96;
        int l = s.indexOf("of") + 3, r = l;

        while(!Character.isWhitespace(s.charAt(r)))
            r++;

        String ans = s.substring(l,r);

        int items = Integer.parseInt(ans);

        if(itemsPerPage >= items){
            return 1;
        }
        int pages = (int) Math.ceil((double)items / itemsPerPage);

        return pages;
    }
}

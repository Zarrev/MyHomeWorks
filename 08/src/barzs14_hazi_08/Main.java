package barzs14_hazi_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * Erdemes a kovetkezo linkekkel tesztelni: http://www.math.u-szeged.hu/~katai/linalg/feladatok.html ; http://zetcode.com/gui/winapi/main/
 * 1. Elonye, hogy ossz-vissz 9 link van rajta es veget er emberi idoben a program futasa.
 * 2. Egy link tobbszor is elofordul, igy lathato a rendezes.
 * @author zsolt
 * 
 */

public class Main {
	public static Map<String, Integer> found = new HashMap<>();
    public static final String[] FORBIDDEN_TERMS = { "bbs", "forum", "download","javascript", "copyright", "video", "schedule", "picture", "comment","price", "about", "contact", "privacy", "forward", "email", "print"};
    public static Integer index = 0;
    
	public static void main(String args[]){
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Give me a \"http://\" URL: ");
		URL url = null;
		try {
			url = new URL(stdIn.readLine());
			found.put(url.toString(), 1);
			stdIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		letMeSee();
		writeOut();
	}
	
	/**
	 * Az URL helyességét vizsgálja.
	 * @param url
	 * 			Az URL String formaban.
	 * @return
	 */
	public static boolean correct(String url){
	    if(!url.contains("http")){
	    	return false;
	    }
	    for(int i = 0; i < FORBIDDEN_TERMS.length; i++){
	    	if (url.contains(FORBIDDEN_TERMS[i])){
	    		return false;
	    	}
	    }
	    return true;
	}
	/**
	 * Egy ideiglenes vektorba pakolom a meg nem tekintett linkeket, amiket megnyitok, ami soran ujak kerulnek a "found"-ba.
	 * Rekurzivan meghivom a fuggvenyt, ha nincs tobb uj elem, ami az "index"-bol es a "found.size()"-bol kovetkezik.
	 */
	public static void letMeSee(){
		boolean isItEnd = false;
		
		Vector<String> temp = new Vector<>();
		int hashMapSizeAct = 1;
		for (String o : found.keySet()){
			if(Main.index < hashMapSizeAct)
				temp.add(o);
			hashMapSizeAct++;
		}
		Main.index = found.size();
		for (String o : temp) {
			URL url = null;
			try {
				url = new URL(o);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			Reader reader = null;
			try {
				reader = new InputStreamReader(url.openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				new ParserDelegator().parse(reader, new LinkPage(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(Main.index-found.size() == 0)
			isItEnd = true;
		
		if(!isItEnd)
			letMeSee();
	}
	/**
	 * Ezt a kis aranyost az internetetn talaltam.
	 * ("Collections.reverseOrder()" nelkul ez novekvo sorrendbe rendezne)
	 * @link
	 * http://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values-java
	 */
	public static void writeOut(){

		System.out.println(
				found.entrySet()
		        .stream()
		        .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
		        .collect(Collectors.toMap(
		        Map.Entry::getKey, 
		        Map.Entry::getValue, 
		        (e1, e2) -> e1, 
		        LinkedHashMap::new)));
	}
}

/**
 * A href-es tagokat megkeresem es a helyes URL cimeket lementem a "found"-ba.
 * Ez a LetMeSee() fv-ben hivodik meg amikor a "parse" fv-t hivom meg.
 * 
 * A java2s.com segitsegevel csinaltam.
 * @link 
 * http://www.java2s.com/Code/Java/Network-Protocol/HTML-Parser.htm
 * @author zsolt
 * 
 */
class LinkPage extends HTMLEditorKit.ParserCallback {

	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		if (t == HTML.Tag.A) {
			String url = new String("null");
			if(a.getAttribute(HTML.Attribute.HREF) != null && Main.correct(a.getAttribute(HTML.Attribute.HREF).toString())){
					url = a.getAttribute(HTML.Attribute.HREF).toString();
					//System.out.println(url);
				if (Main.found.containsKey(url)) {
					Integer i = Main.found.get(url) + 1;
					Main.found.put(url, i);
				} else {
					Main.found.put(url, 1);
				}
			}
		}
	}

}
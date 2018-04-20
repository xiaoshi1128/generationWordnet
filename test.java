package generationWordNet;
import java.util.ArrayList;
import java.util.HashMap;

import generationWordNet.Similarity;
import generationWordNet.getHypernym;
import generationWordNet.getAnoHpy;

import generationWordNet.getHypernymTree;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
public class test {
	public static void main(String[] args) throws IOException {
		
		String wnhome = System.getenv("WNHOME");  
		String path = wnhome + File.separator + "dict";  
		URL url = new URL("file", null, path);
		//建立词典对象并打开它。  
		IDictionary dict = new Dictionary(url);  
		dict.open(); 
		String[] words=new String[10];
//		words[0]="gymnastics";
//		words[1]="rowing";//
//		words[2]="skiing";//
//		words[3]="swimming";
//		words[4]="skating";//
//		words[5]="fishing";
//	    words[6]="riding";//
//		words[7]="cycling";//
//		words[8]="running";
//		words[9]="jumping";
		  
		  
		  words[0]="orange";
		  words[1]="apple";
		  words[2]="watermelon";
		  words[3]="grape";
		  words[4]="peach";
		  words[5]="pear";
		  words[6]="fruit";
		  words[7]="book";
		  words[8]="neck";
		  words[9]="mouth";
		getHypernymTree a=new getHypernymTree();
		try {
		a.getHypernym(words);
		} catch (IndexOutOfBoundsException e) {
			System.out.print("----end");
			e.printStackTrace();
		}
	}
}

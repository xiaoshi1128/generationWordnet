package generationWordNet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
import generationWordNet.Similarity;
public class getHypernym {
	
		
	public String getHypernymsss(String codename) throws IOException {
		String wnhome = System.getenv("WNHOME");  
		String path = wnhome + File.separator + "dict";  
		URL url = new URL("file", null, path);
		//建立词典对象并打开它。  
		IDictionary dict = new Dictionary(url);  
		dict.open(); 

		String nextHypernym=new String();
		String truenext=new String();
		String truenext2=new String();
//		for(String s:codename) {
			nextHypernym=codename;
			
			//提取s中的数字
			String a=codename;
			List<String> digitList = new ArrayList<String>();
			Pattern p = Pattern.compile("[^0-9]");
		    Matcher m = p.matcher(a);
			String result = m.replaceAll("");
			for (int i = 0; i < result.length(); i++) {
			digitList.add(result.substring(i, i+1));
			}
			int q=0;
			for(String h:digitList) {
				q=Integer.valueOf(h).intValue();
			}
//			System.out.println("提取数字："+digitList);

			//把#之前的字符串取出来
			String str = nextHypernym;
			String[]  strs=str.split("#");
//			for(int i=0,len=strs.length;i<len;i++){
//			    System.out.println("strs:"+strs[i].toString());
//			}
	
			truenext=(String)strs[0];
//			System.out.println("truenext:"+truenext);
	  // Look up first sense of the word
			IIndexWord idxWord = dict.getIndexWord(truenext, POS.NOUN);			
			IWordID wordID = (IWordID) idxWord.getWordIDs().get(q-1);
			IWord word = dict.getWord(wordID);
			ISynset synset = word.getSynset();
			// Get the Hypernyms
			List <ISynsetID> hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);
			// Print out each hypernym
			List <IWord> words;
			for(ISynsetID sid : hypernyms) {
				words = dict.getSynset(sid).getWords();
				for(Iterator <IWord> i = words.iterator(); i.hasNext();) {
					truenext = i.next().getLemma();
				}
				truenext2=truenext;
			}
		
		dict.close();
	    return truenext2;
	}
	
}

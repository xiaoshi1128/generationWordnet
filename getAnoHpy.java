package generationWordNet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
public class getAnoHpy {
	
	public ArrayList<String> getAnoHpym(String codename) throws IOException {
		String wnhome = System.getenv("WNHOME");  
		String path = wnhome + File.separator + "dict";  
		URL url = new URL("file", null, path);  
		IDictionary dict = new Dictionary(url);
		dict.open();
		
		String nextHypernym = codename;
	
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

			//把#之前的字符串取出来
			String str = nextHypernym;
			String[]  strs=str.split("#");
			truenext=(String)strs[0];
//			System.out.println("truenext:"+truenext);
	  // Look up first sense of the word
			IIndexWord idxWord0 = dict.getIndexWord(truenext, POS.NOUN);	
			
			int SIDint=0;
			IWordID wordID0 = (IWordID) idxWord0.getWordIDs().get(q);
			IWord word0 = dict.getWord(wordID0);
			ISynset synset0 = word0.getSynset();
			// Get the Hypernyms
			List <ISynsetID> hypernyms0 = synset0.getRelatedSynsets(Pointer.HYPERNYM);
			// Print out each hypernym
			List <IWord> words0;
			ISynsetID sid0 = hypernyms0.get(0);
			SIDint=getSID(sid0);//////把SID传上去
			words0 = dict.getSynset(sid0).getWords();
			for(Iterator <IWord> i = words0.iterator(); i.hasNext();) {
				truenext = i.next().getLemma();
			}
				truenext2=truenext;
				ArrayList<String> truenext3=new ArrayList<String>();
				do {	
//				System.out.println("next0:"+truenext2);
				IIndexWord idxWord = dict.getIndexWord(truenext2, POS.NOUN);
				int index=0;
				int[] wordIDint= {0,0,0,0,0,0,0,0};///////把wordId传上去
				ArrayList<String> wordIDString=new ArrayList<String>();
				wordIDString=getWordID(idxWord);
				
		        //把wordintID转换为整数
				for(int ii=0;ii<wordIDint.length;++ii) {
					wordIDint[ii]=Integer.valueOf(wordIDString.get(ii)).intValue();
					if(wordIDint[ii]==SIDint) {
					   index=ii;
					   break;
					} 
				}
//				    System.out.println("index::"+index);
					IWordID wordID = (IWordID) idxWord.getWordIDs().get(index);
					IWord word = dict.getWord(wordID);
					ISynset synset = word.getSynset();
					// Get the Hypernyms
					List <ISynsetID> hypernyms = synset.getRelatedSynsets(Pointer.HYPERNYM);
					List <IWord> words;
					ISynsetID sid = hypernyms.get(0);/////entity的时候这个地方不能执行
					
					
					SIDint=getSID(sid);//////把SID传上去
//					System.out.println("SIDint2:"+SIDint);
					words = dict.getSynset(sid).getWords();
					for(Iterator <IWord> i = words.iterator(); i.hasNext();) {
						truenext2 = i.next().getLemma();
					}
						truenext3.add(truenext2);
//						System.out.println("truenext33:::"+truenext3);
//						System.out.println("truenext2:down:"+truenext2);
//						System.out.println("");
//					break;
				} while ((Objects.equals(truenext2, "entity")==false ));
//				System.out.println("truenext2:lowest:"+truenext2);
				dict.close();
				return truenext3;	
				}
	
	public ArrayList<String> getWordID(IIndexWord inde) {
		IIndexWord idxWord2=inde;
		String b=idxWord2.toString();
		String sourceStr1 = b;
  	  	String[] sourceStrArray1 = sourceStr1.split(",");
  	 	ArrayList<String> wordList1=new ArrayList<String>();
  	 	ArrayList<String> wordList2=new ArrayList<String>();
  	 	for (int k = 0; k < sourceStrArray1.length; k++) {
  		wordList1.add(sourceStrArray1[k]);
  	}
  	  for(String h:wordList1) {
		List<String> digitList2 = new ArrayList<String>();
		Pattern p2 = Pattern.compile("[^0-9]");
	    Matcher m2 = p2.matcher(h);
		String result2 = m2.replaceAll("");
		for (int i = 0; i < result2.length(); i++) {
		digitList2.add(result2.substring(i, i+1));
		}
		
		String p0=digitList2.get(0);
		String p1=digitList2.get(1);
		String pp2=digitList2.get(2);
		String p3=digitList2.get(3);
		String p4=digitList2.get(4);
		String p5=digitList2.get(5);
		String p6=digitList2.get(6);
		String p7=digitList2.get(7);
		String p8=p0+p1+pp2+p3+p4+p5+p6+p7;
		wordList2.add(p8);
  	  }
		return wordList2;
		}

public int getSID(ISynsetID sinde){
	ISynsetID sid2=sinde;//外部的sid
	String a=sid2.toString();
	List<String> digitList = new ArrayList<String>();
	Pattern p = Pattern.compile("[^0-9]");
    Matcher m = p.matcher(a);
	String result = m.replaceAll("");
	for (int i = 0; i < result.length(); i++) {
	digitList.add(result.substring(i, i+1));
	}
	int q=0;
	String q0=digitList.get(0);
	String q1=digitList.get(1);
	String q2=digitList.get(2);
	String q3=digitList.get(3);
	String q4=digitList.get(4);
	String q5=digitList.get(5);
	String q6=digitList.get(6);
	String q7=digitList.get(7);
	String q8=q0+q1+q2+q3+q4+q5+q6+q7;
	q=Integer.valueOf(q8).intValue();
	return q;
	
}

}

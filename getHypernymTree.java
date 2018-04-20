package generationWordNet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import generationWordNet.Similarity;
import generationWordNet.getHypernym;
import generationWordNet.getAnoHpy;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.Pointer;
public class getHypernymTree {
	public ArrayList<ArrayList<String>> getHypernym(String[] code) throws IOException{
//	public ArrayList<String> getHypernym(String[] code) throws IOException{
		String wnhome = System.getenv("WNHOME");  
		String path = wnhome + File.separator + "dict";  
		URL url = new URL("file", null, path);
		//建立词典对象并打开它。  
		IDictionary dict = new Dictionary(url);  
		dict.open(); 	
	ArrayList<String> wordList=new ArrayList<String>();//接受有相似度的词
	String nextHyper=new String();//上位词
	ArrayList<String> nextHyper2=new ArrayList<String>();//上位词
	
	Similarity sm=new Similarity();	
	getAnoHpy nextHySec=new getAnoHpy();//得到上位词的上位词	
	getHypernym gy=new getHypernym();
	wordList=sm.getSimilarity(code);//计算wordlist中词的相似度	
	ArrayList<String> generaIn=new ArrayList<String>();
	ArrayList<String> generaInRe=new ArrayList<String>();
	ArrayList<ArrayList<String>> genera=new ArrayList<ArrayList<String>>();
//	String[] genera=new String[]();
	
	for(int kk=0;kk<wordList.size();kk++) {
	generaIn.add(wordList.get(kk));//把这个词本身加入到list中
	nextHyper=gy.getHypernymsss(wordList.get(kk));//得到每个词的第一个上位词
	generaIn.add(nextHyper);//把得到的第一个上位词加入LIST中
//	System.out.println("第一步元素串："+generaIn);
	nextHyper2=nextHySec.getAnoHpym(nextHyper);
	for (String f:nextHyper2) {
	generaIn.add(f);
	}
	ArrayList<String> tmp = new ArrayList<String>();
	for(int sd=generaIn.size()-1;sd>=0;sd--) {
		generaInRe.add(generaIn.get(sd));
	}
//	System.out.println("genera1:："+generaInRe);
	tmp.addAll(generaInRe);
	genera.add(tmp);
	generaIn.clear();
	generaInRe.clear();
	
//	System.out.println("");
	}
	System.out.println("外部串："+genera);
	return genera;
  }
}

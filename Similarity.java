package generationWordNet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.sussex.nlp.jws.JWS;
import edu.sussex.nlp.jws.JiangAndConrath;

public class Similarity {
	public static int wSize=10;//һ���ж��ٴ�
	public static String dir = "C:/Program Files/WordNet/";
	public static JWS ws = new JWS(dir, "2.1");
	
		//����Ҫ�����ĵײ��
//		String[] words=new String[wSize];
//		words[wSize]= {"gymnastics","rowing","skiing","swimming","skating","fishing","riding",,"cycling","running","jumping"};

	
	
	public ArrayList<String> getSimilarity(String[] words) throws IOException
	{
		String wnhome = System.getenv("WNHOME");  
		String path = wnhome + File.separator + "dict";  
		URL url = new URL("file", null, path);
		//�����ʵ���󲢴�����  
		IDictionary dict = new Dictionary(url);  
		dict.open(); 
		ArrayList<String> wordList1=new ArrayList<String>();
//	for(String s:words)
//		System.out.println("words:"+s);		 
		
		//��ʼ�������ƶ�
		JiangAndConrath jcn = ws.getJiangAndConrath();
		System.out.println("Jiang & Conrath\n");
        // all senses ���е������������ƶ�
		for(int i=0;i<wSize;i++) {
			for(int j=0;j<wSize;j++) {
		TreeMap<String, Double> scores1	=jcn.jcn(words[i],words[j], "n");	// all senses
		for(String s : scores1.keySet())
		{
		    if(scores1.get(s)>0 && scores1.get(s)<3 ) {//ȡ����0С��3����Ϊ������3�Ļ��������Լ����Լ������ƶȣ�
//		    	System.out.println(s + "\t" + scores1.get(s));
		    	String sourceStr1 = s;
		    	String[] sourceStrArray1 = sourceStr1.split(",");
		    	for (int k = 0; k < sourceStrArray1.length; k++) {
		      	boolean isIn=wordList1.contains(sourceStrArray1[k]);
		    	if(isIn==false) {
		    		wordList1.add(sourceStrArray1[k]);
		    	}
		      }
		    }
		  }
		}
	}
		System.out.println("wordlist::"+wordList1);
		return wordList1;
  }
}

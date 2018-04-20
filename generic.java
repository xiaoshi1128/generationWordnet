package generationWordNet;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.*;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import generationWordNet.Similarity;
import generationWordNet.getHypernym;
import generationWordNet.getAnoHpy;
import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import generationWordNet.getHypernymTree;
public class generic{
	public TreeNode generate(ArrayList<ArrayList<String>> Dictionary) {
		HashSet<String> set=new HashSet<String>();
		ArrayList<TreeNode> list=new ArrayList<TreeNode>();
		//HashMap<String,ArrayList<String>> map=new HashMap<String,ArrayList<String>>();
		TreeNode root=new TreeNode(Dictionary.get(0).get(0));
		list.add(root);
		int n=0;
		while (n<Dictionary.size()) {
			TreeNode father=root;
			for(int i=1;i<Dictionary.get(n).size();i++) {
				TreeNode child=new TreeNode(Dictionary.get(n).get(i));
				if (!father.have(child)) {
					father.addnode(child);
					father=child;
				}
				else {
					int position=father.find(child);
					father=father.child.get(position);
				}
				
			}
			n++;
		}
		return root;
	}
	public Element DFS(Element e,TreeNode n)  
    {  
        if (n.value!="")   
        {  
            Element se=e.addElement(n.value);  
            for (int i=0;i<n.child.size();i++) {
            	se=DFS(se,n.child.get(i));
            }
        } 
        return e;
    }  
	public void generateXML(TreeNode tree) throws Exception {
        Document doc = DocumentHelper.createDocument();
        //���Ӹ��ڵ�
        Element GeneralizationTree = doc.addElement("GeneralizationTree");
        GeneralizationTree=DFS(GeneralizationTree,tree);
        //ʵ���������ʽ����
        OutputFormat format = OutputFormat.createPrettyPrint();
        //�����������
        format.setEncoding("UTF-8");
        //������Ҫд���File����
        File file = new File("D:\\workspaceEE\\generationWordNet" + File.separator + "test.xml");
        //����XMLWriter���󣬹��캯���еĲ���Ϊ��Ҫ������ļ����͸�ʽ
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        //��ʼд�룬write�����а������洴����Document����
        writer.write(doc);
        
    }
	public static void main(String[] args) throws Exception {
		String wnhome = System.getenv("WNHOME");  
		String path = wnhome + File.separator + "dict";  
		URL url = new URL("file", null, path);
		//�����ʵ���󲢴�����  
		IDictionary dict = new Dictionary(url);  
		dict.open(); 
		
		String[] words=new String[10];
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
		 ArrayList<ArrayList<String>> al=new ArrayList<ArrayList<String>>();
		
		getHypernymTree a=new getHypernymTree();
		al=a.getHypernym(words);
		generic gt=new generic();
		TreeNode tree=gt.generate(al);
		gt.generateXML(tree);
	}
}
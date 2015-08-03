import java.io.*;
import java.util.regex.*;
import java.util.*;

public class langDetect {

    public static void main(String[] args) {
        
        String codeText = new Scanner(System.in).useDelimiter("\\Z").next();
	//System.out.println("!!!!!!!!!\n\n\n" + codeText);
	
	codeText = removeCommentsAndStrings(codeText);
     
	
	Pattern pythonSignature = Pattern.compile( "(def .+?(.+?):)");
	Pattern javaClass = Pattern.compile( "(class .+?\\{)" );

	Matcher pythonMatcher = pythonSignature.matcher(codeText);
	Matcher javaMatcher = javaClass.matcher(codeText);
	
	if(pythonMatcher.find()) System.out.println("python");
	else if(javaMatcher.find()) System.out.println("java");
	else System.out.println("C");
	
        
    }

	public static String removeCommentsAndStrings(String s)
	{
		s = s.replaceAll("(\\/\\*(\n|.)*?\\*\\/)","");
		return s.replaceAll("\".+\"","");
		//return s.replace("/*\n","aaaa");
	}
}

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;


public class HW1 {
  @SuppressWarnings("serial")

  public static int calculateFreq(char c, String text)
  {
    int total = 0;
    for (int i=0;i<text.length();i++)
    {
      if (text.charAt(i) == c)
        total++;
    }
    return total;
  }

  /*
  public static HashMap<Character, Integer> sortByValue(HashMap<Character, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<Character, Integer> > list = 
               new LinkedList<Map.Entry<Character, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() { 
            public int compare(Map.Entry<Character, Integer> o1,  
                               Map.Entry<Character, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>(); 
        for (Map.Entry<Character, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
    */
  
  /* Problem 1 */
    
  static void Problem1() {
    String cipherText = "ROYQWH KQXXJYQ: N LQGNQAQ HDJH FO. VW NX J KQKLQO VZ J XQMOQH MONKQ VOYJWNSJHNVW MJGGQF U.D.J.W.H.V.K., IDVXQ YVJG NX HVHJG IVOGF FVKNWJHNVW. HDQNO UGJW NX HV JMBRNOQ J XRUQOIQJUVW JWF HV DVGF HDQ IVOGF OJWXVK. N ZQJO HDJH IQ FV WVH DJAQ KRMD HNKQ LQZVOQ HDQT XRMMQQF.\nN DJAQ OQMQWHGT NWHQOMQUHQF JW QWMOTUHQF KQXXJYQ (JHHJMDKQWH MNUDQO2.HCH) HDJH IJX XQWH LT FO. VW HV VWQ VZ DNX MVWXUNOJHVOX, HDQ NWZJKVRX KO. LGVIZNQGF. N KJWJYQF HV FNXMVAQO HDJH HDQ KQXXJYQ IJX QWMOTUHQF RXNWY HDQ PJMEJG MNUDQO (XQQ XVROMQ MVFQ), LRH N IJX WVH JLGQ FNXMVAQO HDQ XQMOQH EQT, JWF HDQ MNUDQO XQQKX HV LQ RWLOQJEJLGQ. N JK JZOJNF HDJH FQMOTUHNWY HDNX KQXXJYQ NX HDQ VWGT IJT HV XHVU FO. VW'X VOYJWNSJHNVW.\nUGQJXQ XQWF OQNWZVOMQKQWHX NKKQFNJHQGT! N HONQF HV JMH MJRHNVRXGT, LRH N DJAQ J ZQQGNWY HDJH FO. VW'X DQWMDKQW JOQ VWHV KQ. N FVW'H EWVI DVI GVWY N DJAQ LQZVOQ HDQT FNXMVAQO KT OQJG NFQWHNHT JWF KT XQMOQH DNFNWY UGJ";
    HashMap<Character, Integer> frequencies = new HashMap<Character, Integer>() {
      {
        for (char c = 'A';c <= 'Z'; c++)
          put(c, calculateFreq(c, cipherText));
      }
    };
    for (char c = 'A'; c <= 'Z'; c++)
      System.out.printf("%c: %d\n", c, frequencies.get(c));


    /*
    HashMap<Character, Integer> newhash = sortByValue(frequencies);

    Iterator<Character> itr =  newhash.keySet().iterator();
    while (itr.hasNext()) {
			System.out.println(itr.next());
		}
    */

    HashMap<Character, Character> key = new HashMap<Character, Character>() {
      {
        put('Q', 'E');
        put('H', 'T');
        put('J', 'A');
        put('V', 'O');
        put('N', 'I');
        put('W', 'N');
        put('X', 'S');
        put('O', 'R');
        put('D', 'H');
        put('F', 'D');
        put('M', 'C');
        put('K', 'M');
        put('G', 'L');
        put('U', 'P');
        put('T', 'Y');
        put('Y', 'G');
        put('R', 'U');
        put('L', 'B');
        put('I', 'W');
        put('Z', 'F');
        put('A', 'V');
        put('E', 'K');
        put('S', 'Z');
        put('B', 'Q');
        put('P', 'J');
        put('C', 'X');
      }
    };
    for (char c : cipherText.toCharArray()) {
      if (key.containsKey(c))
        System.out.print(key.get(c));
      else
        System.out.print(c);
    }
    System.out.println();
    
  }
  
  

  
  public static byte[] JACKAL_Decrypt(byte firstKeyByte, byte secondKeyByte, byte[] cipherText) 
  {
    byte x=(byte)(firstKeyByte+31);
    byte y=(byte)(secondKeyByte*=3);
    byte[]p=new byte[cipherText.length];
    for(int z=0;z<p.length;z++)
    {
      x+=29;
      y*=19;
      p[z]=(byte)(cipherText[z]^x^y);
    }
      return(p);
    }

  public static boolean isEnglishText(byte[] bytes) {
    String punctuations = ".,'-:{}";
    for (char chr : new String(bytes).toCharArray())
      if (!(Character.isLetterOrDigit(chr) || Character.isWhitespace(chr) || punctuations.contains(String.valueOf(chr))))
        return false;
    return true;
  } 

  static void Problem2() throws IOException {
    byte[] cipherText = Files.readAllBytes(Paths.get("cipher2.txt"));

    byte[] plainText = JACKAL_Decrypt((byte)0, (byte)0, cipherText);
    for (int i=0;i<97;i++)
    {
      for (int j=0;j<97;j++)
      {
        if (isEnglishText(JACKAL_Decrypt((byte)i, (byte)j, cipherText)))
          plainText = JACKAL_Decrypt((byte)i, (byte)j, cipherText);
      }
    }

    System.out.println(new String(plainText));
  }
  
  

    
  static void Problem3() throws IOException {
    byte[] cipherText = Files.readAllBytes(Paths.get("cipher3.txt"));
    // BEGIN SOLUTION
    byte[] key = {2,3,5,7,11,13,17,19,23,29,31}; 
    byte[] plainText = new byte[cipherText.length];
    int keycount = 0;
    for (int i=0;i<cipherText.length;i++)
    {
      plainText[i] = (byte)(cipherText[i] ^ key[keycount]);
      keycount++;
      if (keycount == 11)
        keycount = 0;
    }

    System.out.println(new String(plainText));
  }
  
  public static void main(String [] args) throws IOException {
    Problem1();
    Problem2();
    Problem3();
  }  
}


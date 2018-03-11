import java.util.*;
import java.lang.*;
import java.io.*;

public class WordLadder {
    //words is used to store all words in the input dictionary file.
    //track is used to store all words that has been in the stack of ladder before.
    public static Set<String> words = new HashSet<String>();
    public static Set<String> track = new HashSet<String>();

    public static Stack<String> findLadder(String word1,String word2) {
        //wordQueue is to store all the stack of partial ladder.
        //wordStack is to store partial ladder.
        Queue<Stack<String>> wordQueue = new LinkedList<Stack<String>>();
        track.add(word1);
        Stack<String> wordStack = new Stack<String>();
        wordStack.push(word1);
        wordQueue.add(wordStack);
        //wordS is the top stack of the wordQueue.
        Stack<String> wordS = new Stack<String>();
        while(!wordQueue.isEmpty()) {
            wordS = wordQueue.poll();
            //word is the top word of the wordS.
            String word = wordS.peek();
            for(int i = 0;i<word.length();i++) {
                char original_ch = word.charAt(i);
                for(char ch = 'a';ch<='z';ch++) {
                    if (ch != original_ch) {
                        char[] char_array = word.toCharArray();
                        char_array[i] = ch;
                        String changable_word =new String(char_array);
                        if (changable_word.contentEquals(word2)) {
                            wordS.push(changable_word);
                            return wordS;
                        }
                        if (words.contains(changable_word) && (!track.contains(changable_word))) {
                            track.add(changable_word);
                            Stack<String> word_stack = new Stack<String>();
                            word_stack.addAll(wordS);
                            word_stack.push(changable_word);
                            wordQueue.add(word_stack);
                        }
                    }
                }

            }
        }
        Stack<String> empty=new Stack<String>();
        return empty;
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String fileName = new String();
        File inFile;
        while(true) {
            System.out.print("Dictionary file name? ");
            if (scan.hasNextLine()) {
                fileName = scan.nextLine();
            }
            inFile = new File(fileName);
            if (inFile.exists()) break;
            System.out.println("Unable to open that file. Try Again.");
        }

        BufferedReader br = new BufferedReader(new FileReader(inFile));
        String word = new String();
        while((word = br.readLine()) != null)
            words.add(word);
        br.close();

        String word1 = new String();
        String word2 = new String();
        while(true) {
            System.out.print("\nWord #1 (or Enter to quit):");
            word1 = scan.nextLine();
            if(word1.length() == 0)  {
                System.out.print("Have a nice day.\n");
                return;
            }
            System.out.print("Word #2 (or Enter to quit):");
            word2 = scan.nextLine();
            if(word2.length() == 0)  {
                System.out.print("Have a nice day.\n");
                return;
            }
            word1 = word1.toLowerCase();
            word2 = word2.toLowerCase();
            if(words.contains(word1) && words.contains(word2)) {
                if(word1.length() == word2.length()) {
                    if(!word1.contentEquals(word2)) {
                        Stack<String> wordLadder = findLadder(word1,word2);
                        if(wordLadder.isEmpty()) {
                            System.out.println("No word ladder found from "+word2+" back to "+word1+".\n");
                        }
                        else {
                            System.out.println("A ladder from "+word2+" back to "+word1+":");
                            while(!wordLadder.isEmpty()) {
                                String ladder = wordLadder.pop();
                                System.out.print(ladder+" ");
                            }
                            System.out.print("\n");
                            track.clear();
                        }
                    }
                    else System.out.print("The two words must be different.\n");
                }
                else System.out.print("The two words must be the same length.\n");
            }
        else System.out.print("The two words must be found in the dictionary.\n");


        }
    }
}

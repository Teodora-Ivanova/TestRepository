package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//hghghg
public class File {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your text");
		String text = input.nextLine();
		writeDownTextInFile(text);
		System.out.println(readArray());
		System.out.println(separatePreviousList(readArray()));
		System.out.println(findMostCommonWord(separatePreviousList(readArray())));
		System.out.println(codeMostCommonWord(findMostCommonWord(separatePreviousList(readArray()))));
		ArrayList<String> finalList = replaceMostCommonWord(separatePreviousList(readArray()),
				findMostCommonWord(separatePreviousList(readArray())),
				codeMostCommonWord(findMostCommonWord(separatePreviousList(readArray()))));
		System.out.println(finalList);
	}

	public static void writeDownTextInFile(String text) {
		FileWriter writeDownInAFile;
		try {
			writeDownInAFile = new FileWriter("Text.txt", true);
			writeDownInAFile.write(text);
			writeDownInAFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// adds the info from the file in the list:
	public static ArrayList<String> readArray() throws FileNotFoundException {
		BufferedReader line = new BufferedReader(new FileReader("Text.txt"));
		ArrayList<String> textList = new ArrayList<String>();
		try {
			while (line.readLine() != null) {
				textList.add(line.readLine());
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return textList;
	}

	// separated text list:
	public static ArrayList<String> separatePreviousList(ArrayList<String> textList) {
		ArrayList<String> listr = new ArrayList<>();
		String text = null;
		for (int i = 0; i < textList.size(); i++) {
			text = textList.get(i);
			for (String word : text.split(" ")) {
				listr.add(word);
			}
		}
		return listr;
	}

	public static String findMostCommonWord(ArrayList<String> separatedList) {
		int count = 0;
		int maxCount = 0;
		String mostCommonWord = null;
		for (int i = 0; i < separatedList.size(); i++) {
			for (int j = i + 1; j < separatedList.size(); j++) {
				if (separatedList.get(i).equals(separatedList.get(j))) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
				mostCommonWord = separatedList.get(i);
			}
		}
		return mostCommonWord;
	}

	public static String codeMostCommonWord(String mostCommonWord) {
		String codedMostCommonWord = "";
		for (int i = 0; i < mostCommonWord.length(); i++) {
			codedMostCommonWord = codedMostCommonWord.concat("*");
		}
		return codedMostCommonWord;
	}

	public static ArrayList<String> replaceMostCommonWord(ArrayList<String> separatedTextList, String mostCommonWord,
			String codedMostCommonWord) {

		for (int i = 0; i < separatedTextList.size(); i++) {
			if (separatedTextList.get(i).equals(mostCommonWord)) {
				separatedTextList.set(i, codedMostCommonWord);
			}
		}
		return separatedTextList;
	}
}

/*
* Author: Tao Jiang, Weihao Cheng, Yucheng Xie
* Description: This file contain a child class of abstract code class, we split our project into 3 different parts
* 		and will merge them together later.
*/
import java.io.*;
import java.util.*;
/*
* This class has the method to compile the code file, set the grade and comments, 
* and return two files(grade and comments) by the update method.
*/
public class JavaCode {
	private String path;
	private double score;
	private String comments;
	
	//constructor, has not finished yet
	JavaCode() {
		super();
	}
	//get the path of this code file (Absolute path??)
	public String getPath() {
		return this.path;
	}
	
	public double getScore() {
		return this.score;
	}
	public void setScore(double scr) {
		this.score = scr;
	}
	
	// compile the code using cmd.exe
	public void compile() {
		try {
		    Process pro = Runtime.getRuntime().exec("javac Main.java");
		    String line = null;
		    BufferedReader in = new BufferedReader(
			    new InputStreamReader(pro.getErrorStream()));
		    while ((line = in.readLine()) != null) {
			System.out.println("javac Main.java" + " The error : " + " " + line);
		    }
		    pro.waitFor();
		    System.out.println("javac Main.java" + " exitValue() " + pro.exitValue());


		    pro = Runtime.getRuntime().exec("java Main");
		    line = null;
		    in = new BufferedReader(
			    new InputStreamReader(pro.getInputStream()));
		    while ((line = in.readLine()) != null) {
			System.out.println("java Main" + " The output of this program : " + " " + line);
		    }
		    line = null;
		    in = new BufferedReader(
			    new InputStreamReader(pro.getErrorStream()));
		    while ((line = in.readLine()) != null) {
			System.out.println("java Main" + " The error : " + " " + line);
		    }
		    pro.waitFor();
		    System.out.println("java Main" + " exitValue() " + pro.exitValue());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	//display the code (GUI group can use it to display it into a textarea)
	public void display() {    
		BufferedReader in;
		try {
		    in = new BufferedReader(new FileReader(this.getPath()));
		    String strLine;
		    while ((strLine = in.readLine()) != null) {
			System.out.println(strLine);
		    }
		    in.close();
		} catch (Exception e) {
		    System.err.println("Error: " + e.getMessage());
		}
	}
	// allow the grader to comment the code and write the comment into a file. 
	public void setComments() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the comments: ");
		this.comments = input.nextLine();
	}
	
	// allow the grader to comment the code and write the comment into a file.
	public void grade() {
		String repeatChar = "";
		do{
			Scanner input = new Scanner(System.in);
			System.out.print("Please enter the grade: ");
			this.score = input.nextDouble();
			System.out.println("The grade you set is " + score + " (enter t/T for Ture or f/F for False).");
			repeatChar = input.next();
		} while (repeatChar.charAt(0) == 'F' || repeatChar.charAt(0) == 'f'); 
	}
	
	// create two files for comments and grade respectively.
	public void update() throws IOException { 
		FileWriter comt = new FileWriter("comment.txt");
		BufferedWriter comWriter = new BufferedWriter(comt);
		comWriter.write(this.comments);
		comWriter.flush();
		comWriter.close();
		FileWriter grad = new FileWriter("grade.txt");
		BufferedWriter gradWriter = new BufferedWriter(grad);
		gradWriter.write(this.score + "");
		gradWriter.flush();
		gradWriter.close();
	}
}

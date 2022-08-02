package com.example.runnables;

import com.example.structures.StringStackImpl;
import java.io.*;

public class TagMatching {

	public static void main(String[] args) {

		String path= null;
		StringStackImpl Stack = new StringStackImpl();
		File f;
		FileReader fr;
		BufferedReader br = null;
		String line;
		boolean foundError = false;

		if (0 < args.length) {
			   path = args[0];
			} else {
			   System.err.println("Invalid arguments count:" + args.length);
			   System.exit(1);
			}

		try {
			f = new File(path);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			line = br.readLine();
				
			int openTag;
			int closeTag;
			int lineCounter = 0;
			
			while(line != null) {
				lineCounter+=1;
				openTag = line.indexOf("<");

				while(openTag >= 0)
				{
					closeTag = line.indexOf(">", openTag + 1);
					//if: checks whether there is an open tag and shows related message (e.g. <head)
					if(closeTag < 0) {
						System.out.print("File has errors! \nTag on line "+lineCounter+" is open.\n\n");
						break;
					}
					//adds in the stack the opening tag (e.g. from the tag <head> -> "head" will be added to the stack)
					if( !( line.substring(openTag + 1).startsWith("/") ) ){
						Stack.push(line.substring(openTag + 1, closeTag));
					}
					// closing tags (e.g. </head>) are not added to the stack.
					// We compare them inside the else below with the last tag added inside the stack to see if the tag closes
					// If comparison is good and they match, we pop it out of the stack to check the next one
					else
					{
						int index3 = line.indexOf(">", openTag + 1);
						String s1 = line.substring(openTag + 2,index3);
						//Compare here
						if(Stack.size() > 0) {
							if (s1.equals(Stack.peek())) {
								Stack.pop();
							} else {
								System.out.println("Tags are not Matched. Program not Correct! Line: " + lineCounter);
								foundError = true;
								break;
							}
						}else{
							System.out.println("Tags are not Matched. Program not Correct! Line: " + lineCounter);
							foundError = true;
							break;
						}
					}
					//Check whether there is another tag in current line
					//if there is 	-> openTag is >= 0 so While clause goes again
					//if not 		-> openTag is = -1 so we leave this While clause
					openTag = line.indexOf("<", closeTag + 1);
				}
				//If a single tag is not matched program stops, we have an error we do not check for more
				if(foundError){
					break;
				}
				//change line
				line = br.readLine();
			}
			if(Stack.isEmpty() && !foundError){
				System.out.println("All tags are matched. Program is correct!");
			}
		}
		catch (IOException e) {
			System.err.println("Error opening file!");
		}

		try {
			br.close();
		}
		catch(IOException e){
			System.err.println("Error closing file.");
		}
	}
}

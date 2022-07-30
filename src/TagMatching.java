import java.io.*;

public class TagMatching {

	public static void main(String[] args) {

		String path= null;
		StringStackImpl Stack = new StringStackImpl();
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		boolean flag = false;

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
				
			int index = -1;
			int index2 = -1;
			int c = 0;
			
			while(line != null) {
				c+=1;
				index = line.indexOf("<");

				while(index >= 0)
				{
					index2 = line.indexOf(">", index + 1);
					//if: checks whether there is an open tag and shows related message (e.g. <head)
					if(index2 < 0) {
						System.out.print("File has errors! \nTag on line "+c+" is open.\n\n");
						break;
					}
					//adds in the stack the opening tag (e.g. from the tag <head> -> "head" will be added to the stack)
					if( !( line.substring(index + 1).startsWith("/") ) ){
						Stack.push(line.substring(index + 1, index2));
					}
					// closing tags (e.g. </head>) are not added to the stack.
					// We compare them inside the else below with the last tag added inside the stack to see if the tag closes
					// If comparison is good and they match, we pop it out of the stack to check the next one
					else
					{
						int index3 = line.indexOf(">", index + 1);
						String s1 = line.substring(index + 2,index3);
						//Compare here
						if(Stack.size() > 0) {
							if (s1.equals(Stack.peek())) {
								Stack.pop();
							} else {
								System.out.println("Tags are not Matched. Program not Correct! Line: " + c);
								flag = true;
								break;
							}
						}else{
							System.out.println("Tags are not Matched. Program not Correct! Line: " + c);
							flag = true;
							break;
						}
					}
					//Check whether there is another tag in current line
					//if there is 	-> index is >= 0 so While clause goes again
					//if not 		-> index is = -1 so we leave this While clause
					index = line.indexOf("<", index2 + 1);
				}
				//If a single tag is not matched program stops, we have an error we do not check for more
				if(flag == true){
					break;
				}
				//change line
				line = br.readLine();
			}
			if(Stack.isEmpty() && flag == false){
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

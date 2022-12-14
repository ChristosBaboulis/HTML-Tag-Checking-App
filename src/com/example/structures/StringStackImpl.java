package com.example.structures;

import com.example.interfaces.StringStack;
import com.example.objects.StackNode;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack{
	StackNode firstNode;
	StackNode lastNode;
	String name;
	
	public StringStackImpl(){
		this("Stack");
	}
	
	public StringStackImpl(String StackName) {
		name = StackName;
		firstNode = lastNode = null;
	}
	
	
	public boolean isEmpty() {
		return firstNode == null;
	}
	
	
	public void push(String item) {
		StackNode node = new StackNode(item);
		if (!isEmpty()) {
			node.setNext(firstNode);
		}
		firstNode = node;
	}
	
	
	public String pop() throws NoSuchElementException {
		if ( isEmpty() )
			throw new NoSuchElementException( name );
		
		String removedItem = firstNode.getString();
		
		if ( firstNode == lastNode ) {
			firstNode = lastNode = null;
		}
		else {
			firstNode = firstNode.getNext();
		}
		return removedItem;
	}
	
	
	public String peek() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException(name);
		}

		return firstNode.getString();
	}
	
	

	public void printStack(PrintStream stream) {
		if(isEmpty()) {
			System.out.printf("The %s is Empty.\n", name);
		}
		else {
		stream.printf( "The %s is: ", name );
		StackNode current = firstNode;
		
		while ( current != null )
		{
			stream.printf( "%s ", current.getString() );
			current = current.getNext();
		}

		stream.println( "\n" );
		}
	}

	
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		
		StackNode current = firstNode;
		int i = 0;
		while ( current != null )
		{
			i++;
			current = current.getNext();
		}
		return i;
	}

}

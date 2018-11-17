package com.search.prototype;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class pextractor {
	static int a = 0;

	public static void listFilesForFolder(final File folder) {
		try {
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		            listFilesForFolder(fileEntry);
		        } else {
		        	FileWriter fw = new FileWriter(new File("C:\\Gitrepo\\aspectj\\"+a+".txt"));
		        	fw.write(fileEntry.getPath()+"\n");
		        	a++;
		        	fw.close();
		            //System.out.println(fileEntry.getPath().substring(42));
		        }
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		final File folder = new File("C:\\Gitrepo\\Dataset\\org.aspectj-1_5_3_final");
		int i = 0;
		listFilesForFolder(folder);
		System.out.println("end");
	}
	
}

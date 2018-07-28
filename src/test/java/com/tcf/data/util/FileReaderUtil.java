package com.tcf.data.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderUtil {
	
	public static String readText(String url){
		String text = "";
		try {
			FileReader reader = new FileReader(url);
			BufferedReader br = new BufferedReader(reader);
			String line = null;
			while((line = br.readLine()) != null){
				text += line;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
	public static void appendText(String url,String text){
		writeText(url, text, true);
	}
	public static void writeText(String url,String text,boolean append){
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(url, append);
			bw = new BufferedWriter(fw);
			bw.write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(bw != null) bw.close();
				if(fw != null) fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

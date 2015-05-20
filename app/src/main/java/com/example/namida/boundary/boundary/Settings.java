package com.example.namida.boundary.boundary;

import com.example.namida.boundary.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Namida on 2015/05/10.
 */
public class Settings {

	public static boolean soundEnabled = true;


	public static void load(FileIO files){
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(files.readFile(".boundary")));
			soundEnabled = Boolean.parseBoolean(in.readLine());

		} catch (IOException e){
		} catch (NumberFormatException e){
		} finally {
			try{
				if(in != null){
					in.close();
				}
			} catch (IOException e){
			}
		}
	}

	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".boundary")));
			out.write(Boolean.toString(soundEnabled));
		} catch (IOException e){
		} finally {
			try{
				if(out != null){
					out.close();
				}
			} catch (IOException e){
			}
		}

	}

}

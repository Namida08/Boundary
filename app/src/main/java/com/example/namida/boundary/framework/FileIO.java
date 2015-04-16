package com.example.namida.boundary.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Namida on 2015/02/15.
 */
public interface FileIO {
	public InputStream readAsset(String fileName) throws IOException;

	public InputStream readFile(String fileName) throws IOException;

	public OutputStream writeFile(String fileName) throws IOException;

}

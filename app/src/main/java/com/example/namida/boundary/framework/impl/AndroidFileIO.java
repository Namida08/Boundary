package com.example.namida.boundary.framework.impl;

import android.content.res.AssetManager;
import android.os.Environment;

import com.example.namida.boundary.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Namida on 2015/05/10.
 */
public class AndroidFileIO implements FileIO {
	private AssetManager assets;
	private String externalStoragePath;

	public AndroidFileIO(AssetManager assets){
		this.assets = assets;
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}

	@Override
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}

	@Override
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(externalStoragePath + fileName);
	}

	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}
}

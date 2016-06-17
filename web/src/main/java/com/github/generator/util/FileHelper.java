package com.github.generator.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileHelper {

	/**
	 * 取得两个文件的相对路径
	 * @param baseDir
	 * @param file
	 * @return
	 */
	public static String getRelativePath(File baseDir, File file) {
		if (baseDir.equals(file)){
			return "";
		}
		if (baseDir.getParentFile() == null){
			return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
		}
		return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
	}
	
	public static List<File> searchAllNotIgnoreFile(File dir) {
		ArrayList arrayList = new ArrayList();
		searchAllNotIgnoreFile(dir, arrayList);
		Collections.sort(arrayList, new Comparator<File>() {
			public int compare(File o1, File o2) {
				return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
			}
		});
		return arrayList;
	}
	
	public static void searchAllNotIgnoreFile(File dir, List<File> collector) {
		collector.add(dir);
		if ((!dir.isHidden() && dir.isDirectory()) && !isIgnoreFile(dir)) {
			File[] subFiles = dir.listFiles();
			for (int i = 0; i < subFiles.length; i++) {
				searchAllNotIgnoreFile(subFiles[i], collector);
			}
		}
	}
	
	public static List ignoreList = new ArrayList();
	static {
		ignoreList.add(".svn");
		ignoreList.add("CVS");
		ignoreList.add(".cvsignore");
		ignoreList.add(".copyarea.db"); // ClearCase
		ignoreList.add("SCCS");
		ignoreList.add("vssver.scc");
		ignoreList.add(".DS_Store");
		ignoreList.add(".git");
		ignoreList.add(".gitignore");
	}

	private static boolean isIgnoreFile(File file) {

		for (int i = 0; i < ignoreList.size(); i++) {
			if (file.getName().equals(ignoreList.get(i))) {
				return true;
			}
		}
		return false;
	}
}

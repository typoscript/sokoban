package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FileManager {
	private static final String PATH 		= "maps/";
	private static final String PREFIX 		= "level_";
	private static final String EXTENSION	= ".txt";

	private FileManager() { }

	private static File getFile(String fileName) {
		return new File(PATH + fileName);
	}

	public static boolean doesMapFileExist(int stageLevel) {
		File file = new File(PATH + getFileName(stageLevel));
		
		return file.exists();
	}

	public static boolean isValidMapFileName(String fileName) {
		if (fileName == null || !fileName.matches("^" + PREFIX + "\\d+" + EXTENSION + "$"))
			return false;
		
		String[] fileNameWithoutExtension = fileName.split(EXTENSION);
		String[] stageLevel = fileNameWithoutExtension[0].split(PREFIX);
		
		try {
			Integer.parseInt(stageLevel[1]);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean createFile(String fileName)  {
		File file = getFile(fileName);
		
		boolean isFileCreated = false;
		
		try {
			FileWriter fw = new FileWriter(file);
			
			fw.write(MapMaker.getMakerMapAsString());
			isFileCreated = true;
			
			fw.close();
		} catch (Exception e) { }
		
		return isFileCreated;
	}
	
	public static boolean deleteFile(String fileName) {
		return getFile(fileName).delete();
	}

	public static boolean renameFile(String fileName, int stageLevel) {
		File file = getFile(fileName);
		File fileWithNewName = new File(PATH + getFileName(stageLevel));
		
		return file.renameTo(fileWithNewName);
	}
	
	public static void saveToFile(String mapData, int stageLevel) {
		try {
			FileWriter fw = new FileWriter(PATH + getFileName(stageLevel));

			fw.write(mapData);
			fw.close();
		} catch (Exception e) {
			System.out.println("맵 파일 저장 에러");
		}
	}
	
	public static int getStageLevelFromFileName(String fileName) {
		String[] fileNameWithoutExtension = fileName.split(EXTENSION);
		String[] stageLevel = fileNameWithoutExtension[0].split(PREFIX);
		
		return Integer.parseInt(stageLevel[1]);
	}

	public static String getFileName(int stageLevel) {
	  return PREFIX + stageLevel + EXTENSION;
	}
	
	public static List<String> getFileNamesInMapFolder() {
		File folder = new File(PATH);
		File[] files = folder.listFiles();
		List<String> fileNames = new ArrayList<String>();
		
		if (!folder.isDirectory()) {
			JOptionPane.showMessageDialog(null, String.format("맵 데이터를 저장하는 \"%s\" 폴더가 없습니다\n", PATH.substring(0, PATH.length() - 1)));
			return fileNames;
		}
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile())
				fileNames.add(files[i].getName());
		}

		return fileNames;
	}

	public static String getMapAsString(int stageLevel) {
		String map = "";

		try {
			FileReader fr = new FileReader(PATH + getFileName(stageLevel));
			BufferedReader br = new BufferedReader(fr);
			
			while (br.ready())
				map += br.readLine() + "\n";
			
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("맵 파일 로드 에러");
		}
		
		return map;
	}
}
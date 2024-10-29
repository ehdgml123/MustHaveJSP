package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	//파일 업로드
	public static String uploadFile(HttpServletRequest request, String sDirectory) 
					throws ServletException, IOException{
		
//		Part part = request.getPart("ofile");
		Part part = request.getPart("attachedFile");
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader : " + partHeader);
		
		String[] phArr = partHeader.split("filename=");
		
		System.out.println("------------------");
		for(String s : phArr)
			System.out.println(s);
		System.out.println("------------------");
		
		String originalFileName = phArr[1].trim().replace("\"", "");
		System.out.println("originalFileName : " + originalFileName);
	
		System.out.println("File.separator : " + File.separator);
		System.out.println("sDirectory : " + sDirectory);
		System.out.println("fileDirect : " + sDirectory + File.separator + originalFileName);
		
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory + File.separator + originalFileName); 
		}		
		return originalFileName;		
	}
	
	//파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
		
		String ext = fileName.substring(fileName.lastIndexOf("."));
		System.out.println("ext : " + ext);
		
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now+ext;
		
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}

	public static List<String> multipleFile(HttpServletRequest request, String saveDirectory) throws IOException, ServletException {
		
		System.out.println("-------------multipleFile----------------------");
		List<String> listfileName = new ArrayList<String>();
		
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts) {
			if(!part.getName().equals("attachedFile"))	continue;
			
			String partHeader = part.getHeader("content-disposition");
		
			String[] phArr = partHeader.split("filename=");
			String originalfileName = phArr[1].trim().replace("\"", "");
		
			if(!originalfileName.isEmpty()) {
				part.write(saveDirectory + File.separator + originalfileName);
			}
			listfileName.add(originalfileName);
		}
		
		return listfileName;
	}
	
	//명시한 파일을 찾아 다운로드 합니다.
	public static void download(HttpServletRequest request, HttpServletResponse response,
			      String directory, String sfileName, String ofileName) {
		
		String sDirectory =  request.getServletContext().getRealPath(directory);
	    System.out.println("download sDirectory" + sDirectory);
		
	    try {
	    	
	    	// 파일을 찾아 입력 스트림 생성
	    	File file =  new File(sDirectory, sfileName);
	    	InputStream iStream = new FileInputStream(file);
	    	
	    	// 파일 다운로드용 응답 헤더 설정 
	    	response.reset();
	    	response.setContentType("application/octet-stream");
	    	response.setHeader("Content-Disposition", "attachment; filename=\"" + ofileName + "\"");
	    	response.setHeader("Content-Length", ""+file.length());
	   
	    	
	    	//response 내장 객체로 부터 새로운 출력 스트림 생성
	    	OutputStream oStream = response.getOutputStream();
	    	
	    	// 출력 스트림에 파일 내용 출력
	    	byte[] b = new byte[(int)file.length()];
	    	int readBuffer = 0;
	    	
	    	while( (readBuffer = iStream.read(b)) > 0) {
	    		oStream.write(b,0, readBuffer);
	    	}
	    	
	    	// 입/출력 스트림 닫음
	    	iStream.close();
	    	oStream.close();
	    	
	    }catch (FileNotFoundException e) {
			System.out.println("해당 파일을 찾을 수 없습니다"); 
	    	e.printStackTrace();
		}catch (Exception e) {
			System.out.println("예외가 발생");
		   e.printStackTrace();
		 }
	    
	}
	
	// 지정한 위치의 파이을 삭제
	public static void deleteFile(HttpServletRequest request, String directory, String fileName) {
		   
		String sDirectory = request.getServletContext().getRealPath(directory);
		System.out.println("sDirectory :"+ sDirectory);
		File file = new File(sDirectory + File.separator + fileName);
		if(file.exists()) {
			file.delete();
		}
	}
}








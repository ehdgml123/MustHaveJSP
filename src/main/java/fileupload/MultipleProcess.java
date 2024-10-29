package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/13FileUpload/MultipleProcess.do")
@MultipartConfig(
	maxFileSize = 1024*1024*1,  //파일당 최대 용량 1M
	maxRequestSize = 1024*1024*10  //전체파일 용량 10M
)
public class MultipleProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------UploadProcess doPost--------------------");
		try {
			String saveDirectory = getServletContext().getRealPath("/Uploads");
			
			//원본 파일명 기록한 리스트배열
			List<String> listFileName = FileUtil.multipleFile(request,saveDirectory );
			
			for(String originalFileName : listFileName) {
				String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
				insertMyFile(request, originalFileName, savedFileName);
			}
			response.sendRedirect("FileList.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "파일 업로드 오류");
			request.getRequestDispatcher("FileUploadMain.jsp").forward(request, response);
		}
	}

	private void insertMyFile(HttpServletRequest request, String oFileName, String sFileName) {
		
		String title = request.getParameter("title");
		String[] cateArray = request.getParameterValues("cate");
		StringBuffer cateBuf  = new StringBuffer();
		
		if(cateArray == null) {
			cateBuf.append("선택한 항목 없음");
		}else {
			for(String s : cateArray) {
				cateBuf.append(s + ", ");
			}
		}
		
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
		System.out.println("dto : " + dto);
		
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();		
		
	}
}

package model2.mvcboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.DBConnPool;
import jakarta.servlet.ServletContext;
import lombok.NoArgsConstructor;
import model1.board.BoardDTO;

public class MVCBoardDAO extends DBConnPool{

	public MVCBoardDAO() {}
	
	//검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount(Map<String, Object> map) {
		 int totalCount = 0;
		 
		 String sql = "select count(*) from mvcboard";
		 
		 //검색 조건
		 /*
		  * 예시
		  * SELECT count(*)
			FROM board 
			WHERE title LIKE '%음악%';
		  */
		 if(map.get("searchWord") != null) {
			 sql += " where " + map.get("searchField") +" like '%" + map.get("searchWord") + "%'";
		 }
		 
		 try {
			 stmt = con.createStatement();
			 rs = stmt.executeQuery(sql);
			 
			 if(rs.next()) {
				 totalCount = rs.getInt(1);
			 }
			 
		 }catch(Exception e) {
			 System.out.println("게시물 수를 구하는 중 예외 발생");
			 e.printStackTrace();
		 }
		 
		 return totalCount;
	 }
	
	//검색 조건에 맞는 게시물 목록을 반환합니다.(페이징 기능 지원)
	 public List<MVCBoardDTO> selectListPage(Map<String, Object> map){
		 List<MVCBoardDTO> bbs = new ArrayList<MVCBoardDTO>();
		 
		 String query = "select * from ("
				 +" select tb.*, rownum as rNum from ("
				 +" select * from mvcboard";
		 
		 //검색조건 추가
		 if(map.get("searchWord") != null) {
			 query += " where " + map.get("searchField")
			 		+ " like '%" + map.get("searchWord") + "%' ";
		 }
		 // 검색 조건 추가
		 query += " order by idx desc) Tb)"
		       + " where rNum between ? and ?"; 
		 
		 try {
			 pstmt = con.prepareStatement(query);
			 
			 //? 맵핑
			 pstmt.setString(1, map.get("start").toString());
			 pstmt.setString(2, map.get("end").toString());
			 
			 //쿼리문 실행
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 MVCBoardDTO dto = new MVCBoardDTO();
				 dto.setIdx(rs.getString("idx"));
				 dto.setName(rs.getString("name"));
				 dto.setTitle(rs.getString("title"));
				 dto.setContent(rs.getString("content"));
				 dto.setPostdate(rs.getDate("postdate"));
				 dto.setOfile(rs.getString("ofile"));
				 dto.setSfile(rs.getString("sfile"));
				 dto.setDowncount(rs.getInt("downcount"));
				 dto.setPass(rs.getString("pass"));
				 dto.setVisitcount(rs.getInt("visitcount"));				
				 bbs.add(dto);
			 }
			 
		 }catch(Exception e) {
			 System.out.println("게시물 조회 중 예외 발생");
				 e.printStackTrace();
		 }		 
		 
		 return bbs;
	 }
	 
	 //게시글 데이터를 받아 DB에 추가합니다.(파일 업로드 지원)
	 public int insertWrite(MVCBoardDTO dto) {
		 int result=0;
		 
		 try {
			 String query = "insert into mvcboard(idx, name, title, content, ofile, sfile, pass)"
					 + " values(seq_board_num.nextval, ?, ?,?, ?,?,?)";
			 
			 pstmt = con.prepareStatement(query);
			 
			 //맵핑..
			 pstmt.setString(1, dto.getName());
			 pstmt.setString(2, dto.getTitle());
			 pstmt.setString(3, dto.getContent());
			 pstmt.setString(4, dto.getOfile());
			 pstmt.setString(5, dto.getSfile());
			 pstmt.setString(6, dto.getPass());
			 
			 //실행
			 result = pstmt.executeUpdate();
		 }catch(Exception e) {
			 System.out.println("게시물 입력 중 예외 발생");
			 e.printStackTrace();
		 }		 
		 return result;
	 }
	 
	 // 주어진 일련번호에 해당하는 게시물을 DTO 에 담아  반환한다.
	 public MVCBoardDTO selectView(String idx) {
		 
		 MVCBoardDTO dto = new MVCBoardDTO();
		 
		 try { 
			  
			 String query = "select * from mvcboard where idx = ?";
			  pstmt = con.prepareStatement(query);
			  pstmt.setString(1, idx); 
			  
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 dto.setIdx(rs.getString("idx"));
				 dto.setName(rs.getString("name"));
				 dto.setTitle(rs.getString("title"));
				 dto.setContent(rs.getString("content"));
				 dto.setPostdate(rs.getDate("postdate"));
				 dto.setOfile(rs.getString("ofile"));
				 dto.setSfile(rs.getString("sfile"));
				 dto.setDowncount(rs.getInt("downcount"));
				 dto.setPass(rs.getString("pass"));
				 dto.setVisitcount(rs.getInt("visitcount"));
				 
			 }
			  
		 }catch (Exception e) {
			 System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		 
		 return dto;
	 }
	 
	 // 게시물 조회 1증가 
	 public void updateVisitCount(String idx) {
		 
		 try {
			 String query = "update mvcboard set visitcount= "
					  +" visitcount+1 where idx=?";
			 
			 pstmt =con.prepareStatement(query);
			 pstmt.setString(1, idx);
			 pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			 System.out.println("게시물 조회수 증가 중 예외 발생");
		    e.printStackTrace();
		}
		 
		 
	 }
	 
    public void downCountPlus(String idx) {
		 
		 try {
			 String query = "update mvcboard set downcount= "
					  +" downcount+1 where idx=?";
			 
			 pstmt =con.prepareStatement(query);
			 pstmt.setString(1, idx);
			 pstmt.executeUpdate();
			 
		 }catch (Exception e) {
			 System.out.println("게시물 다운로드 증가 중 예외 발생");
		    e.printStackTrace();
		}
		 
    }
    //비밀번호사 지정한 일련번호의 게시물 의 비밀번호 와 일치하는
    public boolean confirmPassword(String pass, String idx) {
    	
    	boolean isCorr = false;
    	
    	try {
    		
    		String query = "select pass from mvcboard where idx=?";
    		
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, idx);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			if(pass.equals(rs.getString("pass"))) {
    				isCorr = true;
    			} else {
    				System.out.println("비밀번호가 맞지 않습니다.");
    			}
    		}
    		
    	}catch (Exception e) {
			System.out.println("비밀번호 체크중 예외 발생");
			e.printStackTrace();
		}
    	
    	return isCorr;
    }
    
    //일련변호의 게시물을 삭제한다.
    public int deletePost(String idx) {
    	
    	int result = 0;
    	
       try {
    		
    		String query = "delete from mvcboard where idx=?";
    		
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, idx);
    		
    		
    		result = pstmt.executeUpdate();
    		
    	}catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}

             return result;
    }
    
    //게시글 데이터 를 받아 db에 저장되어 있던 내용
    public int updatePost(MVCBoardDTO dto) {
    	
    	int result =0;
    	
    	try {
    		String query = "update mvcboard set"
    				+" name=?, title=?, content=?, ofile=?, sfile=?"
    				+" where idx=? and pass=?";
    		
    		pstmt = con.prepareStatement(query);
    		pstmt.setString(1, dto.getName());
    		pstmt.setString(2, dto.getTitle());
    		pstmt.setString(3, dto.getContent());
    		pstmt.setString(4, dto.getOfile());
    		pstmt.setString(5, dto.getSfile());
    		pstmt.setString(6, dto.getIdx());
    		pstmt.setString(7, dto.getPass());
    		
    		result = pstmt.executeUpdate();
    	
    	}catch (Exception e) {
    		System.out.println("게시글 수정 중 예외발생");
			e.printStackTrace();
		}
    	
    	return result;
    }
    
}

package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect{
	
	//DB 연결
	 public BoardDAO(ServletContext application) {
		 super(application);
	 }
	 
	 public int selectCount(Map<String, Object> map) {
		 int totalCount = 0;
		 
		 String sql = "select count(*) from board";
		 
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
			 stmt = conn.createStatement();
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
	 
	 
	 //전체 목록 가져오기
	 public List<BoardDTO> selectList(Map<String, Object> map){
		
		 List<BoardDTO> bbs = new ArrayList<BoardDTO>();
		 
		 String query = "select * from board";
		 
		 //검색 조건
		 /*
		  * 예시
		  * SELECT * 
			FROM BOARD
			WHERE title LIKE '%음악%'
			ORDER BY num DESC;
		  */
		 if(map.get("searchWord") != null) {
			 query += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%'";
			 query += " order by num desc";
		 }
		 
		 try {
			 
			 pstmt = conn.prepareStatement(query);
			 rs = pstmt.executeQuery();		 
			 
			 while(rs.next()) {
				 BoardDTO dto = new BoardDTO();
				 
				 // String num = rs.getString("num");
				 // dto.setNum(num);
				 
				 /*
				  * 주의!!!!
				  * 예시...
				  * rs.getString("visitcount")  <== 이자리 변수명은 board db있는 값을 기입해야한다.
				  *                  
				  * BoardDTO 클래스 --> visitCount
				  * board db  --> visitcount
				  */
				 dto.setNum(rs.getString("num"));
				 dto.setTitle(rs.getString("title"));
				 dto.setContent(rs.getString("content"));
				 dto.setPostdate(rs.getDate("postdate"));
				 dto.setId(rs.getString("id"));
				 dto.setVisitcount(rs.getString("visitcount"));
				 
				 bbs.add(dto);
			 }
		 }catch(Exception e) {
			 System.out.println("게시물 조회 중 예외 발생");
			 e.printStackTrace();
		 }
		 
		 
		 return bbs;
	 }
	 
	 
	 //검색 조건에 맞는 게시물 목록을 반환합니다.(페이징 기능 지원)
	 public List<BoardDTO> selectListPage(Map<String, Object> map){
		 List<BoardDTO> bbs = new ArrayList<BoardDTO>();
		 
		 /*
		  * 예시
		  * SELECT * FROM (
				SELECT Tb.*, rownum rn FROM (
					SELECT * FROM board 
					WHERE 제목 LIKE '%제목%'
					ORDER BY num desc
				) Tb
			)
			WHERE rn BETWEEN  51 AND 60;
		  */
		 String query = "select * from ("
				 +" select tb.*, rownum as rNum from ("
				 +" select * from board";
		 
		 //검색조건 추가
		 if(map.get("SearchWord") != null) {
			 query += " where " + map.get("searchField")
			 		+ " like '%" + map.get("searchWord") + "%' ";
		 }
		 // 검색 조건 추가
		 query += " order by num desc) Tb)"
		       + " where rNum between ? and ?"; 
		 
		 try {
			 pstmt = conn.prepareStatement(query);
			 
			 //? 맵핑
			 pstmt.setString(1, map.get("start").toString());
			 pstmt.setString(2, map.get("end").toString());
			 
			 //쿼리문 실행
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 BoardDTO dto = new BoardDTO();
				 
				 dto.setNum(rs.getString("num"));
				 dto.setTitle(rs.getString("title"));
				 dto.setContent(rs.getString("content"));
				 dto.setPostdate(rs.getDate("postdate"));
				 dto.setId(rs.getString("id"));
				 dto.setVisitcount(rs.getString("visitcount"));
				 
				 bbs.add(dto);
			 }
			 
		 }catch(Exception e) {
			 System.out.println("게시물 조회 중 예외 발생");
			 e.printStackTrace();
		 }		 
		 
		 return bbs;
	 }
	 
	 
	 //저장(insert)
	 public int insertWrite(BoardDTO dto) {
		 int result = 0; 
		 
		 String query = "INSERT INTO board(num, title, content, id, visitcount) "
				 +" values(seq_board_num.nextval, ?, ?, ?,0)";
		 try {
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, dto.getTitle());
			 pstmt.setString(2, dto.getContent());
			 pstmt.setString(3, dto.getId());
			 
			 result = pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 System.out.println("게시물 입력 중 예외 발생!");
			 e.printStackTrace();
		 }
		 
		 return result;
	 }
	 
	 //num 해당 하는 레코드 검색
	 public BoardDTO selectView(String num) {
		 BoardDTO dto = new BoardDTO();
		 
		 String query = "select b.*, m.name from board b join member m on b.id = m.id " 
				 		+" where num = ?";
		 
		 try {
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, num);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 dto.setNum(rs.getString("num"));
				 dto.setTitle(rs.getString("title"));
				 dto.setContent(rs.getString("content"));
				 dto.setPostdate(rs.getDate("postdate"));
				 dto.setId(rs.getString("id"));
				 dto.setVisitcount(rs.getString("visitcount"));
				 dto.setName(rs.getString("name"));
			 }
			 
		 }catch(Exception e) {
			 System.out.println("게시물 상세보기 중 예외 발생");
			 e.printStackTrace();
		 }
		 
		 return dto;
	 }
	 
	 //조회수 증가
	 public void updateVisitCount(String num) {
		 
		 String query = "update board set visitcount = visitcount+ 1 "
				 +" where num = ?";
		 try {
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, num);
			 pstmt.executeUpdate();
		 }catch(Exception e) {
			 System.out.println("조회수 증가 중 예외 발생");
			 e.printStackTrace();
		 }		 
	 }
	 
	 
	 //수정하기
	 public int updateEdit(BoardDTO dto) {
		 int result=0;
		 
		 String query = "update board set title=?, content = ? "
				 +" where num=? ";
		 
		 try {
			 //먼저 실행해서 문번 오류 찾기
			 pstmt = conn.prepareStatement(query);
			
			 //? 맵핑
			 pstmt.setString(1, dto.getTitle());
			 pstmt.setString(2, dto.getContent());
			 pstmt.setString(3, dto.getNum());
			 
			 //실행
			 result = pstmt.executeUpdate();
			 
		 }catch(Exception e) {
			 System.out.println("게시물 수정 중 예외 발생");
			 e.printStackTrace();
		 }
		 
		 return result;
	 }
	 
	 //삭제하기
	 public int deletePost(String num) {
		 int result=0;
		 
		 String query = "delete from board where num=?";
		 
		 try {
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, num);
			 
			 result = pstmt.executeUpdate();
		 }catch(Exception e) {
			 System.out.println("게시글 삭제 중 예외 발생");
			 e.printStackTrace();
		 }
		 
		 return result;
	 }
}














package memberShip;

import java.util.ArrayList;
import java.util.List;

import common.JDBConnect;


//DB하고 연관된 일을 함(select, update, insert, delete 담당..)
public class MemberDAO extends JDBConnect{
	
	//명시한 DB의 연결이 완료된 MemberDAO 객체를 생성합니다.
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	//id, pass 받아서 DB조회해서 일치하는 회원이 있는지 여부 판단?
	public MemberDTO getMemberDTO(String uid, String upass) {
		
		MemberDTO dto = new MemberDTO();
		
		String sql = "select * from member where id = ? and pass= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.setString(2, upass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				String regidate = rs.getString("regidate");
				
				dto.setId(id);
				dto.setPass(pass);
				dto.setName(name);
				dto.setRegidage(regidate);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		return dto;
	}
	
	//회원 조회 1건 조회	
	
	//회원 목록 전체 조회	
	public List<MemberDTO> getAllList() {
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs  = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidage(rs.getString("regidate"));
				
				list.add(dto);		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//회원 추가
	
	//회원 변경
	
	//회원 삭제
}

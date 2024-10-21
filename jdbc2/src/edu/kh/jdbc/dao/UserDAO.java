package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.User;

import static edu.kh.jdbc.common.JDBCTemplate.*;

// DAO (Data Access Object)
// 데이터가 저장된 곳에 접근하는 용도의 객체
// -> DB에 접근하여 Java에서 원하는 결과를 얻기 위해
//    sql을 수행하고 결과를 반환 받는 역할
public class UserDAO {
	
	// 필드
	// - DB 접근 관련한 JDBC 객체 참조 변수 미리 선언
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/** 전달 받은 Connection을 이용해서 DB에 접근하여 
	 *  전달 받은 아이디와 일치하는 User 정보 조회하기
	 * @param conn : Service에서 생성한 Connection 객체
	 * @param input : View에서 입력받은 아이디
	 * @return 아이디가 일치하는 회원의 User 또는 null
	 */
	public User selectId(Connection conn, String input) {
		
		User user = null; // 결과 저장용 변수
		
		try {
			
			// SQL 작성
			String sql = "SELECT * FROM TB_USER WHERE USER_ID = ?";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ?(위치 홀더)에 알맞은 값 대입
			pstmt.setString(1, input);
			
			// SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			
			// 조회 결과가 있을 경우
			// -> 중복되는 아이디가 없을 경우
			//    1행만 조회되기 때문에 while문 보다는 if를 사용는게 효과적
			if(rs.next()) {
				// 첫 행에 데이터가 존재한다면
				
				// 각 컬럼의 값 얻어오기
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				
				// java.sql.Date 활용
				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				// 조회된 컬럼값을 이용해서 User 객체 생성
				user = new User(userNo, userId, userPw, userName, enrollDate.toString());
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 사용 한 JDBC 객체 자원 반환
			JDBCTemplate.close(conn);
			JDBCTemplate.close(pstmt);
			
			// Connection /
			
		}
		
		return user; // 결과 반환 ( 생성된 User 또는 null )
	}
	

	/** User 등록 DAO 메서드
	 * @param conn : DB 연결 정보가 담겨있는 Connection 객체
	 * @param user : 입력받은 id,pw,name이 세팅된 User 객체
	 * @return INSERT 결과 행의 개수
	 */
	public int insertUser(Connection conn, User user) throws Exception {
		
		// SQL 수행 중 발생하는 예외를
		// catch로 처리하지 않고, throws를 이용해서 호출부로 던져 처리
		// ->  catch문 필요 없다!
		
		// 1. 결과 저장용 변수 선언
		int result = 0;
		
		try {
			
			// 2. SQL 작성
			String sql = """
					INSERT INTO TB_USER
					VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)
					""";
			
			// 3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?(위치홀더) 알맞은 값 대입
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			// 5. SQL(INSERT) 수행(executeUpdate()) 후 결과 반환받기
			result = pstmt.executeUpdate();
			
			
		} finally {
			// 6. 사용한 jdbc 객체 자원 반환(close)
			close(pstmt);
			
		}
		
		// 결과 저장용 변수에 저장된 값 반환
		return result;
	}
	

	/** User 전체 조회 DAO 메서드
	 * @param conn
	 * @return userList
	 */
	public List<User> selectAll(Connection conn) throws Exception {
		
		// 1. 결과 저장용 변수 선언
		// -> List 같은 컬렉션을 반환하는 경우에는
		//    변수 선언 시 객체도 같이 생성해두자!
		List<User> userList = new ArrayList<User>();
		
		try {
			
			// 2. SQL 작성
			String sql = """
					SELECT 
						USER_NO,
						USER_ID,
						USER_PW,
						USER_NAME,
						TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') AS ENDOLL_DATE
					FROM TB_USER
					ORDER BY USER_NO ASC
					""";
			
			// 3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 에 알맞은 값 대입  (없으면 패스)
			
			// 5. SQL(SELECT) 수행(executeQuery) 후 
			//    결과 반환(ResultSet) 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과를 1행씩 접근하여 컬럼 값 얻어오기

			// 몇 행이 조회될지 모른다 -> while
			// 무조건 1행이 조회된다   -> if
			while(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENDOLL_DATE");
				// - java.sql.Date 타입으로 값을 저장하지 않은 이유
				// -> TO_CHAR() 를 이용해서 문자열로 변환했기 때문!
				
				// userList 에 추가
				// -> User 객체를 생성해 조회된 값을 담고
				//    userList에 추가하기!
				User user = new User(userNo, userId, userPw, userName, enrollDate);
				
				userList.add(user);
				
			}

		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		// 조회 결과가 담긴 List 반환
		return userList;
	}


	/** User 이름 검색 DAO 메서드
	 * @param conn
	 * @param input
	 * @return userList
	 */
	public List<User> selectName(Connection conn, String input) throws Exception {
		
		List<User> userList = new ArrayList<User>();
		
		try {
			
			String sql = """
					SELECT 
						USER_NO,
						USER_ID,
						USER_PW,
						USER_NAME,
						TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') AS ENDOLL_DATE
					FROM TB_USER
					WHERE USER_NAME LIKE '%' || ? || '%'
					ORDER BY USER_NO ASC
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, input);
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENDOLL_DATE");
				
				User user = new User(userNo, userId, userPw, userName, enrollDate);
				
				userList.add(user);
				
			}

		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return userList;
	}


	/** USER_NO 검색 조회 DAO 메서드
	 * @param conn
	 * @param no
	 * @return user
	 */
	public User selectUser(Connection conn, int no) throws Exception {
		
		User user = new User();
		
		try {
			
			String sql = """
					SELECT
						USER_NO,
						USER_ID,
						USER_PW,
						USER_NAME,
						TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') AS ENDOLL_DATE
					FROM TB_USER
					WHERE USER_NO = ?
					ORDER BY USER_NO ASC
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENDOLL_DATE");
				
				user = new User(userNo, userId, userPw, userName, enrollDate);
				
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		
		return user;
	}


	/** USER_NO를 입력 받아 일치하는 User 삭제 DAO 메서드
	 * @param conn
	 * @param userNo
	 * @return result
	 */
	public int deleteUser(Connection conn, int no) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = """
					DELETE FROM TB_USER WHERE USER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(rs);
			close(pstmt);
			
		}

		return result;
	}


	/** User 의 ID, PW를 받아 조회하는 DAO 메서드
	 * @param user
	 * @return int
	 */
	public int selectIdPw(Connection conn, User user) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = """
					SELECT 
						COUNT(*)
					FROM TB_USER
					WHERE USER_ID = ?
					AND USER_PW = ? 
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
		} 
		
		
		return result;
	}


	/** 입력한 Name으로 회원 이름 수정 DAO 메서드
	 * @param conn
	 * @param user
	 * @return result
	 */
	public int updateName(Connection conn, User user) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = """
					UPDATE TB_USER
					SET USER_NAME = ?
					WHERE USER_ID = ?
					AND USER_PW = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getUserPw());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return result;
	}

}

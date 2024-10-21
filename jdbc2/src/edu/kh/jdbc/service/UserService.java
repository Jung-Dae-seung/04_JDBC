package edu.kh.jdbc.service;

// import static : 지정된 경로에 존재하는 static 구문을 모두 얻어와
// 클래스명.메서드명() 이 아닌 메서드명() 만 작성해도 호출 가능하게 함
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDAO;
import edu.kh.jdbc.dto.User;

public class UserService {
	
	// 필드
	private UserDAO dao = new UserDAO();

	/** 전달 받은 아이디와 일치하는 User 정보 반환 서비스
	 * @param input
	 * @return
	 */
	public User selectId(String input) {
		
		// 커넥션
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		User user = dao.selectId(conn, input);
		
		// 다 쓴 커넥션 닫기
		JDBCTemplate.close(conn);
		
		JDBCTemplate.close(conn);
		
		return user;
	}

	/** User 등록 서비스
	 * @param user : 입력받은 id, pw, name이 세팅된 객체
	 * @return 삽입 성공한 결과 행의 개수
	 */
	public int insertUser(User user) throws Exception{
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. 데이터 가공(할게 없으면 넘어감)
		
		// 3. DAO 메서드 호출 후 결과 반환 받기
		// 결과(삽입 성공한 개수, int) 반환 받기
		int result = dao.insertUser(conn, user);
		
		// 4. INSERT 수행 결과에 따라 트랜잭션 제어 처리
		if(result > 0) { // INSERT 성공
			commit(conn);
			
		} else { // INSERT 실패
			rollback(conn);
			
		}
		
		// 5. Connection 반환하기
		close(conn);
		
		// 6. 결과 반환
		return result;
	}

	/** User 전체 조회 서비스
	 * @return 조회된 User가 담긴 List 
	 */
	public List<User> selectAll() throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		// 2. 데이터 가공(없으면 넘어감)
		
		// 3. DAO 메서드 호출 후 결과 반환(List<User>)받기
		List<User> userList = dao.selectAll(conn);
		
		
		// 4. DML 인 경우 트랜잭션 처리
		//    SELECT 는 안해도 된다!
		
		// 5. Connection 반환
		close(conn);
		
		// 6. 결과 반환
		return userList;
	}

	/** User 이름 검색 조회 서비스
	 * @param input
	 * @return 조회된 User가 담긴 List 
	 */
	public List<User> selectName(String input) throws Exception{
		
		Connection conn = getConnection();
		
		List<User> userList = dao.selectName(conn, input);
		
		close(conn);
		
		return userList;
	}

	/** USER_NO 검색 조회 서비스
	 * @param userNo
	 * @return user
	 */
	public User selectUser(int userNo) throws Exception {
		
		Connection conn = getConnection();
		
		User user = dao.selectUser(conn, userNo);
		
		close(conn);
		
		return user;
	}

	/** USER_NO를 입력 받아 일치하는 User 삭제 서비스
	 * @param userNo
	 * @return result
	 */
	public int delteUser(int userNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteUser(conn, userNo);
		
		close(conn);
		
		return result;
	}

	/** ID, PW를 입력받아 회원 조회 서비스
	 * @param user
	 * @return result
	 */
	public int selectIdPw(User user) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.selectIdPw(conn, user);
		
		close(conn);
		
		return result;
	}

	/** 입력한 Name으로 해당 회원 이름 수정 서비스
	 * @param user
	 * @return result
	 */
	public int updateName(User user) throws Exception {
		
		Connection conn = getConnection(); 
				
		int result = dao.updateName(conn, user);
		
		close(conn);
		
		return result;
	}

}

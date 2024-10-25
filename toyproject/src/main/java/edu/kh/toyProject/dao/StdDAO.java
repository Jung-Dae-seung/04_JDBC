package edu.kh.toyProject.dao;

import java.sql.Connection;
import java.util.List;

import edu.kh.toyProject.dto.Student;

public interface StdDAO {

	/** 학생 전체 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	List<Student> stdList(Connection conn) throws Exception;

	/** 학생 상세 조회
	 * @param conn
	 * @param stdNo
	 * @return
	 * @throws Exception
	 */
	Student detailStd(Connection conn, int stdNo) throws Exception;

	/** 학생 삭제
	 * @param conn
	 * @param stdNo
	 * @return
	 * @throws Exception
	 */
	int deleteStd(Connection conn, int stdNo) throws Exception;

	/** 학생 등록
	 * @param conn
	 * @param std
	 * @return
	 * @throws Exception
	 */
	int insertStd(Connection conn, Student std) throws Exception;

	/** 학생 수정
	 * @param conn
	 * @param std
	 * @return
	 * @throws Exception
	 */
	int updateStd(Connection conn, Student std) throws Exception;

}

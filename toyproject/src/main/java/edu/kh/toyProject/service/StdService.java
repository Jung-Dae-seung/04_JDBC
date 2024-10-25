package edu.kh.toyProject.service;

import java.util.List;

import edu.kh.toyProject.dto.Student;

public interface StdService {

	/** 학생 전체 조회 서비스
	 * @return
	 * @throws Exception
	 */
	List<Student> stdList() throws Exception;

	/** 학생 상세 조회 서비스
	 * @return
	 * @throws Exception
	 */
	Student detailStd(int stdNo) throws Exception;

	/** 학생 삭제 서비스
	 * @param stdNo
	 * @return
	 * @throws Exception
	 */
	int deleteStd(int stdNo) throws Exception;

	/** 학생 등록 서비스
	 * @param std
	 * @return
	 * @throws Exception
	 */
	int insertStd(Student std) throws Exception;

	/** 학생 수정 서비스
	 * @param std
	 * @return
	 * @throws Exception
	 */
	int updateStd(Student std) throws Exception;

}

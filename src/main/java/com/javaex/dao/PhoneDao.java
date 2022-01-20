package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession;

	//전체 리스트 가져오기
	public List<PersonVo> getPersonList(){
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		System.out.println(personList);

		return personList;
	}
	
	
	// 사람 추가 저장
	public int personInsert(PersonVo personVo) {

		//성공하면 무조건 숫자를 줌
		int count = sqlSession.insert("phonebook.insert",personVo);
		System.out.println(count+"건이 처리되었습니다");
		
		return count;

		//return sqlSession.insert("phonebook.insert",personVo);
		
	}
	
	// 사람 삭제 (데이터 한개 받아올때 )
	public int personDelete(int personId) {
		
		System.out.println("phoneDao.personDelete()실행");
		return sqlSession.delete("phonebook.delete",personId);
		
	}
		
		
	//사람 수정폼 (한명의 데이터 불러오기)
	public PersonVo getPerson(int personId) {
		
		System.out.println("phoneDao.getPerson()실행");
		PersonVo personVo = sqlSession.selectOne("phonebook.updateForm",personId);
		System.out.println(personVo);
		
		return  personVo;
	}
		
	// 사람 수정
	public int personUpdate(PersonVo personVo) {

		System.out.println("phoneDao.personUpdate()실행");
		return sqlSession.update("phonebook.update",personVo);
	}
	
	
	/*
	// 사람 리스트(검색안할때)
	public List<PersonVo> getPersonList() {
		return getPersonList("");
	}

	// 사람 리스트(검색할때)
	public List<PersonVo> getPersonList(String keword) {
		List<PersonVo> personList = new ArrayList<PersonVo>();

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " select  person_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " from person";

			if (keword != "" || keword == null) {
				query += " where name like ? ";
				query += " or hp like  ? ";
				query += " or company like ? ";
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기

				pstmt.setString(1, '%' + keword + '%'); // ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, '%' + keword + '%'); // ?(물음표) 중 2번째, 순서중요
				pstmt.setString(3, '%' + keword + '%'); // ?(물음표) 중 3번째, 순서중요
			} else {
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			}

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);
				personList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return personList;

	}*/
	
	


	

	

}
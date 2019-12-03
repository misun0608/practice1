package com.spring.springform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping(value = "inputForm.me")
	public String inputForm() {
		return "inputform";
	}

	// 글쓰기 작업
	@RequestMapping(value = "inputProcess.me")
	public String inputProcess(EmpVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String sql = "insert into emp_copy values (?, ?, ?, ?, sysdate, ?, ?, ?)";

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			pstmt.setString(2, vo.getEname());
			pstmt.setString(3, vo.getJob());
			pstmt.setInt(4, vo.getMgr());
			pstmt.setDouble(5, vo.getSal());
			pstmt.setDouble(6, vo.getComm());
			pstmt.setInt(7, vo.getDeptno());
		} catch (Exception e) {
//	         e.printStackTrace();	// 뭐지 이게.. 이거 넣으니까 되는데..?
		}
		return "index";
	}

	// 리스트 구하는 작업
	@RequestMapping(value = "selectProcess.me")
	public String selectProcess(Model model) { // 처음에 model객체에는 아무런 데이터 없음
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement("select * from emp_copy order by ename asc");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EmpVO empvo = new EmpVO();
				empvo.setEmpno(rs.getInt("empno"));
				empvo.setEname(rs.getString("ename"));
				empvo.setJob(rs.getString("job"));
				empvo.setMgr(rs.getInt("mgr"));
				empvo.setHiredate(rs.getDate("hiredate"));
				empvo.setSal(rs.getDouble("sal"));
				empvo.setComm(rs.getDouble("comm"));
				empvo.setDeptno(rs.getInt("deptno"));
				list.add(empvo);
			}
			model.addAttribute("list", list);

		} catch (Exception e) {

		}
		return "list";
	}

	// 출력 작업
	@RequestMapping(value = "selectDeptProcess.me")
	public String selectDeptProcess(Model model, HttpServletRequest request) { // 처음에 model객체에는 아무런 데이터 없음
		// public String selectDeptProcess(Model model, @RequestParam(value="deptno", required=false,defaultValue="1")) // 선생님 버전
		// required=false, defaultValue="1" > 전달된 값이 없는 경우 디폴트값을 1로 하겠다 / 저게 필수는 아니지만 만약에 값이 없는 경우 무조건 파라미터에 deptno가 있어야함 아니면 오류날껄...?
		// int deptno에 파라미터로 들어온 deptno 값을 저장한다는 의미!
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "123456");
			pstmt = con.prepareStatement("select * from dept_copy where deptno=?");
			pstmt.setInt(1, Integer.parseInt(request.getParameter("deptno")));
			// pstmt.setInt(1, deptno);	// 선생님 버전
			rs = pstmt.executeQuery();
			
			rs.next();
			DeptVO deptvo = new DeptVO();
			deptvo.setDeptno(rs.getInt("deptno"));
			deptvo.setDname(rs.getString("dname"));
			deptvo.setLoc(rs.getString("loc"));

			model.addAttribute("deptvo", deptvo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "selectDept";
	}
}

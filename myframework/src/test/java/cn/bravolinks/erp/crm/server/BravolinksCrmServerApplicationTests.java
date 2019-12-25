//package cn.bravolinks.erp.crm.server;
//
//import cn.bravolinks.erp.crm.server.dao.CustomerInfoDao;
//import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BravolinksCrmServerApplicationTests {
//
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
//	@Autowired
//	private BO_CRM_APPRIGHTService bO_CRM_APPRIGHTService;
//
//	@Test
//	public void contextLoads() throws SQLException {
//		Connection conn = sqlSessionFactory.openSession().getConnection();
//		DatabaseMetaData data = conn.getMetaData();
//		ResultSet rs = data.getColumns(null, null, "PM_DUOCIDUIZHANG", "%");
//		// while (rs.next()){
//		// //打印字段name信息
//		// System.out.println(rs.getString("COLUMN_NAME"));
//		// }
//
//		// 遍历resultset
//		List<Map<String, Object>> list = new ArrayList<>();
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int count = rsmd.getColumnCount();
//		while (rs.next()) {
//			Map rowData = new HashMap();// 声明Map
//			for (int i = 1; i <= count; i++) {
//				System.out.println(rsmd.getColumnName(i) + "=" + rs.getObject(i));
//				rowData.put(rsmd.getColumnName(i), rs.getObject(i));// 获取键名及值
//			}
//			list.add(rowData);
//		}
//		System.out.println("list = " + list.size());
//	}
//
//	@org.junit.Test
//	public void tst() {
//		boolean b = bO_CRM_APPRIGHTService.checkCountOfFwbm("B0001253");
//		System.out.println(b);
//	}
//
//	@org.junit.Test
//	public void tst1() {
//		// 待办任务状态
//		String dbStatus = bO_CRM_APPRIGHTService.getDbStatusOfTask("2895181", "3228451");
//		// 已办任务状态
//		String ybStatus = bO_CRM_APPRIGHTService.getYbStatusOfTask("2895181", "3228451");
//		System.out.println("待办任务：" + dbStatus);
//		System.out.println("已办任务：" + ybStatus);
//	}
//
//	@org.junit.Test
//	public void paixu() {
//		int a[] = {9,8,7,6,5,4};
//		int temp = 0;
//		for (int i = 1; i < a.length; i++) {
//			int j = i - 1;
//			temp = a[i];
//			for (; j >= 0 && temp < a[j]; j--) {
//				a[j + 1] = a[j]; // 将大于temp 的值整体后移一个单位
//			}
//			a[j + 1] = temp;
//		}
//		for (int i = 0; i < a.length; i++) {
//			System.out.println(a[i]);
//		}
//
//	}
//
//}

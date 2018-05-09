package com.bluedot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bluedot.dao.CheckDao;
import com.bluedot.po.CheckReturnInfo;
import com.bluedot.po.Recommender;
import com.bluedot.util.DBConnection;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

public class CheckDaoImpl implements CheckDao {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	ResultSet rs1 = null;

	public List<Recommender> RecoInterview(int empId, String currStatus) {
		try {
			conn = DBConnection.getConn();
			// ����ж����������޸ġ�������,��һ��ɸѡ֮�󣬾͵ð��Ƽ���ĵ�ǰ�����hr,��Ϊ�Լ���
			String sql = "SELECT reco_id,reco_name,emp_id,reco_mailbox,reco_current_status FROM recommender_table WHERE emp_id !=? AND reco_current_status =? AND (current_hr_id is null or current_hr_id=?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			pstm.setString(2, currStatus);
			pstm.setInt(3, empId);
			rs = pstm.executeQuery();
			// ����Ӧ���жϵ�ǰ�Ƿ�������HR����������Լ��Ƽ����ˣ��������Դ���������
			List<Recommender> list = new ArrayList<Recommender>();
			while (rs.next()) {
				Recommender reco = new Recommender();
				reco.setRecoId(rs.getInt("reco_id"));
				reco.setRecoName(rs.getString("reco_name"));
				reco.setEmpId(rs.getInt("emp_id"));
				reco.setMail(rs.getString("reco_mailbox"));
				reco.setCurrStatus(rs.getString("reco_current_status"));
				// Ȼ������Ƽ��˵�empIdȥ�鿴�Ƽ��˵�����
				sql = "select emp_fullname from emp_details_table where emp_id =?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, rs.getInt("emp_id"));
				rs1 = pstm.executeQuery();
				String empFullName = null;
				while (rs1.next()) {
					empFullName = rs1.getString(1);
				}
				reco.setEmpFullname(empFullName);
				list.add(reco);

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	public boolean modifyInterviewStatus(int recoId, String currentStatus,
			int empId) {
		System.out.println("���Ƽ���ǰ��Ҫ�޸ĵ�״̬" + currentStatus);
		int flag=0;
		try {
			conn = DBConnection.getConn();
			String sql = "update recommender_table set RECO_IS_RECOMMENDED ='T', reco_current_status=?,current_hr_id =? where reco_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, currentStatus);
			pstm.setInt(2, empId);
			pstm.setInt(3, recoId);
			int num = pstm.executeUpdate();
			if (num == 1) {
				flag=1;
			}
           if((flag==1)&&"step3".equals(currentStatus ))
           {
        	// ����empId����ӱ��Ƽ��ɹ��󻹵ø��Ƽ������10������.
				sql = "update credit_grade_table set total_credit=total_credit+10 where emp_id =? ";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				pstm.execute();
				sql = "select total_credit from credit_grade_table where emp_id =? ";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				rs = pstm.executeQuery();
				// rs.next();//�ᷢ��������ľ��쳣����������������
				while (rs.next()) {
					int totalCredit = rs.getInt("total_credit");
					if ((totalCredit > 500) && (totalCredit < 1000)) {
						sql = "update credit_grade_table set CREDIT_GRADE=2 where emp_id =? ";
						pstm = conn.prepareStatement(sql);
						pstm.setInt(1,empId);
						pstm.execute();
					} else if (totalCredit >= 1000) {
						sql = "update credit_grade_table set CREDIT_GRADE=3 where emp_id =? ";
						pstm = conn.prepareStatement(sql);
						pstm.setInt(1, empId);
						pstm.execute();
                }
			}
	      }
           return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * ������ʧ���˲ŷ����˲ſ�
	 * 
	 */
	public boolean returnRecommender(int recoId) {
		try {
			conn = DBConnection.getConn();
			String sql = "update recommender_table set reco_current_status='step', RECO_IS_RECOMMENDED ='F',current_hr_id='' where reco_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, recoId);
			int num = pstm.executeUpdate();
			if (num == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBConnection.close(pstm, conn, null);
		}
		return false;
	}

	// ����recoId�ҵ���Ӧ���Ƽ���empId,Ȼ�����empId�ҵ�Ա��������Ӧ��������룬Ȼ�����������뷢�ʼ�����Ա����

	public CheckReturnInfo getMailBox(int recoId) {
		try {
			conn = DBConnection.getConn();
			String sql = "select emp_id,reco_name from recommender_table where reco_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, recoId);
			rs = pstm.executeQuery();
			int empId = 0;
			String recoName = null;
			while (rs.next()) {
				empId = rs.getInt(1);
				recoName = rs.getString(2);
				System.out.println("�Ƽ���ID" + empId);
			}
			sql = "select emp_mailbox,emp_fullname from emp_details_table where emp_id =?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			String mailBox = null;
			String empFullName = null;
			while (rs.next()) {
				mailBox = rs.getString(1);
				empFullName = rs.getString(2);
				System.out.println("�������" + mailBox);
			}
			CheckReturnInfo cri = new CheckReturnInfo();
			cri.setEmpFullName(empFullName);
			cri.setMailBox(mailBox);
			cri.setRecoName(recoName);
			return cri;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}
}

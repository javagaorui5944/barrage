package com.gaorui.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import com.sample.Bean.Danmu;
import com.sample.Dbcp.Dbcp;

public class DanmuDao {
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;

	public boolean saveDanmu(String d_Id, String d_UserName, String d_Ip,
			String d_Text) {
		boolean b = false;
		Dbcp cd = new Dbcp();
		try {
			conn = cd.getConn();
			String sql = "INSERT INTO danmu( d_Id,d_UserName, d_Ip, d_Text) VALUE('"
					+ d_Id
					+ "','"
					+ d_UserName
					+ "','"
					+ d_Ip
					+ "','"
					+ d_Text
					+ "')";
			pstm = conn.prepareStatement(sql);
			int num = pstm.executeUpdate();
			if (num == 1) {
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean increaseCount(String d_Id) {
		boolean b = false;
		String sql = "UPDATE danmu SET d_count=danmu.d_count+1 WHERE d_Id='"
				+ d_Id + "'";
		Dbcp cd = new Dbcp();
		try {
			conn = cd.getConn();
			pstm = conn.prepareStatement(sql);
			int num = pstm.executeUpdate();
			if (num == 1) {
				b = true;
			}
		} catch (Exception x) {

			x.printStackTrace();
		} finally {
			cd.close(rs, pstm, conn);
		}
		return b;
	}

	public boolean checkSame(String d_Id) {
		boolean b = false;
		String sql = "SELECT d_Id,d_UserName,d_Ip,d_Ip,d_Text,d_count FROM danmu WHERE d_Id ='"
				+ d_Id + "'";
		Dbcp cd = new Dbcp();
		try {
			conn = cd.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				b = true;
			}
		} catch (Exception x) {

			x.printStackTrace();
		}
		return b;
	}

	public ArrayList<Danmu> getDanmu() {
		ArrayList<Danmu> al = new ArrayList<Danmu>();
		Dbcp cd = new Dbcp();
		try {
			conn = cd.getConn();
			String sql = "SELECT d_Id,d_UserName,d_Ip,d_Ip,d_Text,d_count FROM danmu ORDER BY (d_autoid) DESC";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Danmu dm = new Danmu();
				dm.setD_Id(rs.getString("d_Id"));
				dm.setD_UserName(rs.getString("d_UserName"));
				dm.setD_Ip(rs.getString("d_Ip"));
				dm.setD_Text(rs.getString("d_Text"));
				dm.setD_count(rs.getInt("d_count"));
				al.add(dm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cd.close(rs, pstm, conn);
			;
		}
		return al;
	}

	public boolean Searchipstatus(String ip) {
		boolean b = false;
		String sql = "SELECT ip FROM danmuip WHERE ip='" + ip + "'";
		Dbcp cd = new Dbcp();
		try {
			conn = cd.getConn();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				b = true;
			}

		} catch (Exception x) {

			x.printStackTrace();
		} finally {
			cd.close(rs, pstm, conn);
		}
		return b;
	}

	public boolean saveDanmus(String d_UserName, String d_Ip, String d_Text) {
		boolean b = false;
		Dbcp cd = new Dbcp();
		try {
			conn = cd.getConn();
			String sql = "INSERT INTO danmus( d_UserName, d_Ip, d_Text) VALUE('"+ d_UserName + "','" + d_Ip + "','" + d_Text + "')";
			pstm = conn.prepareStatement(sql);
			int num = pstm.executeUpdate();
			if (num == 1) {
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}
}

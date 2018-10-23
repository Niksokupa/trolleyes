package net.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import net.daw.bean.FacturaBean;

public class FacturaDao {

	Connection oConnection;
	String ob = null;

	public FacturaDao(Connection oConnection, String ob) {
		super();
		this.oConnection = oConnection;
		this.ob = ob;
	}

	public FacturaBean get(int id) throws Exception {
		String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
		FacturaBean oFacturaBean;
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setInt(1, id);
			oResultSet = oPreparedStatement.executeQuery();
			if (oResultSet.next()) {
				oFacturaBean = new FacturaBean();
				oFacturaBean.setId(oResultSet.getInt("id"));
				oFacturaBean.setIva(oResultSet.getDouble("iva"));
                                oFacturaBean.setId_usuario(oResultSet.getInt("id_usuario"));
                                oFacturaBean.setFecha(oResultSet.getDate("fecha"));
			} else {
				oFacturaBean = null;
			}
		} catch (SQLException e) {
			throw new Exception("Error en Dao get de " + ob, e);
		} finally {
			if (oResultSet != null) {
				oResultSet.close();
			}
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return oFacturaBean;
	}

	public int remove(int id) throws Exception {
		int iRes = 0;
		String strSQL = "DELETE FROM " + ob + " WHERE id=?";
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setInt(1, id);
			iRes = oPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error en Dao remove de " + ob, e);
		} finally {
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return iRes;
	}

	public int getcount() throws Exception {
		String strSQL = "SELECT COUNT(id) FROM " + ob;
		int res = 0;
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oResultSet = oPreparedStatement.executeQuery();
			if (oResultSet.next()) {
				res = oResultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new Exception("Error en Dao get de " + ob, e);
		} finally {
			if (oResultSet != null) {
				oResultSet.close();
			}
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return res;
	}

	public FacturaBean create(FacturaBean oFacturaBean) throws Exception {
		String strSQL = "INSERT INTO " + ob + " (`id`, `fecha`, `iva`, `id_usuario`) VALUES (NULL, ?,?,?); ";
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setDate(1,(Date) oFacturaBean.getFecha());
                        oPreparedStatement.setDouble(2, oFacturaBean.getIva());
                        oPreparedStatement.setInt(3, oFacturaBean.getId_usuario());
			oPreparedStatement.executeUpdate();
			oResultSet = oPreparedStatement.getGeneratedKeys();
			if (oResultSet.next()) {
				oFacturaBean.setId(oResultSet.getInt(1));
			} else {
				oFacturaBean.setId(0);
			}
		} catch (SQLException e) {
			throw new Exception("Error en Dao create de " + ob, e);
		} finally {
			if (oResultSet != null) {
				oResultSet.close();
			}
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return oFacturaBean;
	}

	public int update(FacturaBean oFacturaBean) throws Exception {
		int iResult = 0;
		String strSQL = "UPDATE " + ob + " SET fecha = ?, iva = ?, id_usuario = ? WHERE id = ?;";

		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setDate(1, (Date) oFacturaBean.getFecha());
			oPreparedStatement.setDouble(2, oFacturaBean.getIva());
                        oPreparedStatement.setInt(3, oFacturaBean.getId_usuario());
                        oPreparedStatement.setInt(4, oFacturaBean.getId());
			iResult = oPreparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new Exception("Error en Dao update de " + ob, e);
		} finally {
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return iResult;
	}

	public ArrayList<FacturaBean> getpage(int iRpp, int iPage) throws Exception {
		String strSQL = "SELECT * FROM " + ob;
		ArrayList<FacturaBean> alFacturaBean;
		if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
			strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
			ResultSet oResultSet = null;
			PreparedStatement oPreparedStatement = null;
			try {
				oPreparedStatement = oConnection.prepareStatement(strSQL);
				oResultSet = oPreparedStatement.executeQuery();
				alFacturaBean = new ArrayList<FacturaBean>();
				while (oResultSet.next()) {
					FacturaBean oFacturaBean = new FacturaBean();
					oFacturaBean.setId(oResultSet.getInt("id"));
					oFacturaBean.setFecha(oResultSet.getDate("fecha"));
                                        oFacturaBean.setIva(oResultSet.getDouble("iva"));
                                        oFacturaBean.setId_usuario(oResultSet.getInt("id_usuario"));
					alFacturaBean.add(oFacturaBean);
				}
			} catch (SQLException e) {
				throw new Exception("Error en Dao getpage de " + ob, e);
			} finally {
				if (oResultSet != null) {
					oResultSet.close();
				}
				if (oPreparedStatement != null) {
					oPreparedStatement.close();
				}
			}
		} else {
			throw new Exception("Error en Dao getpage de " + ob);
		}
		return alFacturaBean;

	}

}

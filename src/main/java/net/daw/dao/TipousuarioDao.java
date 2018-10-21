package net.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import net.daw.bean.TipousuarioBean;

public class TipousuarioDao {

	Connection oConnection;
	String ob = "tipoUsuario";

	public TipousuarioDao(Connection oConnection) {
		super();
		this.oConnection = oConnection;
	}

	public TipousuarioBean get(int id) throws Exception {
		String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
		TipousuarioBean oTipousuarioBean;
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement =null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setInt(1, id);
			oResultSet = oPreparedStatement.executeQuery();
			if (oResultSet.next()) {
				oTipousuarioBean = new TipousuarioBean();
				oTipousuarioBean.setId(oResultSet.getInt("id"));
				oTipousuarioBean.setDesc(oResultSet.getString("desc"));
			} else {
				oTipousuarioBean = null;
			}
		} catch (SQLException e) {
			throw new Exception ("Error en Dao get de tipousuario",e);
		} finally {
			if (oResultSet!=null) {
				oResultSet.close();
			}
			if (oPreparedStatement!=null) {
				oPreparedStatement.close();
			}
		}

		return oTipousuarioBean;

	}
        
        public String create(TipousuarioBean oTipousuarioBean) throws Exception {
		StringBuilder strSql = new StringBuilder();

                strSql.append("INSERT INTO `"+ ob +"`").
                append("(`id`,`desc`) ").
                append("VALUES (?,?)");
		String estado = "El tipo de usuario --" + oTipousuarioBean.getDesc() + "-- no ha sido creado.";

		PreparedStatement oPreparedStatement =null;
		try {
                    oPreparedStatement = oConnection.prepareStatement(strSql.toString());

                    oPreparedStatement.setInt(1, oTipousuarioBean.getId());
                    oPreparedStatement.setString(2, oTipousuarioBean.getDesc());
                    oPreparedStatement.execute();
                    estado = "El tipo de usuario --" + oTipousuarioBean.getDesc() + "-- ha sido creado satisfactoriamente.";
		} catch (SQLException e) {
			throw new Exception ("Error en Dao create de tipousuario",e);
		} finally {
			if (oPreparedStatement!=null) {
				oPreparedStatement.close();
			}
		}

		return estado;

	}
        
        public String update(TipousuarioBean oTipousuarioBean) throws Exception {
                StringBuilder sql = new StringBuilder();
                // Creamos la sentencia SQL UPDATE
                sql.append("UPDATE `tipousuario` SET ").
                        append("`desc` = ? ").
                        append("WHERE id = ?");

                String estado = "El tipo de usuario --" + oTipousuarioBean.getDesc() + "-- no ha podido ser modificado.";
                PreparedStatement oPreparedStatement = null;

                try {
                    oPreparedStatement = oConnection.prepareStatement(sql.toString());

                    oPreparedStatement.setString(1, oTipousuarioBean.getDesc());
                    oPreparedStatement.setInt(2, oTipousuarioBean.getId());
                    oPreparedStatement.execute();
                    estado = "El tipo de usuario --" + oTipousuarioBean.getDesc() + "-- ha sido modificado con éxito.";

                } catch (SQLException e) {
                    throw new Exception("Error en Dao Update de tipousuario", e);
                } finally {

                    if (oPreparedStatement != null) {
                        oPreparedStatement.close();
                    }
                }

                return estado;
        }
        
        public String remove(TipousuarioBean oTipousuarioBean) throws Exception {
            String strSQL = "DELETE FROM " + ob + " WHERE id=?";
            String estado = "El tipo de usuario --" + oTipousuarioBean.getDesc() + "-- no ha podido ser eliminado.";
            PreparedStatement oPreparedStatement =null;
            try {
                    oPreparedStatement = oConnection.prepareStatement(strSQL);
                    oPreparedStatement.setInt(1, oTipousuarioBean.getId());
                    oPreparedStatement.execute();
                    estado = "El tipo de usuario --" + oTipousuarioBean.getDesc() + "-- ha sido eliminado correctamente.";
		} catch (SQLException e) {
			throw new Exception ("Error en Dao remove de tipousuario",e);
		} finally {
			if (oPreparedStatement!=null) {
				oPreparedStatement.close();
			}
		}
		return estado;
        }
        
        public int getcount() throws Exception {
		String strSQL = "SELECT COUNT(id) FROM " + ob;
		int res=0;
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement =null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oResultSet = oPreparedStatement.executeQuery();
			if (oResultSet.next()) {
				res = oResultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new Exception ("Error en Dao get de tipousuario",e);
		} finally {
			if (oResultSet!=null) {
				oResultSet.close();
			}
			if (oPreparedStatement!=null) {
				oPreparedStatement.close();
			}
		}
		return res;
	}
        
        public int getMaxId() throws Exception {
            String strSQL = "SELECT MAX(id) FROM " + ob;
            int idRes = 0;

            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;

            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                if (oResultSet.next()) {
                    idRes = oResultSet.getInt(1);
                }
            } catch (SQLException e) {
                throw new Exception("Error en Dao getMaxId de tipousuario", e);
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
            idRes += 1; //le sumamos uno para obtener el siguiente id disponible
        return idRes;
    }
}

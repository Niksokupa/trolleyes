package net.daw.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import net.daw.bean.ReplyBean;
import net.daw.bean.TipousuarioBean;
import net.daw.connection.publicinterface.ConnectionInterface;
import net.daw.constant.ConnectionConstants;
import net.daw.dao.TipousuarioDao;
import net.daw.factory.ConnectionFactory;
import net.daw.helper.EncodingHelper;

public class TipousuarioService {

	HttpServletRequest oRequest ;
	
	
	public TipousuarioService(HttpServletRequest oRequest) {
		super();
		this.oRequest = oRequest;
	}


	public ReplyBean get() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection ;
		try {
			Integer id = Integer.parseInt(oRequest.getParameter("id"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			TipousuarioDao oTipousuarioDao=new TipousuarioDao(oConnection);
			TipousuarioBean oTipousuarioBean = oTipousuarioDao.get(id);
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(oTipousuarioBean));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500, "Bad Connection: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
        
        public ReplyBean remove() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection ;
		try {
			Integer id = Integer.parseInt(oRequest.getParameter("id"));
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			TipousuarioDao oTipousuarioDao=new TipousuarioDao(oConnection);
			boolean resultado = oTipousuarioDao.remove(id);
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(resultado));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500, "Bad Connection: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
                
        public ReplyBean create() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection ;
		try {
                    TipousuarioBean oTipousuario;
                    oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
                    oConnection = oConnectionPool.newConnection();
                    TipousuarioDao oTipousuarioDao = new TipousuarioDao(oConnection);
                    String sDesc = oRequest.getParameter("desc");
                    Integer maxId = oTipousuarioDao.getMaxId();
                    oTipousuario = new TipousuarioBean();
                    oTipousuario.setId(maxId);
                    oTipousuario.setDesc(sDesc);

                    String resultado = oTipousuarioDao.create(oTipousuario);
                    Gson oGson = new Gson();
                    oReplyBean = new ReplyBean(200, oGson.toJson(resultado));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500, "Bad Connection: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
        
        public ReplyBean update() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection ;
		try {
                    TipousuarioBean oTipousuario;
                    oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
                    oConnection = oConnectionPool.newConnection();
                    TipousuarioDao oTipousuarioDao = new TipousuarioDao(oConnection);
                    Integer id = Integer.parseInt(oRequest.getParameter("id"));
                    String sDesc = oRequest.getParameter("desc");
                    oTipousuario = new TipousuarioBean();
                    oTipousuario.setId(id);
                    oTipousuario.setDesc(sDesc);
                    String resultado = oTipousuarioDao.update(oTipousuario);
                    Gson oGson = new Gson();
                    oReplyBean = new ReplyBean(200, oGson.toJson(resultado));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500, "Bad Connection: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}
                
        public ReplyBean getcount() throws Exception {
		ReplyBean oReplyBean;
		ConnectionInterface oConnectionPool = null;
		Connection oConnection ;
		try {
			oConnectionPool = ConnectionFactory.getConnection(ConnectionConstants.connectionPool);
			oConnection = oConnectionPool.newConnection();
			TipousuarioDao oTipousuarioDao=new TipousuarioDao(oConnection);
			int registros = oTipousuarioDao.getcount();
			Gson oGson = new Gson();
			oReplyBean = new ReplyBean(200, oGson.toJson(registros));
		} catch (Exception ex) {
			oReplyBean = new ReplyBean(500, "Bad Connection: " + EncodingHelper.escapeQuotes(EncodingHelper.escapeLine(ex.getMessage())));
		} finally {
			oConnectionPool.disposeConnection();
		}

		return oReplyBean;

	}

}

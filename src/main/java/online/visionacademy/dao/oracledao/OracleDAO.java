package online.visionacademy.dao.oracledao;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.dao.Identifiable;
import online.visionacademy.datasource.ConnectionFactory;
import online.visionacademy.datasource.DataSourceType;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.support.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class OracleDAO<T extends Identifiable,ID> extends AbstractDAO<T,ID> {

    public abstract String getTableName();
    public abstract String [] getColumns();

    @Override
    protected ConnectionFactory getConnectionFactory() {
        return ConnectionFactory.getConnectionFactory(DataSourceType.ORACLE);
    }

    @Override
    protected String getInsertQuery() {
        return QueryBuilder.insert(getTableName(),getColumns());
    }

    @Override
    protected String getSelectByIdQuery() {
        return QueryBuilder.selectById(getTableName(),getColumns());
    }

    @Override
    protected String getSelectAllQuery() {
        return QueryBuilder.selectAll(getTableName(),getColumns());
    }

    @Override
    protected String getSelectAllByIdQuery(List<ID> ids) {
        return QueryBuilder.selectAllById(getTableName(),getColumns(),ids);
    }

    @Override
    protected String getUpdateQuery() {
        return QueryBuilder.update(getTableName(),getColumns());
    }

    @Override
    protected String getDeleteQuery() {
        return QueryBuilder.delete(getTableName(),getColumns()[0]);
    }

    @Override
    protected String getCountQuery() {
        return QueryBuilder.count(getTableName());
    }

    @Override
    protected void setStatementWhereId(PreparedStatement ps, ID id) throws DAOException {

        try {
            ps.setObject(1,id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(),e);
        }

    }

    @Override
    protected void setStatementParas(PreparedStatement ps, List<ID> ids) throws DAOException {

        int index = 1;

        try {

            for (ID id:ids) {
                ps.setObject(index++,id);
            }

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

    }


    @Override
    public T insert(T entity) throws DAOException {
        String insertQuery = getInsertQuery();

        try(Connection connection = getConnectionFactory().createConnection();
            //, new String[] {"transporter_id"}
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"ID"})) {
            setStatementParas(ps,entity);

            if(ps.executeUpdate() < 1){
                throw new DAOException("Could not save the object.");
            }
            setGeneratedKey(ps.getGeneratedKeys(), entity);
        }
        catch (Exception e){
            throw new DAOException(e.getMessage(),e);
        }
        return entity;
    }

    @Override
    protected void setGeneratedKey(ResultSet rs, T entity) throws DAOException {
        try {
            if(rs != null && rs.next()){
                Long id = rs.getLong(1);
                entity.setId(id);
            }
        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
    }

    @Override
    protected String getSelectByColumnQuery(String targetColumn) {
        return QueryBuilder.targetColumn(getTableName(),getColumns(),targetColumn);
    }

    @Override
    protected void setStatementWhereColumn(PreparedStatement ps, String value) throws DAOException {

        try {
            ps.setString(1,value);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(),e);
        }
    }
}

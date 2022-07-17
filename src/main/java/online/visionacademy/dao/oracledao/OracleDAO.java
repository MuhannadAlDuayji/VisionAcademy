package online.visionacademy.dao.oracledao;

import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.datasource.ConnectionFactory;
import online.visionacademy.datasource.DataSourceType;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.support.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class OracleDAO<T,ID> extends AbstractDAO<T,ID> {

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



}

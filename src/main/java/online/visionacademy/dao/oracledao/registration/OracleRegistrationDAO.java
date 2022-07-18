package online.visionacademy.dao.oracledao.registration;

import online.visionacademy.model.Registration;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.support.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleRegistrationDAO extends RegistrationDAO{

    private static final String TABLE_NAME = "Registration";

    private static final String [] COLUMNS = new String[]{"ID","STUDENT_ID","COURSE_ID"};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
    }

    @Override
    protected String getInsertQuery() {
        return QueryBuilder.insert(TABLE_NAME,COLUMNS);
    }

    @Override
    protected String getSelectByIdQuery() {
        return QueryBuilder.selectById(TABLE_NAME,COLUMNS);
    }

    @Override
    protected String getSelectAllQuery() {
        return QueryBuilder.selectAll(TABLE_NAME,COLUMNS);
    }

    @Override
    protected String getSelectAllByIdQuery(List<Long> ids) {
        return QueryBuilder.selectAllById(TABLE_NAME,COLUMNS,ids);
    }

    @Override
    protected String getUpdateQuery() {
        return QueryBuilder.update(TABLE_NAME,COLUMNS);
    }

    @Override
    protected String getDeleteQuery() {
        return QueryBuilder.delete(TABLE_NAME,COLUMNS[0]);
    }

    @Override
    protected String getCountQuery() {
        return QueryBuilder.count(TABLE_NAME);
    }



    @Override
    protected void setStatementParas(PreparedStatement ps, Registration entity) throws DAOException {

        boolean isUpdate = entity.getId() != null;

        try {

        ps.setLong(1,entity.getStudentId());
        ps.setLong(2,entity.getCourseId());

        if(isUpdate)
            ps.setLong(3,entity.getId());

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

    }

    @Override
    protected void setStatementParas(PreparedStatement ps, List<Long> ids) throws DAOException {

        int index = 1;
        try {

            for (Long id:ids)
                ps.setLong(index++,id);

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

    }

    @Override
    protected Registration mapObject(ResultSet rs) throws DAOException {

        Registration registration = new Registration();

        try {

            registration.setId(rs.getLong(COLUMNS[0]));
            registration.setStudentId((rs.getLong(COLUMNS[1])));
            registration.setCourseId(rs.getLong(COLUMNS[2]));

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }


        return registration;
    }

    @Override
    protected List<Registration> mapAllObjects(ResultSet rs) throws DAOException {

        List<Registration> registrationList = new ArrayList<>();

        try {

            while (rs.next()){
                Registration registration = new Registration();

                registration.setId(rs.getLong(COLUMNS[0]));
                registration.setStudentId(rs.getLong(COLUMNS[1]));
                registration.setCourseId(rs.getLong(COLUMNS[2]));
                registrationList.add(registration);

            }

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

        return registrationList;
    }

    @Override
    public List<Registration> findStudentById(Long id) throws DAOException {

        final String query = QueryBuilder.selectAll(TABLE_NAME,COLUMNS)+" WHERE "+COLUMNS[1]+" = ? ";
        return findBy(id,query);
    }

    @Override
    public List<Registration> findCourseById(Long id) throws DAOException {
        final String query = QueryBuilder.selectAll(TABLE_NAME,COLUMNS)+" WHERE "+COLUMNS[2]+" = ? ";

        return findBy(id,query);
    }

    private List<Registration> findBy(Long id, String query) throws DAOException {

        List<Registration> registrationList = new ArrayList<>();

        try (Connection con = getConnectionFactory().createConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs != null){
                registrationList = mapAllObjects(rs);
            }


        }catch (Exception e){
            throw new DAOException(e.getMessage(),e);

        }
        return registrationList;
    }

}

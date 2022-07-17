package org.example.dao.registration;

import org.example.datasource.ConnectionFactory;
import org.example.datasource.DataSourceType;
import org.example.exceptions.DAOException;
import org.example.exceptions.DataSourceException;
import org.example.model.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleRegistrationDAO extends RegistrationDAO{

    private static final String TABLE = "Registration";
    private static final String ID = "ID";
    private static final String STUDENT_ID = "STUDENT_ID";
    private static final String COURSE_ID = "COURSE_ID";

    @Override
    protected ConnectionFactory getConnectionFactory() {
        return ConnectionFactory.getConnectionFactory(DataSourceType.ORACLE);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO "+TABLE
                +" ("+STUDENT_ID+","+COURSE_ID+") VALUES (?,?)";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT "+ID+", "+STUDENT_ID+", "+COURSE_ID
                +" FROM "+TABLE
                +" WHERE "+ID+" = ? ";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT "+ID+", "+STUDENT_ID+", "+COURSE_ID
                +" FROM "+TABLE;
    }

    @Override
    protected String getSelectAllByIdQuery(List<Long> ids) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Long id:ids)
            stringBuilder.append("?,");

        stringBuilder.deleteCharAt(stringBuilder.length()-1);

        return "SELECT "+ID+", "+STUDENT_ID+", "+COURSE_ID
                +" FROM "+TABLE
                +" WHERE ID IN ("+stringBuilder+")";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE "+TABLE
                +" SET "
                +" "+STUDENT_ID+" = ?, "
                +" "+COURSE_ID+" = ? "
                +" WHERE "
                +" "+ID+" = ? ";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM "+TABLE
                +" WHERE "
                +" "+ID+" = ? ";
    }

    @Override
    protected String getCountQuery() {
        return "SELECT COUNT(*) FROM "
                +" "+TABLE;
    }

    @Override
    protected void setStatementWhereId(PreparedStatement ps, Long id) throws DAOException {

        try {
            ps.setLong(1,id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(),e);
        }
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

            registration.setId(rs.getLong(ID));
            registration.setStudentId((rs.getLong(STUDENT_ID)));
            registration.setCourseId(rs.getLong(COURSE_ID));

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

                registration.setId(rs.getLong(ID));
                registration.setStudentId(rs.getLong(STUDENT_ID));
                registration.setCourseId(rs.getLong(COURSE_ID));
                registrationList.add(registration);

            }

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

        return registrationList;
    }

    @Override
    public List<Registration> findStudentById(Long id) throws DAOException {

        final String query = "SELECT "+ID+", "+STUDENT_ID+", "
                +COURSE_ID+" FROM "+TABLE+" WHERE "+STUDENT_ID+" = ? ";
        return findBy(id,query);
    }

    @Override
    public List<Registration> findCourseById(Long id) throws DAOException {
        final String query = "SELECT "+ID+", "+STUDENT_ID+", "
                +COURSE_ID+" FROM "+TABLE+" WHERE "+COURSE_ID+" = ? ";

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
                System.out.println("row : "+rs.getRow());
            }


        }catch (Exception e){
            throw new DAOException(e.getMessage(),e);

        }
        return registrationList;
    }

}

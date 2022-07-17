package online.visionacademy.dao.student;

import online.visionacademy.datasource.ConnectionFactory;
import online.visionacademy.datasource.DataSourceType;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.model.Student;
import online.visionacademy.support.QueryBuilder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleStudentDAO extends StudentDAO{

    private static final String TABLE_NAME = "STUDENT";

    private static final String [] COLUMNS = new String[] {"ID","FIRST_NAME","LAST_NAME","DOB","NATIONAL_ID"};


    @Override
    protected ConnectionFactory getConnectionFactory() {
        return ConnectionFactory.getConnectionFactory(DataSourceType.ORACLE);
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
    protected void setStatementWhereId(PreparedStatement ps, Long id) throws DAOException {

        try {
            ps.setLong(1,id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(),e);
        }

    }

    @Override
    protected void setStatementParas(PreparedStatement ps, Student entity) throws DAOException {

        boolean isUpdate = entity.getId() != null;

        try {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setDate(3, new Date(entity.getDob().toEpochDay()));
            ps.setLong(4, entity.getNationalId());


            if (isUpdate)
                ps.setLong(5, entity.getId());

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

    }

    @Override
    protected void setStatementParas(PreparedStatement ps, List<Long> ids) throws DAOException {

        int index = 1;

        try {

            for (Long id:ids) {
                ps.setLong(index++,id);
            }

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

    }

    @Override
    protected Student mapObject(ResultSet rs) throws DAOException {

        Student student = new Student();

        try {
            student.setId(rs.getLong(COLUMNS[0]));
            student.setFirstName(rs.getString(COLUMNS[1]));
            student.setLastName(rs.getString(COLUMNS[2]));
            student.setDob(rs.getDate(COLUMNS[3]).toLocalDate());
            student.setNationalId(rs.getLong(COLUMNS[4]));
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(),e);
        }


        return student;
    }

    @Override
    protected List<Student> mapAllObjects(ResultSet rs) throws DAOException {
        List<Student> studentList = new ArrayList<>();

        try {

            while (rs.next()){

                Student student = new Student();
                student.setId(rs.getLong(COLUMNS[0]));
                student.setFirstName(rs.getString(COLUMNS[1]));
                student.setLastName(rs.getString(COLUMNS[2]));
                student.setDob(rs.getDate(COLUMNS[3]).toLocalDate());
                student.setNationalId(rs.getLong(COLUMNS[4]));

                studentList.add(student);
            }
        } catch (SQLException e) {
                throw new DAOException(e.getMessage(),e);
        }
        return studentList;
    }

    @Override
    public Student getStudentWithHighestGPA() {
        return null;
    }
}

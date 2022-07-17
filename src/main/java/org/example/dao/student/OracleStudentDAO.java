package org.example.dao.student;

import org.example.datasource.ConnectionFactory;
import org.example.datasource.DataSourceType;
import org.example.exceptions.DAOException;
import org.example.model.Student;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleStudentDAO extends StudentDAO{

    private static final String TABLE_NAME = "STUDENT";

    private static final String id ="ID";
    private static final String firstName = "FIRST_NAME";
    private static final String lastName = "LAST_NAME";
    private static final String dob = "DOB";
    private static final String nationalId = "NATIONAL_ID";

    @Override
    protected ConnectionFactory getConnectionFactory() {
        return ConnectionFactory.getConnectionFactory(DataSourceType.ORACLE);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO "+TABLE_NAME+" "
                +" ( "+nationalId+","
                +" "+firstName+","
                +" "+lastName+","
                +" "+dob+")"
                +" VALUES (?,?,?,?)";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT "+id+","+firstName+","+lastName+","+dob+","+nationalId+" FROM "
                +TABLE_NAME+" WHERE "+ id +" = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT "+id+","+firstName+","+lastName+","+dob+","+nationalId+" FROM "
                +TABLE_NAME;
    }

    @Override
    protected String getSelectAllByIdQuery(List<Long> ids) {
        StringBuilder st = new StringBuilder();

        for(Long id :ids)
            st.append("?,");

        st.deleteCharAt(st.length()-1);

        return "SELECT "+id+","+firstName+","+lastName+","+dob+","+nationalId+" FROM "
                +TABLE_NAME+" WHERE ID IN ("+st.toString()+")";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE "+TABLE_NAME
                +" SET"
                +" "+nationalId+" = ?,"
                +" "+firstName+" = ?,"
                +" "+lastName+" = ?,"
                +" "+dob+" = ?"
                +" WHERE "+id+" = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM "+TABLE_NAME
                +" WHERE "+id+" = ? ";
    }

    @Override
    protected String getCountQuery() {
        return "SELECT COUNT(*) FROM "+TABLE_NAME;
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
            ps.setLong(1, entity.getNationalId());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setDate(4, new Date(entity.getDob().toEpochDay()));

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
            student.setId(rs.getLong(id));
            student.setFirstName(rs.getString(firstName));
            student.setLastName(rs.getString(lastName));
            student.setDob(rs.getDate(dob).toLocalDate());
            student.setNationalId(rs.getLong(nationalId));
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
                student.setId(rs.getLong(id));
                student.setFirstName(rs.getString(firstName));
                student.setLastName(rs.getString(lastName));
                student.setDob(rs.getDate(dob).toLocalDate());
                student.setNationalId(rs.getLong(nationalId));

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

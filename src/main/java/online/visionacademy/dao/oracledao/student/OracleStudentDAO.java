package online.visionacademy.dao.oracledao.student;

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
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        return COLUMNS;
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

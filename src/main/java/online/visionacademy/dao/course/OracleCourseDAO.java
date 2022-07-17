package online.visionacademy.dao.course;

import online.visionacademy.datasource.DataSourceType;
import online.visionacademy.datasource.ConnectionFactory;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.model.Course;
import online.visionacademy.support.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleCourseDAO extends CourseDAO{

    private static final String TABLE_NAME = "COURSE";

    private static final String [] COLUMNS = new String[]{"ID","CODE","NAME","DESCRIPTION"};
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
    protected void setStatementParas(PreparedStatement ps, Course entity) throws DAOException {
        try {
            ps.setString(1, entity.getCode());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getDescription());

            if(entity.getId() != null)
                ps.setLong(4,entity.getId());

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
    protected Course mapObject(ResultSet rs) throws DAOException {
        Course course = new Course();
        try {

            course.setId(rs.getLong(COLUMNS[0]));
            course.setCode(rs.getString(COLUMNS[1]));
            course.setName(rs.getString(COLUMNS[2]));
            course.setDescription(rs.getString(COLUMNS[3]));

        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        
        return course;
    }

    @Override
    protected List<Course> mapAllObjects(ResultSet rs) throws DAOException {

        List<Course> courseList = new ArrayList<>();

        try {
            while (rs.next()){
                Course course = new Course();

                course.setId(rs.getLong(COLUMNS[0]));
                course.setCode(rs.getString(COLUMNS[1]));
                course.setName(rs.getString(COLUMNS[2]));
                course.setDescription(rs.getString(COLUMNS[3]));

                courseList.add(course);
            }
        }catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }

        return courseList;
    }


    @Override
    public String ddf(){
        return "Muhannad";
    }
}

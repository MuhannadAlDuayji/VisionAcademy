package org.example.dao.course;

import org.example.datasource.ConnectionFactory;
import org.example.datasource.DataSourceType;
import org.example.exceptions.DAOException;
import org.example.model.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleCourseDAO extends CourseDAO{

    private static final String TABLE_NAME = "COURSE";
    private static final String ID = "ID";
    private static final String CODE = "code";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    @Override
    protected ConnectionFactory getConnectionFactory() {
        return ConnectionFactory.getConnectionFactory(DataSourceType.ORACLE);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO "+TABLE_NAME+" ("+CODE+","+NAME+","+DESCRIPTION+") " +
                " VALUES (?,?,?) ";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT "+ID+", "+CODE+", "+NAME+", "+DESCRIPTION+" "
                +" FROM "
                +" "+TABLE_NAME
                +" WHERE "+ID+" = ? ";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT "+ID+", "+CODE+", "+NAME+", "+DESCRIPTION+" "
                +" FROM "
                +" "+TABLE_NAME;
    }

    @Override
    protected String getSelectAllByIdQuery(List<Long> longs) {

        StringBuilder st = new StringBuilder();

        for(Long id:longs)
            st.append("?,");
        st.deleteCharAt(st.length()-1);


        return "SELECT "+ID+", "+CODE+", "+NAME+", "+DESCRIPTION+" "
                +" FROM "
                +" "+TABLE_NAME
                +" WHERE ID IN ("+st.toString()+")";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE "+TABLE_NAME
                +" SET "
                +" "+CODE+" = ?, "
                +" "+NAME+" = ?, "
                +" "+DESCRIPTION+" = ? "
                +" WHERE "+ID+" = ? ";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM "+TABLE_NAME
                +" WHERE "+ID+" = ? ";
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

            course.setId(rs.getLong(ID));
            course.setCode(rs.getString(CODE));
            course.setName(rs.getString(NAME));
            course.setDescription(rs.getString(DESCRIPTION));

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

                course.setId(rs.getLong(ID));
                course.setCode(rs.getString(CODE));
                course.setName(rs.getString(NAME));
                course.setDescription(rs.getString(DESCRIPTION));

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

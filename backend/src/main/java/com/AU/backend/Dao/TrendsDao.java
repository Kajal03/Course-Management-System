package com.AU.backend.Dao;

import com.AU.backend.Model.Material;
import com.AU.backend.Model.Skills;
import com.AU.backend.Model.Trends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TrendsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Trends> getTrendingSkills() throws Exception
    {
        try {
            List<Trends> trends = this.jdbcTemplate.query("select count(*), skillName from skillCourseMapping natural join skills group by skills.skillName;", this::mapRowToTrends);
            return trends;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Trends> getCourseRating() throws Exception
    {
        try {
            List<Trends> trends = this.jdbcTemplate.query("select avg(rating), courseName from feedbacks natural join course group by courseId;", this::mapRowToTrends);
            return trends;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    private Trends mapRowToTrends(ResultSet resultSet, int rowNumber) throws SQLException {
        Trends trends = new Trends();
        trends.setName(resultSet.getString(2));
        trends.setValue(resultSet.getFloat(1));
        return  trends;
    }

}

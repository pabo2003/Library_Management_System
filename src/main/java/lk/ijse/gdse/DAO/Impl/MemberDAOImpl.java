package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.MemberDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.Entity.Members;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public boolean save(Members members) throws SQLException {
        return SQLUtil.execute("INSERT INTO members VALUES(?,?,?,?,?)",members.getMemberId(),members.getName(),members.getEmail(),members.getPhoneNumber(),members.getAddress(),members.getDateOfBirth(),members.getJoinDate(),members.getMembershipType());
    }

    @Override
    public ArrayList<Members> getAll() throws SQLException {
        ArrayList<Members> members = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT*FROM members");
        while (rst.next()){
            Members member = new Members(
                    rst.getInt("memberId"),
                    rst.getString("name"),
                    rst.getString("email"),
                    rst.getString("phoneNumber"),
                    rst.getString("address"),
                    rst.getDate("dateOfBirth"),
                    rst.getDate("joinDate"),
                    rst.getString("membershipType")
            );
            members.add(member);
        }
        return members;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return SQLUtil.execute("DELETE FROM members WHERE member_id = ?",id);
    }

    @Override
    public boolean update(Members members) throws SQLException {
        return SQLUtil.execute("UPDATE members SET name = ? , email = ? , phone_number = ? , address = ?, date_of_birth = ?, join_date = ?, membership_type = ? WHERE member_id = ?",members.getName(),members.getEmail(),members.getPhoneNumber(),members.getAddress(),members.getDateOfBirth(),members.getJoinDate(),members.getMembershipType());
    }

    @Override
    public Members searchByTel(String tel) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM members WHERE phone_number = ?",tel +"");
        rst.next();
        return new Members(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getDate(6),rst.getDate(7),rst.getString(8) );
    }

    @Override
    public List<String> getTel() throws SQLException {
        List<String> telList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT phone_number FROM members")) {
            while (rst.next()) {
                String tel = rst.getString("phone_number");
                telList.add(tel);
            }
        }
        return telList;
    }

    @Override
    public String getCurrentId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT member_id FROM members ORDER BY member_id DESC LIMIT 1");
        if(rst.next()) {
            String memberId = rst.getString(1);
            return memberId;
        }
        return null;
    }
}

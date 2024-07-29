package lk.ijse.gdse.BO;

import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.DTO.MembersDTO;
import lk.ijse.gdse.Entity.Members;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MemberBO extends SuperBO{
    public boolean save(MembersDTO members) throws SQLException;

    public ArrayList<MembersDTO> getAll() throws SQLException;

    public boolean delete(int id) throws SQLException;

    public boolean update(MembersDTO members) throws SQLException;

    public Members searchByTel(String tel) throws SQLException;

    public List<String> getTel() throws SQLException;

    public int getCurrentId() throws SQLException;
}
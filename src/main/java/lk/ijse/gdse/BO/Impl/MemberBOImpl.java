package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.MemberBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.MemberDAO;
import lk.ijse.gdse.DTO.MembersDTO;
import lk.ijse.gdse.Entity.Members;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);
    @Override
    public boolean save(MembersDTO members) throws SQLException {
        return memberDAO.save(new Members(members.getMember_id(), members.getName(), members.getEmail(),members.getPhone_number(), members.getAddress(),members.getDate_of_birth(),members.getJoin_date(),members.getMembership_type()));
    }

    @Override
    public ArrayList<MembersDTO> getAll() throws SQLException {
        ArrayList<MembersDTO> allMembers= new ArrayList<>();
        ArrayList<Members> all = memberDAO.getAll();
        for (Members m : all) {
            allMembers.add(new MembersDTO(
                    m.getMemberId(),
                    m.getName(),
                    m.getEmail(),
                    m.getPhoneNumber(),
                    m.getAddress(),
                    (Date) m.getDateOfBirth(),
                    (Date) m.getJoinDate(),
                    m.getMembershipType()));
        }
        return allMembers;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return memberDAO.delete(id);
    }

    @Override
    public boolean update(MembersDTO members) throws SQLException {
        return memberDAO.update(new Members(members.getMember_id(),members.getName(),members.getEmail(), members.getPhone_number(), members.getAddress(), members.getDate_of_birth(), members.getJoin_date(), members.getMembership_type()));
    }

    @Override
    public Members searchByTel(String tel) throws SQLException {
        return (Members) memberDAO.searchByTel(tel);
    }

    @Override
    public List<String> getTel() throws SQLException {
        return memberDAO.getTel();
    }

    @Override
    public int getCurrentId() throws SQLException {
        return memberDAO.getCurrentId();
    }
}
//BorrowingTransactionsFormController
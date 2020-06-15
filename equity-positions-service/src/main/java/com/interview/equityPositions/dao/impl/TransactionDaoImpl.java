package com.interview.equityPositions.dao.impl;

import com.interview.equityPositions.dao.TransactionDao;
import com.interview.equityPositions.model.Position;
import com.interview.equityPositions.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> queryAll() {
        String sql = "select * from t_transaction";
        return jdbcTemplate.query(sql, new RowMapper<Transaction>() {
            @Override
            public Transaction mapRow(ResultSet rs, int arg) throws SQLException {
                Transaction t = new Transaction();
                t.setTransactionID(rs.getInt("TransactionID"));
                t.setTradeID(rs.getInt("TradeID"));
                t.setVersion(rs.getInt("Version"));
                t.setSecurityCode(rs.getString("SecurityCode"));
                t.setQuantity(rs.getInt("Quantity"));
                t.setTradeType(rs.getString("TradeType"));
                t.setBuyOrSell(rs.getString("BuyOrSell"));
                return t;
            }
        });
    }

    /**
     * output Position
     * @return
     */
    @Override
    public List<Position> queryPosition() {
        String sql = "select \n" +
                "\tSecurityCode,\n" +
                "\tsum(Quantity) Quantity\n" +
                "from (\n" +
                "\tselect\n" +
                "\t\tSecurityCode,\n" +
                "\t\tcase when b.BuyOrSell = 'Sell' then -Quantity else Quantity end Quantity\n" +
                "\tfrom (\n" +
                "\t\tselect\n" +
                "\t\t\tTradeID,\n" +
                "\t\t\tmax(Version) Version\n" +
                "\t\tfrom t_transaction \n" +
                "\t\tgroup by TradeID\n" +
                "\t) a join t_transaction b on a.TradeID = b.TradeID and a.Version = b.Version and b.TradeType != 'CANCEL'\n" +
                ") c \n" +
                "group by SecurityCode";
        return jdbcTemplate.query(sql, new RowMapper<Position>() {
            @Override
            public Position mapRow(ResultSet rs, int arg) throws SQLException {
                Position p = new Position();
                p.setSecurityCode(rs.getString("SecurityCode"));
                p.setQuantity(rs.getInt("Quantity"));
                return p;
            }
        });
    }

    @Override
    public int insert(Transaction transaction) {
        String insertSql = "insert into t_transaction(TradeID,Version,SecurityCode,Quantity,TradeType,BuyOrSell)" +
                " values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(insertSql,
                    transaction.getTradeID(),
                    transaction.getVersion(),
                    transaction.getSecurityCode(),
                    transaction.getQuantity(),
                    transaction.getTradeType(),
                    transaction.getBuyOrSell()
                );
    }

    @Override
    public int update(Transaction transaction) {
        String updateSql = "update t_transaction set " +
                "Version = ?, " +
                "SecurityCode = ?, " +
                "Quantity = ?, " +
                "TradeType = ?, " +
                "BuyOrSell = ? " +
                "where TransactionID = ?";
        return jdbcTemplate.update(updateSql,
                transaction.getVersion(),
                transaction.getSecurityCode(),
                transaction.getQuantity(),
                transaction.getTradeType(),
                transaction.getBuyOrSell(),
                transaction.getTransactionID()
        );
    }

    @Override
    public Transaction queryByTransactionID(int TransactionID) {
        String sql = "select * from t_transaction where TransactionID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{TransactionID}, new RowMapper<Transaction>() {
            @Override
            public Transaction mapRow(ResultSet rs, int arg) throws SQLException {
                Transaction t = new Transaction();
                t.setTransactionID(rs.getInt("TransactionID"));
                t.setTradeID(rs.getInt("TradeID"));
                t.setVersion(rs.getInt("Version"));
                t.setSecurityCode(rs.getString("SecurityCode"));
                t.setQuantity(rs.getInt("Quantity"));
                t.setTradeType(rs.getString("TradeType"));
                t.setBuyOrSell(rs.getString("BuyOrSell"));
                return t;
            }
        });
    }

}

package edu.br.log;

import edu.br.log.dao.ConnectionFactory;
import edu.br.log.view.ConsoleView;

import java.sql.SQLException;

public class Log {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory.getConnection();
        ConsoleView cv = new ConsoleView();
        cv.inicar();
    }
}

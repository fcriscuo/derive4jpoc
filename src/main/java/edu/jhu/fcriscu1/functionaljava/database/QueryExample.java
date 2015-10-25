package edu.jhu.fcriscu1.functionaljava.database;

import fj.F;
import fj.control.db.DB;
import fj.control.db.DbState;
import fj.data.Option;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Created by fcriscuo on 10/19/15.
 */
public class QueryExample {

    private static final Logger logger = Logger.getLogger(QueryExample.class);
    private static final String url = "jdbc:mysql://localhost:3306/cgds_mskimpact?user=criscuof&password=fred3372";
    public static void main(String...args) {
        Option<Connection> connOpt = ConnectionService.INSTANCE.getCbioConnection();
        // DbState encapsulates information about how to get connections.
        DbState state = DbState.reader(DbState.driverManager(url));
        String sql = "select hugo_gene_symbol from cgds_mskimpact";
        if (connOpt.isSome()){
            // A simple DB combinator:
            // for a query that a reader might perform, i might have a function like this:



        }
    }

}

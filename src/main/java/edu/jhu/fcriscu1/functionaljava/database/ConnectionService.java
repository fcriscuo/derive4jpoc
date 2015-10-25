package edu.jhu.fcriscu1.functionaljava.database;

import fj.data.Option;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Copyright (c) 2015 Memorial Sloan-Kettering Cancer Center.
 * <p>
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 * documentation provided hereunder is on an "as is" basis, and
 * Memorial Sloan-Kettering Cancer Center
 * has no obligations to provide maintenance, support,
 * updates, enhancements or modifications.  In no event shall
 * Memorial Sloan-Kettering Cancer Center
 * be liable to any party for direct, indirect, special,
 * incidental or consequential damages, including lost profits, arising
 * out of the use of this software and its documentation, even if
 * Memorial Sloan-Kettering Cancer Center
 * has been advised of the possibility of such damage.
 * <p>
 * Created by Fred Criscuolo on 8/5/15.
 * criscuof@mskcc.org
 */
public enum ConnectionService
{

    INSTANCE;
    private static final Logger logger = Logger.getLogger(ConnectionService.class);

    public Option<Connection> getCbioConnection (){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to locate MySQL JDBC Driver?");
            e.printStackTrace();
            return Option.none();
        }
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/cgds_mskimpact", "criscuof", "fred3372");
            return Option.some(connection);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return Option.none();
        }
    }

    public static void main (String...args){
        Option<Connection> connOpt = ConnectionService.INSTANCE.getCbioConnection();
        if(connOpt.isSome()){
            logger.info("Obtained connection to local cbio");
        }  else {
            logger.info("Failed to connect to local cbio");
        }
    }
}

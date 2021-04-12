import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.List;

public class SqlBuilder extends SqlCommands{

    public SqlBuilder(String jdbcDriver, String url, String username, String passworld){
        super(jdbcDriver, url, username,passworld);
    }

    public String sqlStringBuilder(List<Object> list, String queryType) {
        String sqlQuery="";
        if (String.valueOf(queryType).toUpperCase() == "INSERT") {
            insert(list);
        } else if (String.valueOf(queryType).toUpperCase() == "UPDATE") {
            sqlQuery=update(list);
        }
        return sqlQuery;
    }

    private void insert(List<Object> list,String tableName) {
        String sqlQuery="INSERT INTO "+tableName+" VALUES (";
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1)
                    sqlQuery = sqlQuery + "?,";
                else
                    sqlQuery = sqlQuery + "?);";
            }
        }
             insertStm(list,sqlQuery,tableName);
    }

    private String update(Dictionary dictionary,String tableName)
    {
       String sqlString = "UPDATE "+tableName+" set ";
        for (int i=0; i<dictionary.size();i++ ) {
            sqlString=sqlString+" "+dictionary.get
        }

        return "";
    }

    public void updateSettings(Settings settings, String idValue) {
        System.out.println("update: " + settings.getAktualis_honap());
        String sqlQuery = "update settings set "
                + "nev= '" + settings.getNev() + "',"
                + "varos='" + settings.getVaros() + "',"
                + "cim='" + settings.getCim() + "',"
                + "auto='" + settings.getAuto() + "',"
                + "rendszam='" + settings.getRendszam() + "',"
                + "loketterfogat='" + settings.getLoketterfogat() + "',"
                + "fogyasztas='" + settings.getFogyasztas() + "',"
                + "elozo_zaro=" + settings.getElozo_zaro() + ","
                + "aktualis_honap='" + settings.getAktualis_honap() + "',"
                + "utolso_ugyfel ='" + settings.getUtolso_ugyfel() + "',"
                + "zaro_km ='" + settings.getZaroKm() + "',"
                + "utolso_szerkesztes='" + LocalDateTime.now().toString() + "',"
                + "active='" + settings.getActive() + "'"
                + " where id = '" + idValue + "';";
        System.out.println(sqlQuery);
        try {
            // System.out.println(sqlQuery);
            preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}



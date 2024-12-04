import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;

public class ConnectXML {
    private String host;

    private String port;

    private String user;

    private String password;

    private String database;



    public ConnectXML(String config_file) {
        File file = new File(config_file);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            host = getTagContent(doc, "host");
            port = getTagContent(doc, "port");
            user = getTagContent(doc, "user");
            password = getTagContent(doc, "password");
            database = getTagContent(doc, "database");

            if (host == null || port == null || user == null || password == null || database == null) {
                throw new IllegalArgumentException("El archivo de configuración está incompleto o malformado.");
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getTagContent(Document doc, String tagName) {
        if (doc.getElementsByTagName(tagName).getLength() > 0) {
            return doc.getElementsByTagName(tagName).item(0).getTextContent();
        }
        return null;
    }


    public Connection dbConnect(){
        Connection connection = null;
        try {
            connection = java.sql.DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+database, user, password);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return connection;
    }

}
//private String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/sports";
//    private String username = "postgres";
//    private String password = "1710"
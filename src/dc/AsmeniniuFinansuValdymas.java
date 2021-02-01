package dc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AsmeniniuFinansuValdymas implements Serializable {

    private ArrayList<Kategorija> kategorijos = new ArrayList();
    private ArrayList<Islaidos> islaidos = new ArrayList();
    private ArrayList<Pajamos> pajamos = new ArrayList();
    Connection conn;
    Statement stmt;

    public boolean pridetiKategorija(String pav, String aprasas) {
        try {
            this.connectToDb();
            String statement = "INSERT INTO asmeniniufinansuvaldymas.kategorija (Pavadinimas, Aprasymas) VALUES ('" + pav + "', '" + aprasas + "')";
            stmt.execute(statement);
            this.disconnectFromDb();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double gautiPajamuBalansa() {
        double pajamusuma = 0;
        for (Pajamos p : pajamos) {
            pajamusuma += p.getsuma();
        }
        return pajamusuma;
    }

    public double gautiIslaiduBalansa() {
        double islaidusuma = 0;
        for (Islaidos f : islaidos) {
            islaidusuma += f.getsuma();
        }
        return islaidusuma;
    }

    public double gautiKategorijosIslaidas(String reikiama_kategorija) {
        double reikiamos_kategorijos_islaidos = 0;
        Kategorija kk = new Kategorija(reikiama_kategorija, null);
        if (kategorijos.contains(kk)) {
            for (Islaidos k : islaidos) {
                if (reikiama_kategorija.equals(k.getkategorija())) {
                    System.out.println(k.toString());
                    reikiamos_kategorijos_islaidos += k.getsuma();
                }

            }
            System.out.println(reikiama_kategorija + " kategorijos islaidos " + reikiamos_kategorijos_islaidos);
        } else {
            System.out.println("Tokia kategorija neegzistuoja");
        }
        return reikiamos_kategorijos_islaidos;
    }

    public double gautiKategorijosPajamas(String reikiama_kategorija) {
        double reikiamos_kategorijos_pajamos = 0;
        Kategorija kk = new Kategorija(reikiama_kategorija, null);
        if (kategorijos.contains(kk)) {
            for (Pajamos s : pajamos) {
                if (reikiama_kategorija.equals(s.getkategorija())) {
                    System.out.println(s.toString());
                    reikiamos_kategorijos_pajamos += s.getsuma();
                }

            }
            System.out.println(reikiama_kategorija + " kategorijos pajamos " + reikiamos_kategorijos_pajamos);
        } else {
            System.out.println("Tokia kategorija neegzistuoja");
        }
        return reikiamos_kategorijos_pajamos;
    }

    public double gautiBalansa(Date nuo, Date iki) {
        return 0.0;
    }

    public boolean pridetiIslaida(double suma, Date data, String pavadinimas, String kategorija, String komentaras,
            String numeris) {

        Kategorija cat = rastiKategorijaPagalPavadinima(kategorija);
        if (cat == null) {
            return false;
        } else {
            try {
                this.connectToDb();

                String pattern = "MM/dd/yyyy HH:mm:ss";

                DateFormat df = new SimpleDateFormat(pattern);

                Date today = Calendar.getInstance().getTime();

                String date = df.format(today);

                String statement = "INSERT INTO asmeniniufinansuvaldymas.islaidos (kategorija, komentaras, date, suma, numeris, pavadinimas ) VALUES ('"
                        + kategorija + "', '" + komentaras + "', '" + date + "', '" + suma + "' , '" + numeris + "' , '" + pavadinimas + "')";
                stmt.execute(statement);
                this.disconnectFromDb();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean pridetiPajamas(double suma, Date data, String pavadinimas, String kategorija, String komentaras) {
        Kategorija cat = rastiKategorijaPagalPavadinima(kategorija);
        if (cat == null) {
            return false;
        } else {
            try {
                this.connectToDb();

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);

                String statement = "INSERT INTO asmeniniufinansuvaldymas.pajamos (kategorija, pavadinimas, strDate, komentaras, suma) VALUES ('"
                        + kategorija + "', '" + pavadinimas + "', '" + strDate + "', '" + komentaras + "', '" + suma + "')";
                stmt.execute(statement);
                this.disconnectFromDb();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean salintiKategorija(String kategorijosPavadinimas) {
        try {
            this.connectToDb();
            String statement = "DELETE FROM kategorija WHERE Pavadinimas = '" + kategorijosPavadinimas + "'";
            stmt.execute(statement);
            this.disconnectFromDb();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean keistiKategorijosPavadinimas(String kokiaKategorija, String naujasPavadinimas) {
        try {
            this.connectToDb();
            String statement = "UPDATE kategorija SET Pavadinimas = '" + naujasPavadinimas + "' WHERE Pavadinimas = '" + kokiaKategorija + "'";
            stmt.execute(statement);
            this.disconnectFromDb();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Kategorija> gautiKategorijuSarasa() {
        kategorijos = new ArrayList();
        try {
            this.connectToDb();
            String statement = "SELECT * FROM kategorija";
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                String kategorijaPav = rs.getString("Pavadinimas"); //rs.getString(1);
                String desc = rs.getString("Aprasymas"); //rs.getString(2);
                kategorijos.add(new Kategorija(kategorijaPav, desc));
            }
            rs.close();
            this.disconnectFromDb();
        } catch (Exception e) {
            System.out.println("Klaida gaunant kategoriju sarasa");
            e.printStackTrace();
        }
        return kategorijos;
    }

    public ArrayList<Islaidos> gautiIslaiduSarasa() {

        islaidos = new ArrayList();
        try {
            this.connectToDb();
            String statement = "SELECT * FROM islaidos";
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                String kategorijaPav = rs.getString("kategorija"); //rs.getString(1);
                String komentaras = rs.getString("komentaras");
                String data = rs.getString("date");
                Double suma = rs.getDouble("suma");
                String numeris = rs.getString("numeris");
                String pavadinimas = rs.getString("pavadinimas");//rs.getString(2);
                islaidos.add(new Islaidos(suma,data,pavadinimas,kategorijaPav,komentaras,numeris));
            }
            rs.close();
            this.disconnectFromDb();
        } catch (Exception e) {
            System.out.println("Klaida gaunant kategoriju sarasa");
            e.printStackTrace();
        }
        return islaidos;

    }

    public ArrayList<Pajamos> gautiPajamuSarasa() {
       
        
        pajamos = new ArrayList();
        try {
            this.connectToDb();
            String statement = "SELECT * FROM pajamos";
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                String kategorijaPav = rs.getString("kategorija"); //rs.getString(1);
                String komentaras = rs.getString("komentaras");
                String data = rs.getString("strDate");
                Double suma = rs.getDouble("suma");
                String pavadinimas = rs.getString("pavadinimas");//rs.getString(2);
                pajamos.add(new Pajamos(suma,data,pavadinimas,kategorijaPav,komentaras));
            }
            rs.close();
            this.disconnectFromDb();
        } catch (Exception e) {
            System.out.println("Klaida gaunant kategoriju sarasa");
            e.printStackTrace();
        }
        return pajamos;
        
    }

    public void connectToDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost/asmeniniufinansuvaldymas";
            String USER = "root";
            String PSW = "";
            conn = DriverManager.getConnection(DB_URL, USER, PSW);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnectFromDb() {
        if (stmt != null && conn != null)
            try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Kategorija rastiKategorijaPagalPavadinima(String pav) {
        for (Kategorija k : kategorijos) {
            if (k.getpavadinimas().equals(pav)) {
                return k;
            }
        }
        return null;
    }
}

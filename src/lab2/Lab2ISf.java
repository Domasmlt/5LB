package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dc.AsmeniniuFinansuValdymas;
import dc.Islaidos;
import dc.Kategorija;
import dc.Pajamos;

public class Lab2ISf {

    public static void main(String[] args) throws Exception {

        AsmeniniuFinansuValdymas abc = new AsmeniniuFinansuValdymas();
        Scanner sc = new Scanner(System.in);

        // ArrayList<Kategorija> kat = abc.gautiKategorijuSarasa();
        // ArrayList<Islaidos> isl = abc.gautiIslaiduSarasa();
        // ArrayList<Pajamos> prp = abc.gautiPajamuSarasa();
        String command = "";

        while (!command.equals("f")) {
            System.out.println("Choose from list: \n" + "\tc - category \n" + "\te - expense\n" + "\ti - income\n"
                    + "\tb - balance\n" + "\tf - finish");

            command = sc.next();
            System.out.println("You have chosen option: " + command);

            switch (command) {
                case "c":
                    categoryControl(sc, abc);
                    break;
                case "e":
                    expenseControl(sc, abc);
                    break;
                case "i":
                    incomeControl(sc, abc);
                    break;
                case "b":
                    balanceControl(sc, abc);
                    break;
                case "f":
                    System.out.println("Programa baige darba");
                    break;
            }
        }

    }

    private static void categoryControl(Scanner sc, AsmeniniuFinansuValdymas abc) {

        String line = "";

        System.out.println("Choose from category option list: \n" + "\tprint - Print \n" + "\tadd - Add\n"
                + "\tdel - Delete\n" + "\tedit - Edit\n" + "\tsave - save to file\n" + "\tload -load from file\n"
                + "\tquit - Quit\n");

        line = sc.next().trim();

        switch (line.toLowerCase()) {

            case "print":
                int no = 1;
                ArrayList<Kategorija> list = abc.gautiKategorijuSarasa();
                for (Kategorija k : list) {
                    System.out.printf("%3d. %s\n", no, k.toString());
                    no++;
                }
                break;

            case "add":
                System.out.println("Add category name");
                String catName = sc.next();
                System.out.println("Add category description");
                String catDesc = sc.next();
                abc.pridetiKategorija(catName, catDesc);
                break;

            case "del":
                System.out.println("Remove category name");
                String catNameRem = sc.next();
                abc.salintiKategorija(catNameRem);
                break;

            case "edit":
                int no1 = 1;
                ArrayList<Kategorija> listAdjustment = abc.gautiKategorijuSarasa();
                System.out.println("Iveskite kokios kategorijos pavadinima norit pakeisti:");
                String pav = sc.next();
                System.out.println("Iveskite i koki pavadinima norit ji pakeisti:");
                String naujaspav = sc.next();
                abc.keistiKategorijosPavadinimas(pav, naujaspav);
                System.out.println("Pavadinimas pakeistas");
                for (Kategorija k : listAdjustment) {
                    System.out.printf("%3d. %s\n", no1, k.toString());
                    no1++;
                }
                break;

            case "save":
                ArrayList<Kategorija> listToSave = abc.gautiKategorijuSarasa();
                try {
                    FileWriter file = new FileWriter(new File("data.txt"));
                    for (Kategorija k : listToSave) {
                        file.write(k.toString());
                    }
                    file.close();
                } catch (Exception e) {
                    System.out.println("Unable to perform operation");
                }
                break;

            case "load":
                String failas = "C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\2Lab\\kategorijos.txt";
                Scanner s = null;
                String pirmaEilute = null;
                try {
                    s = new Scanner(new File(failas));
                    String pjm = null;
                    while (s.hasNext()) {
                        pirmaEilute = s.nextLine();
                        if (pirmaEilute.trim().endsWith(":")) {
                            pjm = pirmaEilute;

                        } else {
                            String[] duom = pirmaEilute.split(",");
                            String pava = duom[0];
                            String aprasymas = duom[1];
                            abc.pridetiKategorija(pava, aprasymas);
                        }
                    }
                } catch (Exception klaida) {
                    System.out.println("Klaida");
                    klaida.printStackTrace();
                } finally {
                    if (s != null) {
                        s.close();
                        System.out.println("Kategorijos pridetos");
                    }
                }
                break;

            case "quit":

                break;

            default:
                System.out.println("Invalid value");
                break;

        }

    }

    private static void expenseControl(Scanner sc, AsmeniniuFinansuValdymas abc) {
        String line = "";

        System.out.println("Choose from expense option list: \n" + "\tprintall - Printall \n" + "\tprintn - printn \n"
                + "\tadd - Add\n" + "\tsave - save to file\n" + "\tload -load from file\n" + "\tquit - Quit\n");

        line = sc.next().trim();

        switch (line.toLowerCase()) {

            case "printall":
                int no = 1;
                ArrayList<Islaidos> list = abc.gautiIslaiduSarasa();
                for (Islaidos k : list) {
                    System.out.printf("%3d. %s\n", no, k.toString());
                    no++;
                }
                break;

            case "add":
                System.out.println("Iveskite suma:");
                double suma = 0;
                suma = sc.nextDouble();

                System.out.println("Iveskite pavadinima:");
                String pavadinimas = sc.next();

                System.out.println("Iveskite kategorija:");
                String kategorija = sc.next();

                System.out.println("Iveskite komentara:");
                String kom = sc.next();

                System.out.println("Iveskite cekio numery:");
                String numeris = sc.next();
                abc.pridetiIslaida(suma, new Date(), pavadinimas, kategorija, kom, numeris);
                System.out.println("Islaida prideta");
                break;

            case "printn":
                System.out.println("Iveskite kurios kategorijos islaidas norite pamatyti:");
                String isl_kategorija = sc.next();
                abc.gautiKategorijosIslaidas(isl_kategorija);
                break;

            case "edit":
                int no1 = 1;
                ArrayList<Kategorija> listAdjustment = abc.gautiKategorijuSarasa();
                System.out.println("Iveskite kokios kategorijos pavadinima norit pakeisti:");
                String pava = sc.next();
                System.out.println("Iveskite i koki pavadinima norit ji pakeisti:");
                String naujaspav = sc.next();
                abc.keistiKategorijosPavadinimas(pava, naujaspav);
                System.out.println("Pavadinimas pakeistas");
                for (Kategorija k : listAdjustment) {
                    System.out.printf("%3d. %s\n", no1, k.toString());
                    no1++;
                }
                break;

            case "load":

                // FileReader fr = new FileReader("src/Pajamos.txt");
                String failas = "C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\2Lab\\islaidos.txt";
                Scanner s = null;
                String pirmaEilute = null;
                try {
                    s = new Scanner(new File(failas));
                    String pjm = null;
                    while (s.hasNext()) {
                        pirmaEilute = s.nextLine();
                        if (pirmaEilute.trim().endsWith(":")) {
                            pjm = pirmaEilute;

                        } else {
                            String[] duom = pirmaEilute.split(",");
                            Double sum = Double.parseDouble(duom[0]);
                            String pav = duom[1];
                            String is_kategorija = duom[2];
                            String komentaras = duom[3];
                            String num = duom[4];
                            abc.pridetiIslaida(sum, new Date(), pav, is_kategorija, komentaras, num);
                        }
                    }
                } catch (Exception klaida) {
                    System.out.println("Klaida");
                    klaida.printStackTrace();
                } finally {
                    if (s != null) {
                        s.close();
                        System.out.println("Islaidos pridetos");
                    }
                }

                break;

            case "save":
                ArrayList<Islaidos> listToSave = abc.gautiIslaiduSarasa();
                try {
                    FileWriter file = new FileWriter(new File("islaidusarasas.txt"));
                    for (Islaidos k : listToSave) {
                        file.write(k.toString());
                    }
                    file.close();
                } catch (Exception e) {
                    System.out.println("Unable to perform operation");
                }
                break;

            case "quit":
                break;

            default:
                System.out.println("Invalid value");
                break;

        }
    }

    private static void incomeControl(Scanner sc, AsmeniniuFinansuValdymas abc) {
        String line = "";

        System.out.println("Choose from income option list: \n" + "\tprintall - Printall \n" + "\tprintn - printn \n"
                + "\tadd - Add\n" + "\tsave - save to file\n" + "\tload -load from file\n" + "\tquit - Quit\n");

        line = sc.next().trim();

        switch (line.toLowerCase()) {

            case "printall":
                int no = 1;
                ArrayList<Pajamos> list = abc.gautiPajamuSarasa();
                for (Pajamos k : list) {
                    System.out.printf("%3d. %s\n", no, k.toString());
                    no++;
                }
                break;

            case "add":
                System.out.println("Iveskite suma:");
                double suma = 0;
                suma = sc.nextDouble();

                System.out.println("Iveskite pavadinima:");
                String pavadinimas = sc.next();

                System.out.println("Iveskite kategorija:");
                String kategorija = sc.next();

                System.out.println("Iveskite komentara:");
                String kom = sc.next();

                abc.pridetiPajamas(suma, new Date(), pavadinimas, kategorija, kom);
                System.out.println("Pajamos pridetos");
                break;

            case "printn":
                System.out.println("Iveskite kurios kategorijos pajamas norite pamatyti:");
                String pjm_kategorija = sc.next();
                abc.gautiKategorijosPajamas(pjm_kategorija);
                break;

            case "edit":
                int no1 = 1;
                ArrayList<Kategorija> listAdjustment = abc.gautiKategorijuSarasa();
                System.out.println("Iveskite kokios kategorijos pavadinima norit pakeisti:");
                String pava = sc.next();
                System.out.println("Iveskite i koki pavadinima norit ji pakeisti:");
                String naujaspav = sc.next();
                abc.keistiKategorijosPavadinimas(pava, naujaspav);
                System.out.println("Pavadinimas pakeistas");
                for (Kategorija k : listAdjustment) {
                    System.out.printf("%3d. %s\n", no1, k.toString());
                    no1++;
                }
                break;

            case "load":

                String failas = "C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\2Lab\\pajamos.txt";
                Scanner s = null;
                String pirmaEilute = null;
                try {
                    s = new Scanner(new File(failas));
                    String pjm = null;
                    while (s.hasNext()) {
                        pirmaEilute = s.nextLine();
                        if (pirmaEilute.trim().endsWith(":")) {
                            pjm = pirmaEilute;

                        } else {
                            String[] duom = pirmaEilute.split(",");
                            Double sum = Double.parseDouble(duom[0]);
                            String pav = duom[1];
                            String is_kategorija = duom[2];
                            String komentaras = duom[3];
                            abc.pridetiPajamas(sum, new Date(), pav, is_kategorija, komentaras);
                        }
                    }
                } catch (Exception klaida) {
                    System.out.println("Klaida");
                    klaida.printStackTrace();
                } finally {
                    if (s != null) {
                        s.close();
                        System.out.println("Pajamos pridetos");
                    }
                }

                break;

            case "save":
                ArrayList<Pajamos> listToSave = abc.gautiPajamuSarasa();
                try {
                    FileWriter file = new FileWriter(new File("pajamusarasas.txt"));
                    for (Pajamos k : listToSave) {
                        file.write(k.toString());
                    }
                    file.close();
                } catch (Exception e) {
                    System.out.println("Unable to perform operation");
                }
                break;

            case "quit":
                break;

            default:
                System.out.println("Invalid value");
                break;

        }
    }

    private static void balanceControl(Scanner sc, AsmeniniuFinansuValdymas abc) {

        String line = "";

        System.out.println("Choose from balance option list: \n" + "\tislaidu - Islaidu balansas \n"
                + "\tpajamu - Pajamu balansas \n" + "\tbendras - Bendras sistemos balansas \n"
                + "\tissaugot - Issaugoti pajamas, islaidas ir kategorijas\n"
                + "\tikeltiv - Ikelti kategorijas, pajamas ir islaidas\n" + "\tquit - Iseiti\n");

        line = sc.next().trim();

        switch (line.toLowerCase()) {

            case "islaidu":
                System.out.println("Islaidos: " + abc.gautiIslaiduBalansa());
                break;

            case "pajamu":
                System.out.println("Pajamos: " + abc.gautiPajamuBalansa());
                break;

            case "bendras":
                System.out.println("Bendras sistemos balansas: ");
                System.out.println(abc.gautiPajamuBalansa() + abc.gautiIslaiduBalansa());
                break;

            case "issaugot":
                ArrayList<Kategorija> kat = abc.gautiKategorijuSarasa();
                ArrayList<Islaidos> isl = abc.gautiIslaiduSarasa();
                ArrayList<Pajamos> prp = abc.gautiPajamuSarasa();

                try {
                    FileWriter file = new FileWriter(new File("databendras.txt"));
                    for (Pajamos k : prp) {
                        file.write(k.toString());
                    }
                    for (Islaidos k : isl) {
                        file.write(k.toString());
                    }
                    for (Kategorija k : kat) {
                        file.write(k.toString());
                    }
                    file.close();
                } catch (Exception e) {
                    System.out.println("Unable to perform operation");
                }

                break;

            case "ikeltiv":

                String failas = "C:\\Users\\Dell\\OneDrive\\Documents\\NetBeansProjects\\2Lab\\Visiduomenys.txt";
                Scanner s = null;
                String pirmaEilute = null;
                try {
                    s = new Scanner(new File(failas));
                    String temp = null;
                    String kt = "Kategorijos:";
                    String pj = "Pajamos:";
                    String is = "Islaidos:";
                    while (s.hasNext()) {
                        pirmaEilute = s.nextLine();
                        if (pirmaEilute.trim().endsWith(":")) {
                            temp = pirmaEilute;
                        } else {
                            if (temp.contentEquals(kt)) {
                                String[] duom = pirmaEilute.split(",");
                                String pava = duom[0];
                                String aprasymas = duom[1];
                                abc.pridetiKategorija(pava, aprasymas);
                            } else if (temp.contentEquals(pj)) {
                                String[] duom = pirmaEilute.split(",");
                                Double sum = Double.parseDouble(duom[0]);
                                String pav = duom[1];
                                String is_kategorija = duom[2];
                                String komentaras = duom[3];
                                abc.pridetiPajamas(sum, new Date(), pav, is_kategorija, komentaras);
                            } else {
                                String[] duom = pirmaEilute.split(",");
                                Double sum = Double.parseDouble(duom[0]);
                                String pav = duom[1];
                                String is_kategorija = duom[2];
                                String komentaras = duom[3];
                                String num = duom[4];
                                abc.pridetiIslaida(sum, new Date(), pav, is_kategorija, komentaras, num);
                            }
                        }
                    }
                } catch (Exception klaida) {
                    System.out.println("Klaida");
                    klaida.printStackTrace();
                } finally {
                    if (s != null) {
                        s.close();
                        System.out.println("Duomenys ikelti");
                    }
                }

                break;

            case "quit":

                break;

        }

    }

}

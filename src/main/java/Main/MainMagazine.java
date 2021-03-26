package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entities.*;


public class MainMagazine {

    public static void main(String[] args)  {
        ArrayList<Revista> revistes = new ArrayList();
        FileAccessor fa;


        Menu menu = new Menu();
        int opcio = -1;

        while (opcio != 0) {

            opcio = menu.menuPral();

            switch (opcio) {

                case 1:

                    System.out.println("1!!");
                    fa = new FileAccessor();
                    try {
                        fa.readAutorsFile("src/main/java/docs/autors.txt");
                        fa.printAutors();
                        fa.readMagazinesFile("src/main/java/docs/revistes.txt");
                        fa.printRevistes();
                        revistes = fa.readArticlesFile("src/main/java/docs/articles.txt");
                        mostraRevistes(revistes);
                    } catch (NumberFormatException | IOException e) {

                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("2!!");
                    System.out.println("SELECCIÓ DE REVISTA");

                    Revista revista = seleccionaRevista(revistes);
                    if (revista != null) {
                        System.out.println(revista.toString());
                    }

                    Article article = null;
                    if (revista != null) {
                        article = seleccionaArticle(revista);
                    }

                    if (article != null) {
                        System.out.println(article.toString());
                    }

                    break;

                default:
                    System.out.println("Adeu!!");
                    System.exit(1);
                    break;

            }
        }
    }
    public static void mostraRevistes(ArrayList<Revista> revistes){

        for (int i = 0; i < revistes.size(); i++) {

            System.out.println(revistes.get(i).toString());
            for (int j = 0; j < revistes.get(i).getArticles().size(); j++) {
                System.out.println("\t"+ revistes.get(i).getArticle(j).toString());
                System.out.println("\t\t"+revistes.get(i).getArticle(j).getAutor().toString());
            }

        }
    }

    public static Revista seleccionaRevista(ArrayList<Revista> revistes){
        Scanner sc = new Scanner(System.in);

        mostraRevistes(revistes);
        System.out.println("Selecciona un id de revista");
        int id = sc.nextInt();


        for (Revista r : revistes ) {
            if (id == r.getId_revista()){
                return r;
            }
        }

        return null;

    }
    public static Article seleccionaArticle(Revista revista){
        Scanner sc = new Scanner(System.in);
        String articles = revista.getArticles().toString();

        System.out.println(articles);
        System.out.println("Selecciona un id d'article");

        int id = sc.nextInt();

        for (Article a: revista.getArticles()) {
            if (id == a.getId_article())
                return a;
        }

        return null;

    }



}

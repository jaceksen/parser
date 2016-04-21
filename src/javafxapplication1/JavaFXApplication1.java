/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author jsen
 * test 1
 */
public class JavaFXApplication1 extends Application implements IntParser {
    
    
    @Override
    public void start(Stage primaryStage) {
        
        //Label
        Label wynik = new Label();
        Label info1 = new Label("Program używa pliku wejściowego: WCC_DATA.csv");
        Label info2 = new Label("Parametry należy umieścić w pliku: WCC_PARAMETERS.txt");
        Label info3 = new Label("Plik wynikowy to: WCC_DATA_PARSED.txt");
        Label info4 = new Label("Podgląd pierwszych 100 wierszy pliku wynikowego:");
        
        String uzytkownik = System.getProperty("user.name");
        
        Label info5 = new Label("Aktualnie zalogowany: " + uzytkownik);
        
        //zamazanie
        double ilezamazac = 10;
        BoxBlur zamaz = new BoxBlur(1.0,1.0,1);
    
        
        //TextArea
        TextArea podglad = new TextArea();
        
        //przycisk
        Button btn = new Button();
        btn.setText("CSV do WCC");
        
        Button btn1 = new Button();
        btn1.setText("WCC do CSV");
        
        Button btn2 = new Button();
        btn2.setText("Wyczyść");
        
        HBox hb = new HBox(btn, btn1, btn2);
        hb.setSpacing(10);
        //hb.setPadding(new Insets(20));
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    //System.out.println("Plik przerobiony.");
                    //Sprawdzam ile jest wyrażeń aby stworzyć odpowiednią tablicę
                   
         
                   
                    
                    
                    //Pobieram parametry do tablicy
                    //String tablica[] = new String[100];
                    int liczba_wierszy = 0;
                    int ile_wyswietlac = 100;
                    String parametr;
                    String tablica[] = new String[100];
                    String str_podglad = "";
                    Scanner params = new Scanner(new File("WCC_PARAMETERS.txt"));
                    params.useDelimiter(",");
                    int k=0;
                    while (params.hasNext()) {
                        parametr = params.next().replaceAll("[\n\r]", "");
                        tablica[k] = parametr;
                        k++;
                    }
                    params.close();
                    
                    
                    Scanner scanner = new Scanner(new File("WCC_DATA.csv"));
                    PrintWriter writer = new PrintWriter("WCC_DATA_PARSED.txt", "UTF-8");
                    
                    //String tablica[] = {"FileName=", "FilePar2=", "FilePar3="};
                    int i = 0;
                    String aktualny;
                    
                    //scanner.useDelimiter("\\|\\|"); 
                    scanner.useDelimiter("\\|");
                    
                    //wpisuję pierwszą linię Action
                    writer.println("Action = update");
                    
                    while (scanner.hasNext())
                    {                                      
                        //if(scanner.next() == "\n") scanner.next().replace("\n", "");
                        //if(scanner.next() == "\n") continue;
                        aktualny = scanner.next().replaceAll("[\n\r]", "");
                        //aktualny = scanner.next();
                        
                        //if(aktualny == "") continue;
                        
                        //writer.println(tablica[i]+scanner.next().trim()); 
                        
                        if(tablica[i] != null) {
                        //writer.println(tablica[i]+aktualny+" i: " + i + " tab.length: " + tablica.length);
                        writer.println(tablica[i]+aktualny);
                        liczba_wierszy++;
                            if(liczba_wierszy < ile_wyswietlac){
                            str_podglad += tablica[i]+aktualny+"\n";
                            podglad.setText(str_podglad);
                            }
                        }
                        else
                        {
                        //writer.println(tablica[0]+aktualny+" i: " + i + " tab.length: " + tablica.length + "po else"); 
                        writer.println("<<EOD>>");
                        liczba_wierszy++;
                        if(liczba_wierszy < ile_wyswietlac) {
                            str_podglad += "<<EOD>>\n";
                            podglad.setText(str_podglad);
                            }
                        writer.println(tablica[0]+aktualny);
                        liczba_wierszy++;
                        if(liczba_wierszy < ile_wyswietlac) {
                            str_podglad += tablica[0]+aktualny+"\n";
                            podglad.setText(str_podglad);
                            }
                        }
                        
                        if(tablica[i] == null) i = 0;
                 
                         i++;
                 
                        //if(i == tablica.length -1) i=-1;
                        //writer.println(tablica[1]+scanner.next());
                        //writer.println(tablica[2]+scanner.next());
                        //scanner.next().replace("\n", "");
                        //System.out.print("FileName="+scanner.next()+"\n");
                        //System.out.print("FilePar2="+scanner.next()+"\n");
                        //System.out.print("FilePar3="+scanner.next()+"\n");
       
                    }
                    writer.println("<<EOD>>");
                    scanner.close();
                    writer.close();
                    wynik.setText("Plik wynikowy został wygenerowany"); 
                    //btn.setEffect(zamaz);
                    //zamaz.setWidth(ilezamazac);
                    //zamaz.setHeight(ilezamazac);
                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    Logger.getLogger(JavaFXApplication1.class.getName()).log(Level.SEVERE, null, ex);
                    podglad.setText("Coś poszło nie tak ... :-( \n\n"+
                    "Sprawdź czy w katalogu są pliki WCC_DATA.csv i WCC_PARAMETERS.csv\n"+
                    "Format WCC_PARAMETERS.csv to: NazwaPola1=,NazwaPola2=,NazwaPola3=\n"+
                    "Pola w pliku WCC_DATA.csv powinny być rozdzielane znakiem: \"|\"\n"+        
                    "Gdy już wszystko zawiedzie, zwróć się do autora:\nJacek Seń\n"+
                     "(jacek.sen@mir.gdynia.pl)");
                    
                }
            }
        });
        
        
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event1) {
           
                try 
                    {
                    //Plik z którego czytam
                    Scanner dane_z_wcc = new Scanner(new File("WCC_DATA.txt"));
                    //Plik do którego piszę
                    PrintWriter plik_csv = new PrintWriter("WCC.csv", "UTF-8");
                    
                    String wiersz;
                    
                    dane_z_wcc.useDelimiter("\n");
                    while (dane_z_wcc.hasNext())
                        {     
                        wiersz = dane_z_wcc.next().replaceAll("[\n\r]", "|");
                        
                        //jeżeli wiersz zawiera <EOD> to go kasuję i zmaiast niego wstawiam \n
                        if(wiersz.contains("<EOD>"))
                            {
                            plik_csv.println();
                            continue;
                            }
                        
                        //jeżeli wiersz zawiera wpis "Action =" to go pomijam
                        if(wiersz.contains("Action =" )) continue;
                        
                        //Zapisuję wiersz do pliku wynikowego
                        plik_csv.print(wiersz);                 
                        }
                    //sprzątam
                    dane_z_wcc.close();
                    plik_csv.close();
                    wynik.setText("Plik wynikowy został wygenerowany"); 
                    }                 
                catch (FileNotFoundException | UnsupportedEncodingException ex) 
                    {
                    Logger.getLogger(JavaFXApplication1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                
                
            }
        });
        
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event2) {
                podglad.setText("");
                wynik.setText("");
            }
        });
        
        //StackPane root = new StackPane();
        FlowPane root = new FlowPane(Orientation.VERTICAL,20,10);
        root.setAlignment(Pos.CENTER);
        
        //root.getChildren().add(btn);
        //root.getChildren().addAll(btn, btn1, info1, info2, info3, info4, podglad, wynik, info5);
         root.getChildren().addAll(hb, info1, info2, info3, info4, podglad, wynik, info5);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("PARSER - Repozytrium Dokumentów MIR-PIB");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

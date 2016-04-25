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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 *
 * @author jsen test 1
 */
public class JavaFXApplication1 extends Application implements IntParser {

    public String komunikat_o_bledzie() {
        String komunikat;
        komunikat = "Coś poszło nie tak ... :-( \n\n"
                + "Format WCC_PARAMETERY.txt to: NazwaPola1=,NazwaPola2=,NazwaPola3=\n"
                + "Pola w pliku wejściowym CSV powinny być rozdzielane znakiem: \"|\"\n"
                + "Gdy już wszystko zawiedzie, zwróć się do autora:\nJacek Seń\n"
                + "(jacek.sen@mir.gdynia.pl)";
        return komunikat;
    }

    @Override
    public void start(Stage primaryStage) {

        //Label
        Label wynik = new Label();
        Label info1 = new Label("PLIK WYNIKOWY: ");
        //Label info2 = new Label("Parametry należy umieścić w pliku: WCC_PARAMETERS.txt");
        Label info4 = new Label("Podgląd pierwszych 100 wierszy pliku wynikowego:");

        String uzytkownik = System.getProperty("user.name");

        Label info5 = new Label("Aktualnie zalogowany: " + uzytkownik);

        //zamazanie
        double ilezamazac = 10;
        BoxBlur zamaz = new BoxBlur(1.0, 1.0, 1);
        
        //radio buttons
        ToggleGroup group = new ToggleGroup();
        
        RadioButton insradio = new RadioButton("insert");
        insradio.setToggleGroup(group);
        
        RadioButton updradio = new RadioButton("update");
        updradio.setSelected(true);
        updradio.setToggleGroup(group);
        
        RadioButton delradio = new RadioButton("delete");
        delradio.setToggleGroup(group);
        
        //HBox na przyciski
        HBox radiobuttons = new HBox(insradio, updradio, delradio);
        radiobuttons.setSpacing(10);
        

        //Pole tekstowe - plik wynikowy
        TextField nazwa_pliku_wyn = new TextField("WCC_WYNIK");
        nazwa_pliku_wyn.setPromptText("Wpisz nazwę pliku wynikowego...");
        nazwa_pliku_wyn.setPrefWidth(200);
        //nazwa_pliku_wyn.setPrefColumnCount(20);

        //Pole tekstowe - plik wejściowy
        TextField nazwa_pliku_wej = new TextField();
        nazwa_pliku_wej.setPromptText("Wybierz plik wejściowy...");
        nazwa_pliku_wej.setPrefWidth(400);
        
        //przycisk wybierz plik wejściowy
        Button btn_plik_wej = new Button();
        btn_plik_wej.setText("Wybierz plik wejściowy");
        
        //Pole tekstowe - plik parametrów
        TextField nazwa_pliku_param = new TextField("WCC_PARAMETRY.txt");
        nazwa_pliku_param.setPromptText("Wybierz plik parametrów...");
        nazwa_pliku_param.setPrefWidth(400);
        
        //przycisk plik parametrów
        Button btn_plik_param = new Button();
        btn_plik_param.setText("Wybierz plik parametrów");




        //TextArea
        TextArea podglad = new TextArea();

        //przycisk
        Button btn = new Button();
        btn.setText("CSV do WCC");

        Button btn1 = new Button();
        btn1.setText("WCC do CSV");

        Button btn2 = new Button();
        btn2.setText("Wyczyść");

        //HBox na przyciski
        HBox hb = new HBox(btn, btn1, btn2);
        hb.setSpacing(10);
        //hb.setPadding(new Insets(20)); 

        //HBox na wybranie pliku wejściowego
        HBox hb_file_in = new HBox(nazwa_pliku_wej, btn_plik_wej);
        hb_file_in.setSpacing(10);
        
        //HBox na wybranie pliku parametrów
        HBox hb_param_file = new HBox(nazwa_pliku_param, btn_plik_param);
        hb_param_file.setSpacing(10);
        
        //Hbox plik wynikowy
        HBox hb_wyn = new HBox(info1, nazwa_pliku_wyn);
        hb_param_file.setSpacing(10);
        
        
    
        
      
        
        
        //przycisk plik parametrów
        btn_plik_param.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc1 = new FileChooser();
                File wybrany_plik1 = fc1.showOpenDialog(null);

                if (wybrany_plik1 != null) {
                    String plwej1;
                    plwej1 = wybrany_plik1.getName();
                    //System.out.println(plwej);
                    nazwa_pliku_param.setText(wybrany_plik1.getAbsolutePath());
                }
            }
        }
        );

        //przycisk plik wejściowy
        btn_plik_wej.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                File wybrany_plik = fc.showOpenDialog(null);

                if (wybrany_plik != null) {
                    String plwej;
                    plwej = wybrany_plik.getName();
                    //System.out.println(plwej);
                    nazwa_pliku_wej.setText(wybrany_plik.getAbsolutePath());
                }
            }
        }
        );
        
        
        /*
          group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                        @Override
                        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                            System.out.println(t1);
                            System.out.println(group.selectedToggleProperty().toString());
                        }
                    });

          */                         
        
        
        
        // przycisk SCV do WCC
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    //System.out.println("Plik przerobiony.");
                    //Sprawdzam ile jest wyrażeń aby stworzyć odpowiednią tablicę

                    String plik_wejsciowy = nazwa_pliku_wej.getText();
                    String plik_wynikowy = nazwa_pliku_wyn.getText()+".txt";
                    String plik_parametrow = nazwa_pliku_param.getText();
                    
                    
                      
        

                    //Pobieram parametry do tablicy
                    //String tablica[] = new String[100];
                    int liczba_wierszy = 0;
                    int ile_wyswietlac = 100;
                    String parametr;
                    String tablica[] = new String[100];
                    String str_podglad = "";
                    Scanner params = new Scanner(new File(plik_parametrow));
                    params.useDelimiter(",");
                    int k = 0;
                    while (params.hasNext()) {
                        parametr = params.next().replaceAll("[\n\r]", "");
                        tablica[k] = parametr;
                        k++;
                    }
                    params.close();

                    //Scanner scanner = new Scanner(new File("WCC_DATA.csv"));
                    Scanner scanner = new Scanner(new File(plik_wejsciowy));
                    //PrintWriter writer = new PrintWriter("WCC_DATA_PARSED.txt", "UTF-8");
                    PrintWriter writer = new PrintWriter(plik_wynikowy, "UTF-8");

                    //String tablica[] = {"FileName=", "FilePar2=", "FilePar3="};
                    int i = 0;
                    String aktualny;
                    String wybrany_radio = "";

                    //scanner.useDelimiter("\\|\\|"); 
                    scanner.useDelimiter("\\|");

                    //wpisuję pierwszą linię Action
                    //str_podglad += "Action = update\n";
                    if(insradio.isSelected()) wybrany_radio="insert";
                    if(updradio.isSelected()) wybrany_radio="update";
                    if(delradio.isSelected()) wybrany_radio="delete";
                    //str_podglad += (group.selectedToggleProperty().toString());
                    str_podglad += "Action = " + wybrany_radio + "\n"; 
                    writer.println("Action = update");

                    while (scanner.hasNext()) {
                        //if(scanner.next() == "\n") scanner.next().replace("\n", "");
                        //if(scanner.next() == "\n") continue;
                        aktualny = scanner.next().replaceAll("[\n\r]", "");
                        //aktualny = scanner.next();

                        //if(aktualny == "") continue;
                        //writer.println(tablica[i]+scanner.next().trim()); 
                        if (tablica[i] != null) {
                            //writer.println(tablica[i]+aktualny+" i: " + i + " tab.length: " + tablica.length);
                            writer.println(tablica[i] + aktualny);
                            liczba_wierszy++;
                            if (liczba_wierszy < ile_wyswietlac) {
                                str_podglad += tablica[i] + aktualny + "\n";
                                podglad.setText(str_podglad);
                            }
                        } else {
                            //writer.println(tablica[0]+aktualny+" i: " + i + " tab.length: " + tablica.length + "po else"); 
                            writer.println("<<EOD>>");
                            liczba_wierszy++;
                            if (liczba_wierszy < ile_wyswietlac) {
                                str_podglad += "<<EOD>>\n";
                                podglad.setText(str_podglad);
                            }
                            writer.println(tablica[0] + aktualny);
                            liczba_wierszy++;
                            if (liczba_wierszy < ile_wyswietlac) {
                                str_podglad += tablica[0] + aktualny + "\n";
                                podglad.setText(str_podglad);
                            }
                        }

                        if (tablica[i] == null) {
                            i = 0;
                        }

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
                    str_podglad += "<<EOD>>\n";
                    scanner.close();
                    writer.close();
                    wynik.setText("Plik wynikowy został wygenerowany");
                    //btn.setEffect(zamaz);
                    //zamaz.setWidth(ilezamazac);
                    //zamaz.setHeight(ilezamazac);
                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    Logger.getLogger(JavaFXApplication1.class.getName()).log(Level.SEVERE, null, ex);
                    podglad.setText(komunikat_o_bledzie());

                }
            }
        });
        
        
        //przycisk WCC do CSV
        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event1) {

                try {

                    String plik_wynikowy = nazwa_pliku_wyn.getText()+".csv";

                    //Plik z którego czytam
                    String plik_wejsciowy = nazwa_pliku_wej.getText();
                    //Scanner dane_z_wcc = new Scanner(new File("WCC_DATA.txt"));
                    Scanner dane_z_wcc = new Scanner(new File(plik_wejsciowy));
                    //Plik do którego piszę
                    //PrintWriter plik_csv = new PrintWriter("WCC.csv", "UTF-8");
                    PrintWriter plik_csv = new PrintWriter(plik_wynikowy, "UTF-8");

                    int liczba_werszy = 0;
                    String str_podglad = "";
                    int ile_wyswietlac = 100;

                    String wiersz;
                    String wiersz_przyciety;
                    int poz1;

                    dane_z_wcc.useDelimiter("\n");
                    while (dane_z_wcc.hasNext()) {
                        wiersz = dane_z_wcc.next().replaceAll("[\n\r]", "|");

                        //jeżeli wiersz zawiera <EOD> to go kasuję i zmaiast niego wstawiam \n
                        if (wiersz.contains("<EOD>")) {
                            liczba_werszy++;
                            plik_csv.println();
                            str_podglad += "\n";
                            podglad.setText(str_podglad);
                            continue;
                        }

                        //jeżeli wiersz zawiera wpis "Action =" to go pomijam
                        if (wiersz.contains("Action =")) {
                            continue;
                        }

                        //sprawdzam jaka jest pozycja "=" i wywalam wszystko co było przed nią
                        poz1 = wiersz.indexOf("=");
                        wiersz_przyciety = "";
                        wiersz_przyciety = wiersz.substring(poz1 + 1);

                        //Zapisuję wiersz do pliku wynikowego
                        if (liczba_werszy < ile_wyswietlac) {
                            wiersz_przyciety = wiersz_przyciety+"|";
                            str_podglad += wiersz_przyciety;
                            plik_csv.print(wiersz_przyciety);
                            podglad.setText(str_podglad);
                        }
                    }
                    //wyświetlam podgląd
                    podglad.setText(str_podglad);
                    //sprzątam
                    dane_z_wcc.close();
                    plik_csv.close();
                    wynik.setText("Plik wynikowy został wygenerowany");
                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    Logger.getLogger(JavaFXApplication1.class.getName()).log(Level.SEVERE, null, ex);
                    podglad.setText(komunikat_o_bledzie());
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
        FlowPane root = new FlowPane(Orientation.VERTICAL, 20, 10);
        root.setAlignment(Pos.CENTER);

        //root.getChildren().add(btn);
        //root.getChildren().addAll(btn, btn1, info1, info2, info3, info4, podglad, wynik, info5);
        root.getChildren().addAll(hb, radiobuttons, hb_file_in, hb_param_file, hb_wyn, info4, podglad, wynik, info5);

        Scene scene = new Scene(root, 600, 440);

        primaryStage.setTitle("PARSER - Repozytrium Dokumentów MIR-PIB");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }

}

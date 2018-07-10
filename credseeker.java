// involves 3 ways to get the credentials using JSoup

package com.company;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Document doc = null;

        Random rand = new Random();
        StringBuilder result = new StringBuilder();
        String url = "http://mysu.sabanciuniv.edu/apps/wireless/guest/index.php";
        int namelength = 4;
        String inputname = "";
        String phonenumber = JOptionPane.showInputDialog("Enter phone number in following format: 5554443322");

        if (phonenumber.length() != 10 || phonenumber.equals(null)) {
            JOptionPane.showMessageDialog(null, "Invalid input, try again");
        } else {
            for (int i = 0; i < namelength; i++)
                inputname += (char) (97 + rand.nextInt(25));
            // input name ready

            try {

                System.out.println(inputname);
                System.out.println(phonenumber);

                doc = Jsoup.connect(url)
                        .data("guestname", inputname)
                        .data("phone1", "+90")
                        .data("phone2", phonenumber)
                        .data("sponsor", "-")
                        .data("Submit", "Submit")
                        .post();

                // if everything went fine, the document should have this text somewhere on the page
                if (doc.html().contains("You will get")) {
                    JOptionPane.showMessageDialog(null, "You will get an SMS including the login info.");
                } else {
                    // if the text doesn't appear, there's an error
                    JOptionPane.showMessageDialog(null, "Action couldn't be completed. Check internet connection" +
                            " and provided phone number.");
                }
                /*
                Connection.Response res = Jsoup.connect(url)
                        .data("guestname", inputname)
                        .data("phone1", "+90")
                        .data("phone2", phonenumber)
                        .data("sponsor", "-")
                        .data("Submit", "Submit")
                        .method(Connection.Method.POST)
                        .execute();

                Document doc2 = res.parse();

                System.out.println(doc2.html());

                */

                /*
                doc = Jsoup.connect(url).get();

                Elements gname = doc.select("#guestname");
                Elements phone = doc.select("#phone2");
                Elements submit = doc.select("#Submit");

                gname.attr("value", inputname);
                phone.attr("value", phonenumber);
                submit.attr("value", "Submit");
                */

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("IO problem.");
            }
        }

    }
}

package Poker.io;

import Poker.components.Game;
import Poker.components.Player;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// It would be smarter and more efficient to just implement a web server
// this would allow to have an interactive app using ajax or other real time
// framework that allows for real time page updates without refreshing

public class HtmlUserInterface implements UserInterface<HtmlUserInterface> {

    private String roundToHtml(Game<HtmlUserInterface> game) {
        StringBuilder htmlOut = new StringBuilder();
        List<Player> players = game.getPlayers();

        htmlOut.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Round</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            background-color: black;\n" +
                "        }\n" +
                "        .game-container {\n" +
                "            display: flex;\n" +
                "            flex-flow: column nowrap;\n" +
                "            align-items: center;\n" +
                "        }\n" +
                "\n" +
                "        .row-container {\n" +
                "            flex: 1 1 auto;\n" +
                "            display: flex;\n" +
                "            flex-flow: row nowrap;\n" +
                "            justify-content: space-around;\n" +
                "            align-content: center;\n" +
                "        }\n" +
                "\n" +
                "        .player-container {\n" +
                "            flex: 1 1 auto;\n" +
                "            display: flex;\n" +
                "            flex-flow: column nowrap;\n" +
                "            align-content: center;\n" +
                "            min-width: 30%;\n" +
                "            max-width: 30%;\n" +
                "        }\n" +
                "\n" +
                "        .cards-container {\n" +
                "            flex: 0 1 auto;" +
                "            display: flex;\n" +
                "            flex-flow: row nowrap;\n" +
                "            \n" +
                "        }\n" +
                "\n" +
                "        .winner-container {\n" +
                "            flex: 1 1 auto;\n" +
                "            min-width: 40%;\n" +
                "            max-width: 40%;\n" +
                "\n" +
                "            display: flex;\n" +
                "            flex-flow: row nowrap;\n" +
                "            align-items: center;\n" +
                "            justify-content: center;\n" +
                "            \n" +
                "        }\n" +
                "\n" +
                "        .winner-container > p{\n" +
                "            color: white;\n" +
                "            font-size: 1.9em;\n" +
                "            font-family: sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        .header > p {\n" +
                "            text-align: center;\n" +
                "            font-size: 1.5em;\n" +
                "            font-family: sans-serif;\n" +
                "            color: white;\n" +
                "            margin: 0.1em;\n" +
                "        }\n" +
                "\n" +
                "        .card {\n" +
                "            flex: 1 1 auto;\n" +
                "\n" +
                "            display: flex;\n" +
                "            flex-flow: column nowrap;\n" +
                "        }\n" +
                "\n" +
                "        .card > img {\n" +
                "            max-width:100%;\n" +
                "        }\n" +
                "        .card > p {\n" +
                "            text-align: center;\n" +
                "            font-size: 1em;\n" +
                "            font-family: sans-serif;\n" +
                "            margin: 0;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        .big-text {\n" +
                "            text-align: center;\n" +
                "            font-size: 1.7em;\n" +
                "            font-family: sans-serif;\n" +
                "            color: white;\n" +
                "            margin: 0.2em;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"game-container\">");
        htmlOut.append(players.get(0).toHtml());
        htmlOut.append("\n        <div class=\"row-container middle-row-container\">\n");
        htmlOut.append(players.get(1).toHtml());
        htmlOut.append("\n<div class=\"winner-container\">\n" + "                <p>Winner: ");
        htmlOut.append(game.calcWinner().getName()).append("</p>\n");
        htmlOut.append("            </div>\n\n");
        htmlOut.append(players.get(2).toHtml());
        htmlOut.append("        </div>\n");
        htmlOut.append(players.get(3).toHtml());
        htmlOut.append("    </div>\n" +
                "</body>\n" +
                "</html>\n");

        return htmlOut.toString();
    }

    @Override
    public void update(Game<HtmlUserInterface> game) {
        System.out.println("Round played, the output will soon pop up on your screen!");
        try {
            final String tempDir = System.getProperty("java.io.tmpdir");
            final String fileName = "htmlOutput.html";
            final String rawHtmlData = this.roundToHtml(game);

            FileWriter htmlOut = new FileWriter(tempDir + fileName);
            File htmlData = new File(tempDir + fileName);

            htmlOut.append(rawHtmlData);

            Desktop.getDesktop().browse(htmlData.toURI());

            htmlOut.close();
            System.out.println("This round is done.");
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void gameEnd() {
        System.out.println("Game has ended!");
    }

    @Override
    public boolean getAction() {
        System.out.println("Do you want to play another round? \n[y] -- yes \n[any key] -- no");
        return new Scanner(System.in).nextLine().equals("y");
    }
}

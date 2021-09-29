package duke;

import duke.ui.PrintBot;
import duke.util.ActionBot;
import duke.util.Storage;
import duke.util.TaskList;


import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Duke {
    private TaskList tasks;
    private PrintBot ui;
    private Storage storage;


    public Duke (String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList();
            ui = new PrintBot();
        } catch (IOException e) {
            e.printStackTrace();
            //update loading error
        }
    }


    public void run() {
        ActionBot parser = new ActionBot(ui, tasks);
        parser.Activation();

        storage.loadData(tasks);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.startsWith("bye")) {
            parser.identifyInput(input);
            storage.saveData(tasks);

            input = in.nextLine();
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

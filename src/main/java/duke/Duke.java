package duke;

import duke.exception.*;
import duke.task.*;
import duke.ui.PrintBot;
import duke.util.ActionBot;
import duke.util.Alarm;
import duke.util.Storage;


import java.io.IOException;
import java.util.Scanner;


import static duke.ui.ErrorReport.*;

public class Duke {
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static ActionBot duke = new ActionBot();
    public static PrintBot print = new PrintBot();
    public static Storage storage;

    static {
        try {
            storage = new Storage();
        } catch (IOException e) {
            PrintBot.print("Can't access storage");
        }
    }
    //work on that later, storage constructor throws exception

    public static void identifyInput (String[] userInput) {
        String taskType = userInput[0];

        print.line();
        switch (taskType) {
        case "hello":
            print.hello();
            break;
        case"todo":
            try {
                Todo t = duke.addTodo(userInput[1]);
                print.addTask(t);
                storage.saveData();
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_TODO);
            } catch (IOException e) {
                alarm(Alarm.SAVE_ERROR);
            }
            break;
        case "deadline":
            try {
                Deadline d = duke.addDeadline(userInput[1], "/by");
                print.addTask(d);
                storage.saveData();
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DEADLINE);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_DDL_KEYWORD);
            } catch (IOException e) {
                alarm(Alarm.SAVE_ERROR);
            }
            break;
        case "event":
            try {
                Event e = duke.addEvent(userInput[1],"/at");
                print.addTask(e);
                storage.saveData();
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_EVENT);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_DDL_KEYWORD);
            } catch (IOException e) {
                alarm(Alarm.SAVE_ERROR);
            }
            break;
        case "list":
            print.printList();
            break;
        case "done":
            try {
                int id = Integer.parseInt(userInput[1]);
                duke.markDone(id, true);
                print.markDone(id, true);
                storage.saveData();
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DONE);
            } catch (DukeException e) {
                alarm(Alarm.OUT_OF_RANGE);
            } catch (NumberFormatException e) {
                alarm(Alarm.INVALID_ID);
            } catch (IOException e) {
                alarm(Alarm.SAVE_ERROR);
            }
            break;
        default:
            alarm(Alarm.INVALID_COMMAND);
            break;
        }
        print.line();
    }

    public static void main(String[] args) {
        print.greet();

        storage.loadData();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();


        while (!input.startsWith("bye")) {
            String cleanInput = input.trim();
            String[] userInput = cleanInput.split(" ",2);

            identifyInput(userInput);
            input = in.nextLine();
        }
        print.exit();
    }
}

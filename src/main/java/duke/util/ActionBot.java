package duke.util;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.exception.NoKeywordException;
import duke.task.*;
import duke.ui.ErrorReport;
import duke.ui.PrintBot;


import java.util.Random;
import java.util.Scanner;

import static duke.ui.ErrorReport.alarm;

/* This class manipulates the task list in the
 * way required by the user.
 */
public class ActionBot {

    static final int DESCRIPTION = 0;
    static final int TIME = 1;

    protected TaskList tasks;
    protected PrintBot ui;

    public ActionBot (PrintBot ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }


    public void Activation() {
        System.out.println("Repeat after me : \n " +
                "I solemnly swear that I am up to no good.");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        int n = 0;
        while (notActivated(input)) {
            if (n == 4) {
                n = 0;
            }
            ui.printFalseCharmMsg(n);
            n++;
            input = in.nextLine();
        }
        ui.greet();
    }
    public boolean notActivated(String input) {
        return !input.equals("I solemnly swear that I am up to no good.");
    }


    public static String[] getDetails(String taskInput, String keyword) {
        String[] details = taskInput.split(keyword,2);

        details[1] = details[1].replace(keyword, "");

        String[] cleanDetails = new String[2];
        cleanDetails[0] = details[0].trim();
        cleanDetails[1] = details[1].trim();
        return cleanDetails;
    }

    public String[] getCommand (String in) {
        String clean = in.trim();
        return clean.split(" ", 2);
    }

    public void identifyInput(String original) {
        String[] userInput = getCommand(original);
        String taskType = userInput[0];

        ui.line();
        switch (taskType) {
        case "hello":
            ui.hello();
            break;
        case"todo":
            try {
                Todo t = tasks.addTodo(userInput[1]);
                ui.addTask(t, tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_TODO);
            }
            break;
        case "deadline":
            try {
                String[] details = getDetails(userInput[1], "/by");
                Deadline d = tasks.addDeadline(details[DESCRIPTION], details[TIME]);
                ui.addTask(d, tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DEADLINE);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_DDL_KEYWORD);
            }
            break;
        case "event":
            try {
                String[] details = getDetails(userInput[1], "/at");
                Event e = tasks.addEvent(details[DESCRIPTION], details[TIME]);
                ui.addTask(e, tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_EVENT);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_DDL_KEYWORD);
            }
            break;
        case "list":
            try {
                tasks.printList();
            } catch (EmptyListException e) {
                alarm(Alarm.EMPTY_LIST);
            }
            break;
        case "done":
            try {
                int id = Integer.parseInt(userInput[1]);
                Task t = tasks.markDone(id, true);
                ui.markDone(id, true, t);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_INDEX);
            } catch (InvalidIndexException e) {
                ErrorReport.outOfRangeException(tasks.getSize());
            } catch (NumberFormatException e) {
                alarm(Alarm.INVALID_ID);
            } catch (EmptyListException e) {
                alarm(Alarm.EMPTY_LIST);
            }
            break;
        case "delete":
            try {
                int id = Integer.parseInt(userInput[1]);
                Task t = tasks.delete(id);
                ui.delete(t, tasks.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_INDEX);
            } catch (InvalidIndexException e) {
                ErrorReport.outOfRangeException(tasks.getSize());
            } catch (NumberFormatException e) {
                alarm(Alarm.INVALID_ID);
            } catch (EmptyListException e) {
                alarm(Alarm.EMPTY_LIST);
            }
            break;
        default:
            alarm(Alarm.INVALID_COMMAND);
            break;
        }
        ui.line();
    }
}

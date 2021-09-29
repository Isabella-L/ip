package duke.ui;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.task.*;

import java.util.Random;


/*
 * This class prints the results of manipulation
 * from ActionBot to the CLI User Interface.
 */
public class PrintBot {
    static final String LINE = "--------------------------------";

    public static void print(String input) {
        System.out.println(input);
    }

    public void line() {
        print(LINE);
    }

    public void greet() {
        String logo = "  _                                         _   _            \n" +
                " | |__   __ _ _ __ _ __ _   _   _ __   ___ | |_| |_ ___ _ __ \n" +
                " | '_ \\ / _` | '__| '__| | | | | '_ \\ / _ \\| __| __/ _ \\ '__|\n" +
                " | | | | (_| | |  | |  | |_| | | |_) | (_) | |_| ||  __/ |   \n" +
                " |_| |_|\\__,_|_|  |_|   \\__, | | .__/ \\___/ \\__|\\__\\___|_|   \n" +
                "                        |___/  |_|                         \n" +
                "  S. Lu - I solemnly swear that I am up to no good.\n";
        print("Hello from\n" + logo);
        print(
                "       Messrs Moony, Wormtail, Padfoot, and Prongs\n" +
                        "       Purveyors of Aids to Magical Mischief-Makers\n" +
                        "                  are proud to present\n" +
                        "                 --THE MARAUDER'S MAP--");
    }

    public void hello() {
        print("I see you are lost. \n" +
                "Read the charm beneath out loud, and I shall serve you.");
        print("- \"I solemnly swear that I am up to no good.\" ");
    }

    public void exit() {
        line();
        print("Mischief managed.");
        line();
    }


    public void addTask(Task t, int taskCount) {
        print("|| Got it. I've added this task");
        print("|| \t" + t.toString());
        print("|| Now you have " + taskCount + " tasks in the list.");
    }


    public void markDone(int id, boolean isDone, Task t) throws InvalidIndexException, EmptyListException {
        int i = id - 1;
        if (isDone) {
            print("Nice!I've marked this task as done:");
            print(id + ". " + t.toString());
        }
    }


    public static void loadingData() {
        print("Please wait. Loading data ..... ");
    }


    public static void loadData(String data) {
        print(data);
    }

    public void delete(Task t, int taskCount) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("|| \t" + t.toString());
        System.out.println("|| Now you have " + taskCount + " in the list.");
    }

    public void printFalseCharmMsg (int n) {
        if (n == 0) {
            System.out.println("Mr Moony presents his compliments to Professor Snape \n" +
                    "and begs him to keep his abnormally large nose \n" +
                    "out of other people's business. ");
        } else if (n == 1) {
            System.out.println("Mr Prongs agrees with Mr Moony \n" +
                    "and would like to add that Professor Snape is an ugly git.");
        } else if (n == 2) {
            System.out.println("Mr Padfoot would like to register \n" +
                    "his astonishment that an idiot like that ever became a Professor.");
        } else if (n == 3) {
            System.out.println("Mr Wormtail bids Professor Snape good day, \n" +
                    "and advises him to wash his hair, the slime-ball.\"");
        }
    }
}

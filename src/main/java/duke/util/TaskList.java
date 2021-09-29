package duke.util;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.exception.NoKeywordException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.PrintBot;

import java.util.ArrayList;

import static duke.ui.PrintBot.print;


public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task getTask(int i) throws InvalidIndexException,EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if (i == -1 || i >= tasks.size()) {
            throw new InvalidIndexException();
        }

        return tasks.get(i);
    }

    public Todo addTodo(String description) {
        Todo t = new Todo(description);
        addTask(t);
        return t;
    }

    public Deadline addDeadline (String description, String deadline) throws NoKeywordException {
        try {
            Deadline d = new Deadline(description, deadline);
            addTask(d);
            return d;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoKeywordException();
        }
    }

    public Event addEvent (String description, String duration) throws NoKeywordException {
        try {
            Event e = new Event(description, duration);
            addTask(e);
            return e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoKeywordException();
        }
    }

    public Task markDone(int id, boolean isDone) throws EmptyListException, InvalidIndexException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        int i = id -1;
        //index in string
        Task t = getTask(i);
        if (isDone) {
            t.setDone(true);
        }
        return t;
    }

    public Task delete(int id) throws InvalidIndexException, EmptyListException {
        int i = id - 1;
        Task t = getTask(i);
        tasks.remove(i);
        return t;
    }

    public void printList() throws EmptyListException {
        print("Here are the tasks in your list:\n");
        int id = 1;
        for (Task t : tasks) {
            System.out.println(id + ". " + t);
            id++;
        }
    }

    public int getSize() {
        return tasks.size();
    }
}

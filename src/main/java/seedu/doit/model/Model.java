package seedu.doit.model;

import java.util.Set;

import seedu.doit.commons.core.UnmodifiableObservableList;
import seedu.doit.commons.exceptions.EmptyTaskManagerStackException;
import seedu.doit.model.item.ReadOnlyTask;
import seedu.doit.model.item.Task;
import seedu.doit.model.item.UniqueTaskList;
import seedu.doit.model.item.UniqueTaskList.DuplicateTaskException;
import seedu.doit.model.item.UniqueTaskList.TaskNotFoundException;

//@@author A0139399J
/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * Clears existing backing model and replaces with the provided new data.
     */
    void resetData(ReadOnlyItemManager newData);

    /**
     * Returns the TaskManager
     */
    ReadOnlyItemManager getTaskManager();

    /**
     * Deletes the given task.
     */
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /**
     * Adds the given task
     */
    void addTask(Task task) throws DuplicateTaskException;

    /**
     * Marks the given task as completed
     */
    void markTask(int taskIndex, ReadOnlyTask taskToDone)
            throws UniqueTaskList.TaskNotFoundException, DuplicateTaskException;

    /**
     * Marks the given task as uncompleted
     */
    void unmarkTask(int filteredTaskListIndex, ReadOnlyTask taskToDone)
            throws TaskNotFoundException, DuplicateTaskException;

    /**
     * Updates the task located at {@code filteredTaskListIndex} with
     * {@code editedTask}.
     *
     * @throws DuplicateTaskException
     *             if updating the task's details causes the task to be
     *             equivalent to another existing task in the list.
     * @throws IndexOutOfBoundsException
     *             if {@code filteredTaskListIndex} < 0 or >= the size of the
     *             filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask) throws DuplicateTaskException;

    /**
     * Returns the filtered task list as an
     * {@code UnmodifiableObservableList<ReadOnlyTask>}
     */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to show all tasks
     */
    void updateFilteredListToShowAll();

    /**
     * Updates the filter of the filtered task list to filter by the given
     * keywords
     */
    void updateFilteredTaskList(Set<String> nameKeywords, Set<String> priorityKeywords, Set<String> descriptionKeywords,
            Set<String> tagKeywords);

    /**
     * Set how the tasks are sorted
     */
    void sortBy(String sortType);

    // @@author A0138909R
    /**
     * Undo the previous undoable command.
     *
     * Undo command is not undoable
     *
     * @throws EmptyTaskManagerStackException
     */
    void undo() throws EmptyTaskManagerStackException;

    /**
     * Reverse the undo command.
     *
     * Only undo command is redoable
     *
     * @throws EmptyTaskManagerStackException
     */
    void redo() throws EmptyTaskManagerStackException;

    /**
     * Clears all the data into a data with no tasks
     */
    void clearData();
    // @@author



}

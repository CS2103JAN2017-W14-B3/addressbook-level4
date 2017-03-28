package seedu.doit.commons.core;

import static seedu.doit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.Serializable;

import seedu.doit.logic.commands.AddCommand;
import seedu.doit.logic.commands.ClearCommand;
import seedu.doit.logic.commands.DeleteCommand;
import seedu.doit.logic.commands.DoneCommand;
import seedu.doit.logic.commands.EditCommand;
import seedu.doit.logic.commands.ExitCommand;
import seedu.doit.logic.commands.FindCommand;
import seedu.doit.logic.commands.HelpCommand;
import seedu.doit.logic.commands.ListCommand;
import seedu.doit.logic.commands.RedoCommand;
import seedu.doit.logic.commands.SaveCommand;
import seedu.doit.logic.commands.SelectCommand;
import seedu.doit.logic.commands.SetCommand;
import seedu.doit.logic.commands.SortCommand;
import seedu.doit.logic.commands.UndoCommand;
import seedu.doit.logic.commands.exceptions.CommandExistedException;
import seedu.doit.logic.commands.exceptions.NoSuchCommandException;

//@@author A0138909R
public class CommandSettings implements Serializable {

    private String add;
    private String delete;
    private String edit;
    private String done;
    private String clear;
    private String exit;
    private String find;
    private String help;
    private String list;
    private String redo;
    private String save;
    private String select;
    private String sort;
    private String undo;
    private String set;

    private static CommandSettings instance = null;

    public static CommandSettings getInstance() {
        if (instance == null) {
            instance = new CommandSettings();
        }
        return instance;
    }

    protected CommandSettings() {
        this.add = AddCommand.COMMAND_WORD;
        this.delete = DeleteCommand.COMMAND_WORD;
        this.edit = EditCommand.COMMAND_WORD;
        this.done = DoneCommand.COMMAND_WORD;
        this.clear = ClearCommand.COMMAND_WORD;
        this.exit = ExitCommand.COMMAND_WORD;
        this.find = FindCommand.COMMAND_WORD;
        this.help = HelpCommand.COMMAND_WORD;
        this.list = ListCommand.COMMAND_WORD;
        this.redo = RedoCommand.COMMAND_WORD;
        this.save = SaveCommand.COMMAND_WORD;
        this.select = SelectCommand.COMMAND_WORD;
        this.sort = SortCommand.COMMAND_WORD;
        this.undo = UndoCommand.COMMAND_WORD;
        this.set = SetCommand.COMMAND_WORD;
    }

    public void setCommandSettings(String add, String delete, String edit, String done, String clear, String exit,
            String find, String help, String list, String redo, String save, String select, String sort, String undo,
            String set) {
        this.add = add;
        this.delete = delete;
        this.edit = edit;
        this.done = done;
        this.clear = clear;
        this.exit = exit;
        this.find = find;
        this.help = help;
        this.list = list;
        this.redo = redo;
        this.save = save;
        this.select = select;
        this.sort = sort;
        this.undo = undo;
        this.set = set;
    }

    // Getter
    public String getAdd() {
        return this.add;
    }

    public String getDelete() {
        return this.delete;
    }

    public String getEdit() {
        return this.edit;
    }

    public String getDone() {
        return this.done;
    }

    public String getClear() {
        return this.clear;
    }

    public String getExit() {
        return this.exit;
    }

    public String getFind() {
        return this.find;
    }

    public String getHelp() {
        return this.help;
    }

    public String getList() {
        return this.list;
    }

    public String getRedo() {
        return this.redo;
    }

    public String getSave() {
        return this.save;
    }

    public String getSelect() {
        return this.select;
    }

    public String getSet() {
        return this.set;
    }

    public String getSort() {
        return this.sort;
    }

    public String getUndo() {
        return this.undo;
    }

    // Setter
    public static void setInstance(CommandSettings commandSettings) {
        instance = commandSettings;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public void setList(String list) {
        this.list = list;
    }

    public void setRedo(String redo) {
        this.redo = redo;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setUndo(String undo) {
        this.undo = undo;
    }

    public void setCommand(String oldCommand, String newCommand)
            throws NoSuchCommandException, CommandExistedException {
        if (doesCommandExist(newCommand)) {
            throw new CommandExistedException(MESSAGE_UNKNOWN_COMMAND);
        } else if (AddCommand.COMMAND_WORD.equals(oldCommand) || this.add.equals(oldCommand)) {
            setAdd(newCommand);
        } else if (EditCommand.COMMAND_WORD.equals(oldCommand) || this.edit.equals(oldCommand)) {
            setEdit(newCommand);
        } else if (SelectCommand.COMMAND_WORD.equals(oldCommand) || this.select.equals(oldCommand)) {
            setSelect(newCommand);
        } else if (DoneCommand.COMMAND_WORD.equals(oldCommand) || this.done.equals(oldCommand)) {
            setDone(newCommand);
        } else if (SortCommand.COMMAND_WORD.equals(oldCommand) || this.sort.equals(oldCommand)) {
            setSort(newCommand);
        } else if (DeleteCommand.COMMAND_WORD.equals(oldCommand) || this.delete.equals(oldCommand)) {
            setDelete(newCommand);
        } else if (ClearCommand.COMMAND_WORD.equals(oldCommand) || this.clear.equals(oldCommand)) {
            setClear(newCommand);
        } else if (FindCommand.COMMAND_WORD.equals(oldCommand) || this.find.equals(oldCommand)) {
            setFind(newCommand);
        } else if (ListCommand.COMMAND_WORD.equals(oldCommand) || this.list.equals(oldCommand)) {
            setList(newCommand);
        } else if (ExitCommand.COMMAND_WORD.equals(oldCommand) || this.exit.equals(oldCommand)) {
            setExit(newCommand);
        } else if (HelpCommand.COMMAND_WORD.equals(oldCommand) || this.help.equals(oldCommand)) {
            setHelp(newCommand);
        } else if (SaveCommand.COMMAND_WORD.equals(oldCommand) || this.save.equals(oldCommand)) {
            setSave(newCommand);
        } else if (UndoCommand.COMMAND_WORD.equals(oldCommand) || this.undo.equals(oldCommand)) {
            setUndo(newCommand);
        } else if (RedoCommand.COMMAND_WORD.equals(oldCommand) || this.redo.equals(oldCommand)) {
            setRedo(newCommand);
        } else if (SetCommand.COMMAND_WORD.equals(oldCommand) || this.set.equals(oldCommand)) {
            setSet(newCommand);
        } else {
            throw new NoSuchCommandException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public boolean doesCommandExist(String command) {
        switch (command) {

        case AddCommand.COMMAND_WORD:
        case EditCommand.COMMAND_WORD:
        case SelectCommand.COMMAND_WORD:
        case DoneCommand.COMMAND_WORD:
        case SortCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD:
        case SaveCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_WORD:
        case SetCommand.COMMAND_WORD:
            return true;
        }
        if (this.add.equals(command) || this.delete.equals(command) || this.edit.equals(command)
                || this.done.equals(command) || this.clear.equals(command) || this.exit.equals(command)
                || this.find.equals(command) || this.help.equals(command) || this.list.equals(command)
                || this.redo.equals(command) || this.save.equals(command) || this.select.equals(command)
                || this.set.equals(command) || this.sort.equals(command) || this.undo.equals(command)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.add == null) ? 0 : this.add.hashCode());
        result = (prime * result) + ((this.clear == null) ? 0 : this.clear.hashCode());
        result = (prime * result) + ((this.delete == null) ? 0 : this.delete.hashCode());
        result = (prime * result) + ((this.done == null) ? 0 : this.done.hashCode());
        result = (prime * result) + ((this.edit == null) ? 0 : this.edit.hashCode());
        result = (prime * result) + ((this.exit == null) ? 0 : this.exit.hashCode());
        result = (prime * result) + ((this.find == null) ? 0 : this.find.hashCode());
        result = (prime * result) + ((this.help == null) ? 0 : this.help.hashCode());
        result = (prime * result) + ((this.list == null) ? 0 : this.list.hashCode());
        result = (prime * result) + ((this.redo == null) ? 0 : this.redo.hashCode());
        result = (prime * result) + ((this.save == null) ? 0 : this.save.hashCode());
        result = (prime * result) + ((this.select == null) ? 0 : this.select.hashCode());
        result = (prime * result) + ((this.set == null) ? 0 : this.set.hashCode());
        result = (prime * result) + ((this.sort == null) ? 0 : this.sort.hashCode());
        result = (prime * result) + ((this.undo == null) ? 0 : this.undo.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Add : " + this.add + "\n");
        sb.append("Delete : " + this.delete + "\n");
        sb.append("Edit : " + this.edit + "\n");
        sb.append("Done : " + this.done + "\n");
        sb.append("Clear : " + this.clear + "\n");
        sb.append("Exit : " + this.exit + "\n");
        sb.append("Find : " + this.find + "\n");
        sb.append("Help : " + this.help + "\n");
        sb.append("List : " + this.list + "\n");
        sb.append("Redo : " + this.redo + "\n");
        sb.append("Save : " + this.save + "\n");
        sb.append("Select : " + this.select + "\n");
        sb.append("Set : " + this.set + "\n");
        sb.append("Sort : " + this.sort + "\n");
        sb.append("Undo : " + this.undo);
        return sb.toString();
    }

}

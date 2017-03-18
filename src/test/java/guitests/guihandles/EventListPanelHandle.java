package guitests.guihandles;


import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import guitests.GuiRobot;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.doit.TestApp;
import seedu.doit.model.item.Event;
import seedu.doit.model.item.ReadOnlyEvent;
import seedu.doit.testutil.TestUtil;

/**
 * Provides a handle for the panel containing the event list.
 */
public class EventListPanelHandle extends GuiHandle {

    public static final int NOT_FOUND = -1;
    public static final String CARD_PANE_ID = "#cardPane";

    private static final String TASK_LIST_VIEW_ID = "#eventListView";

    public EventListPanelHandle(GuiRobot guiRobot, Stage primaryStage) {
        super(guiRobot, primaryStage, TestApp.APP_TITLE);
    }

    public List<ReadOnlyEvent> getSelectedTasks() {
        ListView<ReadOnlyEvent> eventList = getListView();
        return eventList.getSelectionModel().getSelectedItems();
    }

    public ListView<ReadOnlyEvent> getListView() {
        return getNode(TASK_LIST_VIEW_ID);
    }

    /**
     * Returns true if the list is showing the task details correctly and in correct order.
     *
     * @param events A list of task in the correct order.
     */
    public boolean isListMatching(ReadOnlyEvent... events) {
        return this.isListMatching(0, events);
    }

    /**
     * Returns true if the list is showing the task details correctly and in correct order.
     *
     * @param startPosition The starting position of the sub list.
     * @param events         A list of event in the correct order.
     */
    public boolean isListMatching(int startPosition, ReadOnlyEvent... events) throws IllegalArgumentException {
        if (events.length + startPosition != getListView().getItems().size()) {
            throw new IllegalArgumentException("List size mismatched\n" +
                "Expected " + (getListView().getItems().size() - 1) + " events");
        }
        assertTrue(this.containsInOrder(startPosition, events));
        for (int i = 0; i < events.length; i++) {
            final int scrollTo = i + startPosition;
            this.guiRobot.interact(() -> getListView().scrollTo(scrollTo));
            this.guiRobot.sleep(200);
            if (!TestUtil.compareCardAndEvent(getEventCardHandle(startPosition + i), events[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Clicks on the ListView.
     */
    public void clickOnListView() {
        Point2D point = TestUtil.getScreenMidPoint(getListView());
        this.guiRobot.clickOn(point.getX(), point.getY());
    }

    /**
     * Returns true if the {@code tasks} appear as the sub list (in that order) at position {@code startPosition}.
     */
    public boolean containsInOrder(int startPosition, ReadOnlyEvent... events) {
        List<ReadOnlyEvent> eventsInList = getListView().getItems();

        // Return false if the list in panel is too short to contain the given list
        if (startPosition + events.length > eventsInList.size()) {
            return false;
        }

        // Return false if any of the tasks doesn't match
        for (int i = 0; i < events.length; i++) {
            if (!eventsInList.get(startPosition + i).getName().fullName.equals(events[i].getName().fullName)) {
                return false;
            }
        }

        return true;
    }

    public EventCardHandle navigateToEvent(String name) {
        this.guiRobot.sleep(500); //Allow a bit of time for the list to be updated
        final Optional<ReadOnlyEvent> event = getListView().getItems().stream()
            .filter(p -> p.getName().fullName.equals(name))
            .findAny();
        if (!event.isPresent()) {
            throw new IllegalStateException("Name not found: " + name);
        }

        return navigateToEvent(event.get());
    }

    /**
     * Navigates the listview to display and select the event.
     */
    public EventCardHandle navigateToEvent(ReadOnlyEvent event) {
        int index = getEventIndex(event);

        this.guiRobot.interact(() -> {
            getListView().scrollTo(index);
            this.guiRobot.sleep(150);
            getListView().getSelectionModel().select(index);
        });
        this.guiRobot.sleep(100);
        return getEventCardHandle(event);
    }


    /**
     * Returns the position of the task given, {@code NOT_FOUND} if not found in the list.
     */
    public int getEventIndex(ReadOnlyEvent targetEvent) {
        List<ReadOnlyEvent> eventsInList = getListView().getItems();
        for (int i = 0; i < eventsInList.size(); i++) {
            if (eventsInList.get(i).getName().equals(targetEvent.getName())) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Gets a task from the list by index
     */
    public ReadOnlyEvent getEvent(int index) {
        return getListView().getItems().get(index);
    }

    public EventCardHandle getEventCardHandle(int index) {
        return getEventCardHandle(new Event(getListView().getItems().get(index)));
    }

    public EventCardHandle getEventCardHandle(ReadOnlyEvent event) {
        Set<Node> nodes = getAllCardNodes();
        Optional<Node> eventCardNode = nodes.stream()
            .filter(n -> new EventCardHandle(this.guiRobot, this.primaryStage, n).isSameEvent(event))
            .findFirst();
        if (eventCardNode.isPresent()) {
            return new EventCardHandle(this.guiRobot, this.primaryStage, eventCardNode.get());
        } else {
            return null;
        }
    }

    protected Set<Node> getAllCardNodes() {
        return this.guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    public int getNumberOfEvents() {
        return getListView().getItems().size();
    }
}

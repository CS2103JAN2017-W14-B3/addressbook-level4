package seedu.doit.ui;
//@@author: A0160076L
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.doit.commons.core.LogsCenter;
import seedu.doit.commons.events.ui.NewResultAvailableEvent;
import seedu.doit.commons.util.FxViewUtil;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(ResultDisplay.class);
    private static final String FXML = "ResultDisplay.fxml";

    private final StringProperty displayed = new SimpleStringProperty("");

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextArea resultDisplay;

    public ResultDisplay(AnchorPane placeHolder) {
        super(FXML);
        this.resultDisplay.textProperty().bind(this.displayed);
        FxViewUtil.applyAnchorBoundaryParameters(this.resultDisplay, 0.0, 0.0, 0.0, 0.0);
        FxViewUtil.applyAnchorBoundaryParameters(this.mainPane, 0.0, 0.0, 0.0, 0.0);
        placeHolder.getChildren().add(this.mainPane);
        registerAsAnEventHandler(this);
    }

    @Subscribe
    private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        this.displayed.setValue(event.message);
    }

}
